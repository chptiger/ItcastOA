package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	
//	======================declare service==================
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected UserService userService;
	
//	=======================support ModelDriven ============
	protected T model ;
	
	public BaseAction(){
		System.out.println("-------------> BaseAction Constructor");
		try{
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> claz = (Class<T>) pt.getActualTypeArguments()[0];
//			get model instance by reflection
			model = claz.newInstance();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public T getModel() {
		return model;
	}
}
