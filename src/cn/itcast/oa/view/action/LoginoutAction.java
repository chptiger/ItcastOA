package cn.itcast.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.User;

@Controller
@Scope("prototype")
public class LoginoutAction extends BaseAction<User>{
	
	public String loginUI() throws Exception {
		
		return "loginUI";
	}
	
	public String login() throws Exception {
		User user = userService.findByLoginNameAndPassword(
				model.getLoginName(), model.getPassword());
		if (user == null) {
			addFieldError("login", "login name or password is wrong");
			return "loginUI";
		} else {
			ActionContext.getContext().getSession().put("user", user);
			return "toHome";
		}
	}
	
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
}
