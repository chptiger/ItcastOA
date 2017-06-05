package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.UserService;
@Service
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService{

	@Override
	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"From Privilege p where p.parent is null")
				.list();
	}

	@Override
	public List<String> getAllPrivilegeUrls() {
		return getSession().createQuery(//
				"SELECT distinct p.url From Privilege p where p.url is not null")
				.list();
	}

}
