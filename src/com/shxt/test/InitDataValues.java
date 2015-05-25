package com.shxt.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shxt.base.utils.HibernateUtils;
import com.shxt.framework.menu.model.Menu;
import com.shxt.framework.role.model.Role;
import com.shxt.framework.user.model.User;




public class InitDataValues {
	static Random ran = new Random();
	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		try {
			session  = HibernateUtils.getSession();
			tx = session.getTransaction();
			tx.begin();
			
			
			Role r1 = new Role();
			r1.setRole_name("超级管理员");
			Role r2 = new Role();
			r2.setRole_name("录入人员");
			Role r3 = new Role();
			r3.setRole_name("业务人员");
			
			
			session.save(r1);
			session.save(r2);
			session.save(r3);
			
			//根目录
			Menu m1 = new Menu();
			m1.setMenu_name("系统管理");
			session.save(m1);
			
			Menu m2 = new Menu();
			m2.setMenu_name("基础数据管理");
			session.save(m2);
			
			Menu m3 = new Menu();
			m3.setMenu_name("业务管理");
			session.save(m3);
			
			Menu m4 = new Menu();
			m4.setMenu_name("栏目管理");
			session.save(m4);
			
			Menu m5 = new Menu();
			m5.setMenu_name("统计管理");
			session.save(m5);
			
			Menu m101 = new Menu();
			m101.setMenu_name("系统用户管理");
			m101.setUrl("findUserAction.action");
			m101.setParent_id(String.valueOf(m1.getMenu_id()));
			
			Menu m102 = new Menu();
			m102.setMenu_name("角色管理");
			m102.setUrl("listRoleAction.action");
			m102.setParent_id(String.valueOf(m1.getMenu_id()));
			
			Menu m103 = new Menu();
			m103.setMenu_name("菜单管理");
			m103.setUrl("listParentMenuAction.action");
			m103.setParent_id(String.valueOf(m1.getMenu_id()));
			
			Menu m201 = new Menu();
			m201.setMenu_name("栏目管理");
			m201.setUrl("listColumnAction.action");
			m201.setParent_id(String.valueOf(m2.getMenu_id()));
			
			
			
			Menu m301 = new Menu();
			m301.setMenu_name("公司介绍");
			m301.setUrl("queryCompanyAction.action");
			m301.setParent_id(String.valueOf(m3.getMenu_id()));
			
			Menu m302 = new Menu();
			m302.setMenu_name("首页产品展示");
			m302.setUrl("首页产品展示");
			m302.setParent_id(String.valueOf(m3.getMenu_id()));
			
			
			Menu m401 = new Menu();
			m401.setMenu_name("车辆知识库");
			m401.setUrl("findCarKnowledgeAction.action");
			m401.setParent_id(String.valueOf(m4.getMenu_id()));
			
			Menu m402 = new Menu();
			m402.setMenu_name("生活知识库");
			m402.setUrl("findLiveAction.action");
			m402.setParent_id(String.valueOf(m4.getMenu_id()));
			
			Menu m403 = new Menu();
			m403.setMenu_name("惊喜活动维护");
			m403.setUrl("findActivityAction.action");
			m403.setParent_id(String.valueOf(m4.getMenu_id()));
			
			Menu m404 = new Menu();
			m404.setMenu_name("救援维护");
			m404.setUrl("findRescueAction.action");
			m404.setParent_id(String.valueOf(m4.getMenu_id()));
			
			Menu m405 = new Menu();
			m405.setMenu_name("新车维护");
			m405.setUrl("findCarAction.action");
			m405.setParent_id(String.valueOf(m4.getMenu_id()));
			
			
			
			session.save(m101);
			session.save(m102);
			session.save(m103);
			
			session.save(m201);
			
			session.save(m301);
			session.save(m302);
			
			session.save(m401);
			session.save(m402);
			session.save(m403);
			session.save(m404);
			session.save(m405);
			
			
			Menu[] menus = new Menu[]{m101,m102,m103,m201,m301,m302,m401,m402,m403,m404,m405};
			//超级管理员
			Set<Menu> adminMenus = new HashSet<Menu>();
			adminMenus.add(m101);
			adminMenus.add(m102);
			adminMenus.add(m103);
			
			r1.getMenuSet().addAll(adminMenus);
			
			r2.getMenuSet().add(menus[ran.nextInt(8)+3]);
			r2.getMenuSet().add(menus[ran.nextInt(8)+3]);
			r2.getMenuSet().add(menus[ran.nextInt(8)+3]);
			r2.getMenuSet().add(menus[ran.nextInt(8)+3]);
			r2.getMenuSet().add(menus[ran.nextInt(8)+3]);
			
			
			r3.getMenuSet().add(menus[ran.nextInt(8)+3]);
			r3.getMenuSet().add(menus[ran.nextInt(8)+3]);
			r3.getMenuSet().add(menus[ran.nextInt(8)+3]);
			r3.getMenuSet().add(menus[ran.nextInt(8)+3]);
			r3.getMenuSet().add(menus[ran.nextInt(8)+3]);
			
			
			// 帐号 姓名全拼+i
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String[] dateArr = new String[]{"1990-01-01","1992-02-01","1993-03-01",
											"1994-04-01","1995-05-01","1996-06-01",
											"1998-08-01","1999-09-01","2000-10-01",
											"2001-11-01","2002-12-01","2003-01-01",
											"2004-01-01","2005-02-01","2006-03-01",
											"2007-04-01","2008-05-01","2009-06-01",
											"2010-07-01","2011-08-01","2012-09-01",
											"2013-10-01","2014-01-01","2014-02-11",
											"2014-03-22","2014-04-14","2014-05-05",
											"2014-06-05","2014-07-05","2014-07-21"
											};
			
			Role[] roles = new Role[]{r1,r2,r3};
			for (int i = 0; i < 100; i++) {
				String userName = getName();
				String[] sex = new String[] { "男", "女","未知" };
				User user = new User();
				user.setAccount(CnToPinyinHelper.getPinyin(userName, false)	+ "_" + i);
				user.setSex(sex[ran.nextInt(3)]);
				user.setPassword("ly15");
				user.setCreate_name(getName());
				user.setCreate_date(sf.parse(dateArr[ran.nextInt(30)]));
				user.setId_card(String.valueOf((new Date()).getTime()+i));
				
				//测试测试工资金额
				//user.setSalary(Float.parseFloat(String.valueOf(ran.nextInt(9999)+1000)));
				
				if(i<90){
				user.setEmail("kk_8983"+i+"@163.com");
				}
				user.setTelphone(String.valueOf(1556857230+i));
				user.setUser_name(userName);
				if(i<90){
					user.setRole(roles[ran.nextInt(3)]);
				}
				
				session.save(user);
			}

			
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)tx.rollback();
		}finally{
			HibernateUtils.closeSession(session);
		}

	}
	private static String getName() {
		String[] name1 = new String[] { "孔", "张", "叶", "李", "叶入", "孔令", "张立",
				"陈", "刘", "牛", "夏侯", "令", "令狐", "赵", "母", "穆", "倪", "张毅", "称",
				"程", "王", "王志", "刘金", "冬", "吴", "马", "沈" };

		String[] name2 = new String[] { "凡", "课", "颖", "页", "源", "都", "浩", "皓",
				"西", "东", "北", "南", "冲", "昊", "力", "量", "妮", "敏", "捷", "杰",
				"坚", "名", "生", "华", "鸣", "蓝", "春", "虎", "刚", "诚" };

		String[] name3 = new String[] { "吞", "明", "敦", "刀", "备", "伟", "唯", "楚",
				"勇", "诠", "佺", "河", "正", "震", "点", "贝", "侠", "伟", "大", "凡",
				"琴", "青", "林", "星", "集", "财" };

		boolean two = ran.nextInt(50) >= 45 ? false : true;
		if (two) {
			String n1 = name1[ran.nextInt(name1.length)];
			String n2;
			int n = ran.nextInt(10);
			if (n > 5) {
				n2 = name2[ran.nextInt(name2.length)];
			} else {
				n2 = name3[ran.nextInt(name3.length)];
			}
			return n1 + n2;
		} else {
			String n1 = name1[ran.nextInt(name1.length)];
			String n2 = name2[ran.nextInt(name2.length)];
			String n3 = name3[ran.nextInt(name3.length)];
			return n1 + n2 + n3;
		}
	}
	
	
	public void sel02Test() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			String hql = "select p from Menu p where p.menu_id  " +
					"in (select DISTINCT m.parent_id from Menu m , User u , Role r " +
					"where u.user_id=17 and m.position='LEFT' and u.role.role_id=r.role_id and r in elements(m.roleSet))";
			Query query = session.createQuery(hql);
			List<Menu> tempList = query.list();
			for (Menu menu : tempList) {
				System.out.println("菜单名称：" + menu.getMenu_name() + "\tID:"
						+ menu.getMenu_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

}
