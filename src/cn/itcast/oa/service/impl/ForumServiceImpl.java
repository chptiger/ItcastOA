package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.ForumService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService{
	
	/**
	 * Overwrite save()
	 */
	@Override
	public void save(Forum forum) {
		getSession().save(forum);
		forum.setPosition(forum.getId().intValue());
	}
	
	/**
	 * Overwrite findAll()
	 */
	@Override
	public List<Forum> findAll() {
		return getSession().createQuery(//
				"FROM Forum f ORDER BY f.position")//
				.list();
	}
	
	@Override
	public void moveDown(Long id) {
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(//
					  "FROM Forum f WHERE f.position > ? ORDER BY f.position")
					  .setParameter(0, forum.getPosition())
					  .setFirstResult(0)
					  .setMaxResults(1)
					  .uniqueResult();
		
		if (other == null) {
			return;
		}
		
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		
		getSession().update(forum);
		getSession().update(other);
	}

	@Override
	public void moveUp(Long id) {
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(//
					  "FROM Forum f WHERE f.position < ? ORDER BY f.position DESC")
					  .setParameter(0, forum.getPosition())
					  .setFirstResult(0)
					  .setMaxResults(1)
					  .uniqueResult();
		
		if (other == null) {
			return;
		}
		
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		
		getSession().update(forum);
		getSession().update(other);
	}

}
