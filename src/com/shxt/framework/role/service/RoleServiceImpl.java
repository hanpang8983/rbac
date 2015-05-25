package com.shxt.framework.role.service;

import java.util.List;

import com.shxt.base.dao.BaseDaoImpl;
import com.shxt.base.dao.IBaseDao;
import com.shxt.framework.menu.model.Menu;
import com.shxt.framework.role.model.Role;
@SuppressWarnings("unchecked")
public class RoleServiceImpl implements IRoleService {
	
	private IBaseDao baseDao ;
	

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Role> getStartRoleList() {
		return this.getRoleList("1");
	}
	
	public List<Role> getRoleAllList() {
		return this.getRoleList(null);
	}
	
	
	private List<Role> getRoleList(String role_status){
		String hql = "from Role r where 1=1 ";
		if(role_status!=null){
			hql += " and r.role_status='"+role_status+"'";
		}
		
		return (List<Role>) this.baseDao.list(hql);
	}

	public Role getRoleById(Integer role_id) {
		return (Role) this.baseDao.load(Role.class, role_id);
	}
	
	public void updateAuthorize(Integer role_id,Integer[] menuIds){
		Role role = (Role) this.baseDao.load(Role.class, role_id);
		
		//清空该角色下原有的错有菜单信息
		role.getMenuSet().clear();
		
		//新加入菜单
		if(menuIds!=null&&menuIds.length>0){
			for (Integer menu_id : menuIds) {
				Menu menu = (Menu) this.baseDao.load(Menu.class, menu_id);
				role.getMenuSet().add(menu);
			}
		}
		
		this.baseDao.update(role);
		
	}

}
