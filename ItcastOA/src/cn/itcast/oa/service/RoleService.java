package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Role;

public interface RoleService {

	/**
	 * 查询所有的岗位信息
	 * 
	 * @return
	 */
	List<Role> findAll();

	/**
	 * 删除岗位信息
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 保存
	 * @param role
	 */
	void save(Role role);

	/**
	 * 获取
	 * @param id
	 * @return
	 */
	Role getById(Long id);

	/**
	 * 更新
	 * @param role
	 */
	void update(Role role);

}
