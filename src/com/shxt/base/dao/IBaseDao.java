package com.shxt.base.dao;

import java.util.List;

import org.hibernate.SQLQuery;

public interface IBaseDao {
	/**
	 * 描述:保存对象
	 * @param object
	 */
	public abstract void add(Object object);
	/**
	 * 更新对象
	 * @param object
	 */
	public abstract void update(Object object);
	/**
	 * 通过主键获取一个对象
	 * @param clazz
	 * @param id
	 * @return
	 */
	public abstract Object load(Class<?> clazz, Integer id);
	/**
	 * 通过主键删除对象
	 * @param clazz
	 * @param id
	 */
	public abstract void delete(Class<?> clazz, Integer id);
	/**
	 * 删除对象
	 * @param object
	 */
	public abstract void delete(Object object);
	/**
	 * 通过HQL获取一列表,没有条件
	 * @param hql
	 * @return
	 */
	public abstract List<?> list(String hql);
	/**
	 * 通过HQL获取一列表,一个条件
	 * @param hql
	 * @param arg
	 * @return
	 */
	public abstract List<?> list(String hql, Object arg);
	/**
	 * 通过HQL获取一列表,多个条件
	 * @param hql 语句
	 * @param args 参数
	 * @return
	 */
	public abstract List<?> list(String hql, Object[] args);
	
	/**
	 * 通过无条件HQL查询返回一个对象
	 * @param hql
	 * @return
	 */
	public abstract Object query(String hql);
	/**
	 * 通过一个条件HQL查询返回一个对象
	 * @param hql
	 * @param arg
	 * @return
	 */
	public abstract Object query(String hql, Object arg);
	/**
	 * 通过多个条件HQL查询返回一个对象
	 * @param hql
	 * @param args
	 * @return
	 */
	public abstract Object query(String hql, Object[] args);
	/**
	 * HQL能执行update和delete的HQL语句
	 * 更新/删除多个数据
	 * @param hql
	 * @param args
	 */
	public abstract void updateByHql(String hql, Object[] args);
	/**
	 * HQL能执行update和delete的HQL语句
	 * 更新一个数据
	 * @param hql
	 * @param arg
	 */
	public abstract void updateByHql(String hql, Object arg);
	/**
	 * HQL能执行update和delete的HQL语句
	 * 直接写HQL里面的参数
	 * @param hql
	 */
	public abstract void updateByHql(String hql);
	
	/*可以查询百度来完成，参考二期只是封装技巧也可以*/
	public abstract PageBean find(String hql, Object arg, PageBean pageBean);
	public abstract PageBean find(String hql, PageBean pageBean);
	public abstract PageBean find(String hql, Object[] args, PageBean pageBean);
	
	
	/**
	 * 
	 * @param sql 
	 * @param args
	 * @param clazz 对应的类型
	 * @param isHBM true代表有HBM文件或者有对应的注解方式  false 代码使用DTO形式
	 * @return
	 */
	public abstract List<?> listSQL(String sql, Object[] args,Class<?> clazz,boolean isHBM);
	public abstract List<?> listSQL(String sql, Object arg,Class<?> clazz,boolean isHBM);
	public abstract List<?> listSQL(String sql, Class<?> clazz,boolean isHBM);
	
	
	public abstract Object querySQL(String sql, Object[] args,Class<?> clazz,boolean isHBM);
	public abstract Object querySQL(String sql, Object arg,Class<?> clazz,boolean isHBM);
	public abstract Object querySQL(String sql, Class<?> clazz,boolean isHBM);
	
	
	//public abstract PageBean findSQL(String sql, Object[] args, Class<?> clazz, PageBean pageBean,boolean isHBM);
	
	public void updateBySQL(String sql, Object[] args);
	public void updateBySQL(String sql, Object arg);
	public void updateBySQL(String sql);
	
}
