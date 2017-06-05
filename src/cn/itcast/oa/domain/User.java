package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户
 * @author tyg
 * 
 */
public class User implements Serializable{
	private Long id;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();

	private String loginName; // 登录名
	private String password; // 密码
	private String name; // 真实姓名
	private String gender; // 性别
	private String phoneNumber; // 电话号码
	private String email; // 电子邮件
	private String description; // 说明
	
	/**
	 * check current user if has privilege by privUrl
	 * @param privUrl
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privUrl) {
//		if current user is super user, get all privilege.
		if (isAdmin()) {
			return true;
		}
//		if current user is common user, check if has privilege.
		
		int pos = privUrl.indexOf("?");
		if (pos > -1) {
			privUrl = privUrl.substring(0, pos);
		}
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}
//		如果是普通用户，就需要判断权限
		Collection<String> allPrivilege = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilege.contains(privUrl)) {
//		a，如果这个URL是不需要控制的功能（登录后可以直接使用），直接返回true
			return true;
		} else {
//		b, 如果这个URL是需要控制的功能（登录后，需要响应的权限才能使用），此时应判断权限
		
			for (Role role : roles) {
				for (Privilege p : role.getPrivileges()) {
					if (privUrl.equals(p.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	/**
	 * check current user if has the privilege
	 * @param privName
	 * @return
	 */
	public boolean hasPrivilegeByName(String privName) {
//		if current user is super user, get all privilege.
		if (isAdmin()) {
			return true;
		}
//		if not common user, check if has privilege.
		for (Role role : roles) {
			for (Privilege p : role.getPrivileges()) {
				if (p.getName().equals(privName)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isAdmin() {
		return "admin".equals(loginName);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
