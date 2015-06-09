package com.shxt.framework.user.service;

import java.util.List;

import com.shxt.base.dao.BaseDaoImpl;
import com.shxt.base.dao.IBaseDao;
import com.shxt.base.dao.PageBean;
import com.shxt.base.exception.RbacException;
import com.shxt.base.model.CharDatas;
import com.shxt.framework.user.model.User;
import com.shxt.framework.user.query.UserQuery;

public class UserServiceImpl implements IUserService {
	
	private IBaseDao baseDao ;
	
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public User login(User user){
		String hql = "from User u where u.account=? and u.password=?";
		return (User) this.baseDao.query(hql, new Object[]{user.getAccount().trim(),user.getPassword().trim()});
	}
	
	public User getUserById(Integer user_id){
		return (User) this.baseDao.load(User.class, user_id);
	}
	
	public PageBean find(UserQuery query,PageBean pageBean){
		
		String hql = "from User u where 1=1 ";
		if(query!=null){
			if(query.getUser_name()!=null&&query.getUser_name().trim().length()>0){
				hql += " and u.user_name like '"+query.getUser_name().trim()+"%'";
			}
			if(query.getSex()!=null&&query.getSex().trim().length()>0){
				hql += " and u.sex='"+query.getSex()+"'";
			}
			if(query.getRole_id()!=null&&query.getRole_id().trim().length()>0){
				if(!query.getRole_id().equals("-1")){
					hql += " and u.role.role_id="+query.getRole_id();
				}else{
					hql += " and u.role is null ";
				}
				
			}
			
			
		}
		
		hql += " order by u.user_id desc";
		
		System.out.println(hql);
		
		return this.baseDao.find(hql, pageBean);
	}

	public void add(User user) {
		try {
			this.baseDao.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(User user){
		//第一步先通过传递过来的主键获取数据库中的对象
		User oldUser = (User) this.baseDao.load(User.class, user.getUser_id());
		oldUser.setBirthday(user.getBirthday());
		if(user.getPhoto()!=null){
			oldUser.setPhoto(user.getPhoto());
		}
		oldUser.setEmail(user.getEmail());
		oldUser.setTelphone(user.getTelphone());
		oldUser.setId_card(user.getId_card());
		oldUser.setRole(user.getRole());
		oldUser.setSex(user.getSex());
		oldUser.setUser_name(user.getUser_name());
		
		
		this.baseDao.update(oldUser);
	}
	
	public void updateStatus(Integer user_id){
		User user = (User) this.baseDao.load(User.class, user_id);
		if(user.getAccount_status().equals("1")){
			user.setAccount_status("2");
		}else{
			user.setAccount_status("1");
		}
		this.baseDao.update(user);
	}
	
	public void checkAccount(String account) {
		String hql = "select count(user_id) from User where account=?";
		Long count = (Long) this.baseDao.query(hql, account);
		if(count>0){
			throw new RbacException("该账号已经被使用，请重新填写");
		}
		
	}
	
	public List<CharDatas> getCharDatas(){
		String sql = "select IFNULL(r.role_name,'无角色人员') label,count(u.user_id) value  from web_sys_user u LEFT JOIN web_sys_role r on u.fk_role_id=r.role_id group by r.role_name";
		
		return (List<CharDatas>) this.baseDao.listSQL(sql, CharDatas.class, false);
	}

	

}
