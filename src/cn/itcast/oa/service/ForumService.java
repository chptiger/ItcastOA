package cn.itcast.oa.service;


import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {

	void moveDown(Long id);

	void moveUp(Long id);

}
