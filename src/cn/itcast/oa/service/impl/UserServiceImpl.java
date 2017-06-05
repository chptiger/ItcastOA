package cn.itcast.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService{
	@Override
	public void save(User user) {
		String md5 = DigestUtils.md5Hex("1234");
//		default password is 1234
		user.setPassword(md5);
		super.save(user);
	}

	public User findByLoginNameAndPassword(String loginName, String password) {
		String md5 = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery("From User u WHERE u.loginName=? AND u.password=?")
				.setParameter(0, loginName)
				.setParameter(1, md5)
				.uniqueResult();// if return size more than one, throw hibernate exception
	}
}
