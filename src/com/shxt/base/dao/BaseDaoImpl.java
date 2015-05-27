package com.shxt.base.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shxt.base.utils.AliasToBeanResultTransformer;


public class BaseDaoImpl implements IBaseDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void add(Object object) {
		getSession().save(object);
	}

	public void delete(Class<?> clazz, Integer id) {
		getSession().delete(getSession().get(clazz, id));
	}

	public void delete(Object object) {
		getSession().delete(object);
	}

	public void update(Object object) {
		getSession().update(object);
	}

	public List<?> list(String hql) {
		return this.list(hql, null);
	}

	public List<?> list(String hql, Object arg) {
		return this.list(hql, new Object[] { arg });
	}

	public List<?> list(String hql, Object[] args) {//
		Query query = getSession().createQuery(hql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query.list();
	}

	public Object load(Class<?> clazz, Integer id) {
		return getSession().get(clazz, id);
	}

	public Object query(String hql) {
		return this.query(hql, null);
	}

	public Object query(String hql, Object arg) {
		return this.query(hql, new Object[] { arg });
	}

	public Object query(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query.uniqueResult();
	}

	public void updateByHql(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		query.executeUpdate();
	}
	public void updateByHql(String hql, Object arg) {
		this.updateByHql(hql, new Object[] { arg });
	}
	public void updateByHql(String hql) {
		this.updateByHql(hql, null);
	}

	public PageBean find(String hql, Object arg, PageBean pageBean) {
		return this.find(hql, new Object[] { arg }, pageBean);
	}
	public PageBean find(String hql, PageBean pageBean) {
		return this.find(hql, null, pageBean);
	}

	public PageBean find(String hql, Object[] args, PageBean pageBean) {
		// 获取总的结果集
		Query query = getSession().createQuery(hql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		query.setFirstResult(
				(pageBean.getPageNow() - 1) * pageBean.getPageSize())
				.setMaxResults(pageBean.getPageSize());

		// 存结果集的记录
		pageBean.setDatas(query.list());
		// 获取总记录数
		String count_hql = this.getCountHQL(hql);
		Query count_query = getSession().createQuery(count_hql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				count_query.setParameter(i, args[i]);
			}
		}
		// 存总记录数
		Long totalCount = (Long) count_query.uniqueResult();
		pageBean.setTotalCount(totalCount);
		// 存总页数
		Long totalPages = totalCount % pageBean.getPageSize() == 0 ? totalCount
				/ pageBean.getPageSize() : totalCount / pageBean.getPageSize()
				+ 1;
		pageBean.setTotalPages(totalPages);

		return pageBean;
	}

	private String getCountHQL(String hql) {
		String str = hql.substring(0, hql.indexOf("from"));
		String count_hql = "";
		if (str.trim().length() == 0) {
			count_hql = "select count(*) " + hql;
		} else {
			count_hql = hql.replace(str, "select count(*) ");
		}

		return count_hql;
	}

	public List<?> listSQL(String sql, Object[] args, Class<?> clazz,
			boolean isHBM) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		// 关键步骤--判断
		if (isHBM) {
			query.addEntity(clazz);
		} else {
			query.setResultTransformer(new AliasToBeanResultTransformer(clazz));// DTO
		}

		return query.list();

	}

	public List<?> listSQL(String sql, Object arg, Class<?> clazz, boolean isHBM) {
		return this.listSQL(sql, new Object[] { arg }, clazz, isHBM);
	}

	public List<?> listSQL(String sql, Class<?> clazz, boolean isHBM) {
		return this.listSQL(sql, null, clazz, isHBM);
	}

	public Object querySQL(String sql, Object[] args, Class<?> clazz,
			boolean isHBM) {

		SQLQuery query = getSession().createSQLQuery(sql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		// 关键步骤--判断
		if (isHBM) {
			query.addEntity(clazz);
		} else {
			query.setResultTransformer(new AliasToBeanResultTransformer(clazz));
		}

		return query.uniqueResult();

	}

	public Object querySQL(String sql, Object arg, Class<?> clazz, boolean isHBM) {
		return this.querySQL(sql, new Object[] { arg }, clazz, isHBM);
	}

	public Object querySQL(String sql, Class<?> clazz, boolean isHBM) {
		return this.querySQL(sql, null, clazz, isHBM);
	}
	
	public void updateBySQL(String sql, Object[] args) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		query.executeUpdate();
		
	}

	public void updateBySQL(String sql, Object arg) {
		this.updateBySQL(sql, new Object[]{arg});
		
	}

	public void updateBySQL(String sql) {
		this.updateBySQL(sql,null);
		
	}

}
