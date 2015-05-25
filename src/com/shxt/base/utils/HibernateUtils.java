package com.shxt.base.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * 
 * @描述: 封装的Hibernate的工具类
 * @作者:刘文铭
 * @版本:1.0
 * @版权所有:
 * @时间 2014-12-3 下午06:15:20
 */
public class HibernateUtils {
	
	private static Configuration cfg = null;
	private static SessionFactory factory = null;
	
	static{
		cfg = new Configuration();
		cfg.configure();//读取hibernate.cfg.xml
		factory = cfg.buildSessionFactory();
	}
	/**
	 * @描述:获取Session
	 * @作者: 刘文铭
	 * @时间:2014-12-3 下午06:14:45
	 * @参数:@return 
	 * @返回值：Session
	 */
	public static Session getSession(){
		return factory.openSession();
	}
	/**
	 * @描述: 关闭Session
	 * @作者: 刘文铭
	 * @时间:2014-12-3 下午06:15:02
	 * @参数:@param session 
	 * @返回值：void
	 */
	
	public static void closeSession(Session session){
		if(session!=null)session.close();
	}

}
