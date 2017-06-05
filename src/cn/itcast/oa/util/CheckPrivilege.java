package cn.itcast.oa.util;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilege extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		System.out.println("==================> interceptor start  <===================");
//		String result = invocation.invoke();
//		System.out.println("==================> interceptor finish  <===================");
//		prepare data
//		current user
		User user = (User) ActionContext.getContext().getSession().get("user");
//		current url
		String namespace = invocation.getProxy().getNamespace();
		if (namespace == null || "".equals(namespace)) {
			namespace = "/";
		}
		if (!namespace.endsWith("/")) {
			namespace += "/";
		}
		String url = namespace + invocation.getProxy().getActionName();
		if (user == null) { 
//		一，如果用户未登录，就转到登录页面
			if (url.startsWith("/loginout_login")) {// loginout_loginUI / loginout_login
//		a,如果当前访问的是登录功能，放行
				return invocation.invoke();
			} else {
//		b,如果当前访问的不是登录功能，就转到登录页面
				return "loginUI";
			}
		} else {
//		二，如果用户已登录，就判断权限
			if (user.hasPrivilegeByUrl(url)) {
//		a, 如果有权限访问当前的URL, 放行
				return invocation.invoke();
			} else {
//		b, 如果没有权限访问当前的URL, 转到提示消息的页面
				return "noPrivilegeUI";
			}
		}
	}

}
