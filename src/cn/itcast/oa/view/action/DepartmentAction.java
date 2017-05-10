package cn.itcast.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private Long parentId;
	
	/** 列表 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		if( parentId == null){
			departmentList = departmentService.findTopList();
		}else{
			departmentList = departmentService.findChildren(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/** 列表 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** 列表 */
	public String addUI() throws Exception {
//		prepare data
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "saveUI";
	}

	/** 列表 */
	public String add() throws Exception {
		// 封装对象
		// Department department = new Department();
		// department.setName(name);
		// department.setDescription(description);
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		// 保存到数据库
		departmentService.save(model);

		return "toList";
	}

	/** 列表 */
	public String editUI() throws Exception {
		// 准备回显的数据
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		
		if(department.getParent() !=null && department.getParent().getId()!= null){
			this.parentId = department.getParent().getId();
		}
		
//		prepare data
//		List<Department> departmentList = departmentService.findAll();
//		ActionContext.getContext().put("departmentList", departmentList);
		
//		prepare data in tree structure
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, department);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	/** 列表 */
	public String edit() throws Exception {
		// 1，从数据库中取出要修改的原始数据
		Department department = departmentService.getById(model.getId());

		// 2，设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		
//		edit higher office
		Department parent = departmentService.getById(parentId);
		department.setParent(parent);
		// 3，更新到数据库
		departmentService.update(department);

		return "toList";
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

}
