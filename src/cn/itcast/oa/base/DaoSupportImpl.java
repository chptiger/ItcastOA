package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
//@Transactional is usable for public method in subclass
@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz = null; // 这是一个问题！

	// public BaseDaoImpl(Class<T> clazz) {
	// this.clazz = clazz;
	// }

	public DaoSupportImpl() {
		// 通过反射获取T的真是类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];

		System.out.println("---> clazz = " + clazz);
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(Long id) {
		if (id == null) {
			return;
		}

		Object entity = getById(id);
		if (entity != null) {
			getSession().delete(entity);
		}
	}

	public T getById(Long id) {
		if( id == null){
			return null;
		}
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByIds(Long[] ids) {
		return getSession().createQuery(//
				// 注意空格！
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids)// 注意一定要使用setParameterList()方法！
				.list();
	}

	public List<T> findAll() {
		// 注意空格！
		return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
	}

}
