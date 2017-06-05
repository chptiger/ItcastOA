package cn.itcast.oa.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;

public class OAInitListener implements ServletContextListener {
	
	private Log log = LogFactory.getLog(OAInitListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
//		get privilegeService from Spring container
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);// 获取spring的监听器中创建的spring容器对象
		
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
// 		1. check all top privilegeList and put into application
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		log.info("=========topPrivilegeList already put into application!===========");
//		2. 查询出所有的权限的URL集合并放到 application作用域中
		List<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		log.info("=========allPrivilegeUrls already put into application!===========");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
