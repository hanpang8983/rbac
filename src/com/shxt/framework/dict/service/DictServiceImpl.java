package com.shxt.framework.dict.service;

import java.util.List;

import com.shxt.base.dao.BaseDaoImpl;
import com.shxt.base.dao.IBaseDao;
import com.shxt.framework.dict.model.AddressDict;
import com.shxt.framework.dict.model.UserDTO;
import com.shxt.framework.user.model.User;

public class DictServiceImpl implements IDictService {
	
	private IBaseDao baseDao = new BaseDaoImpl();

	public List<AddressDict> getProvinceList() {
		String sql = "select p.pro_id id,p.pro_name name , p.pro_code code  from province p";
		return (List<AddressDict>) this.baseDao.listSQL(sql, AddressDict.class, false);
	}
	
	public List<AddressDict> getCityListByProId(Integer pro_id ){
		String sql = "select c.city_id id,c.city_name name , c.city_code code,c.fk_pro_id fk_id  from city c where c.fk_pro_id=?";
		return (List<AddressDict>) this.baseDao.listSQL(sql, pro_id, AddressDict.class, false);
	}
	
	
	public List<User> getUserByName(String name ){
		String sql = "select u.user_id,u.user_name from web_sys_user u where 1=1 ";
		if(name!=null&&name.length()>0){
			sql += " and u.user_name like '"+name+"%'";
		}
		sql += " limit 5";
		return  (List<User>) this.baseDao.listSQL(sql,UserDTO.class, false);
	}
	

}
