package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	private Long departmentId;
	private Long[] roleIds;
	/** 列表 */
	public String list() throws Exception {
		// 准备数据
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
//		prepare data: departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);
		
//		prepare data: roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 封装对象
//		>> related a department
		model.setDepartment(departmentService.getById(departmentId));
//		>> related multiple roles
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		// 保存到数据库
		userService.save(model);
 
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备要回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		
//		prepare departmentId	
		if(user.getDepartment()!=null){
			departmentId = user.getDepartment().getId();
		}
		
//		prepare roleIds
		roleIds = new Long[user.getRoles().size()];
		int index = 0;
		for(Role role:user.getRoles()){
			roleIds[index++] = role.getId();
		}
//		prepare data: departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);
		
//		prepare data: roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中获取要修改的原始对象
		User user = userService.getById(model.getId());

		// 2, 设置要修改的属性
		user.setName(model.getName());
		user.setDescription(model.getDescription());
		user.setLoginName(model.getLoginName());
		user.setGender(model.getGender());
		user.setEmail(model.getEmail());
		user.setPhoneNumber(model.getPhoneNumber());
		
//		>> related a department
		user.setDepartment(departmentService.getById(departmentId));
//		>> related multiple roles
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}
	
	/**initialize password 1234 */
	public String initPassword() throws Exception{
		// 1，从数据库中获取要修改的原始对象
		User user = userService.getById(model.getId());

		// 2, 设置要修改的属性
		String md5 = DigestUtils.md5Hex("1234");
		user.setPassword(md5);
		
		// 3，更新到数据库
		userService.update(user);
		return "toList";
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
}
