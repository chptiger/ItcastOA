package cn.itcast.oa.install;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.User;

/**
 * Initial Data
 * 
 * @author Thomas
 *
 */
@Component
public class Installer {
	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();
		// ====================================================
		// 1. super mamager
		User user = new User();
		user.setLoginName("admin");
		user.setName("Super Manager");
		user.setPassword(DigestUtils.md5Hex("admin"));
		session.save(user);

		// ====================================================
		// 2. privilege data
		Privilege menu, menu1, menu2, menu3, menu4, menu5, menu6;
		menu = new Privilege("System", null, null);
		menu1 = new Privilege("Role", "/role_list", menu);
		menu2 = new Privilege("Department", "/department_list", menu);
		menu3 = new Privilege("User", "/user_list", menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

		session.save(new Privilege("Role List", "/role_list", menu1));
		session.save(new Privilege("Role Delete", "/role_delete", menu1));
		session.save(new Privilege("Role Add", "/role_add", menu1));
		session.save(new Privilege("Role Edit", "/role_edit", menu1));
		
		session.save(new Privilege("Department List", "/department_list", menu2));
		session.save(new Privilege("Department Delete", "/department_delete", menu2));
		session.save(new Privilege("Department Add", "/department_add", menu2));
		session.save(new Privilege("Department Edit", "/department_edit", menu2));
		
		session.save(new Privilege("User List", "/user_list", menu3));
		session.save(new Privilege("User Delete", "/user_delete", menu3));
		session.save(new Privilege("User Add", "/user_add", menu3));
		session.save(new Privilege("User Edit", "/user_edit", menu3));
		session.save(new Privilege("User Initial Password","/user_initPassword", menu3));

//		-----
		menu4 = new Privilege("Online Chat",null,null);
		menu5 = new Privilege("Forum Management", "/forumManage_list",menu4);
		menu6 = new Privilege("Forum", "/forum_list",menu4);
		session.save(menu4);
		session.save(menu5);
		session.save(menu6);
		
		// ------

		menu = new Privilege("审批流转", null, null);
		menu1 = new Privilege("审批流程管理", "/processDefinition_List", menu);
		menu2 = new Privilege("申请模板管理", "/template_List", menu);
		menu3 = new Privilege("起草申请", "/flow_templateList", menu);
		menu4 = new Privilege("待我审批", "/flow_myTaskList", menu);
		menu5 = new Privilege("我的申请查询", "/flow_myApplicationList", menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
	}

	public static void main(String[] args) {
		System.out.println("Initial data......");

		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();

		System.out.println("Initial data complete!");
	}

}
