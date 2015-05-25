package com.shxt.framework.menu.service;

import java.util.List;

import com.shxt.base.dao.BaseDaoImpl;
import com.shxt.base.dao.IBaseDao;
import com.shxt.framework.menu.model.Menu;

@SuppressWarnings("unchecked")
public class MenuServiceImpl implements IMenuService {
	private IBaseDao baseDao ;
	

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Menu> getChildNodeByRoleId(Integer roleId) {
		String sql = "select m.* from web_sys_menu m,role_link_menu rlm where rlm.fk_menu_id=m.menu_id and rlm.fk_role_id=?";
		return (List<Menu>) this.baseDao.listSQL(sql, roleId, Menu.class, true);
	}

	public List<Menu> getParentNodeByRoleId(Integer roleId) {
		String sql = "select mm.* from web_sys_menu mm where mm.menu_id in( select DISTINCT m.parent_id from web_sys_menu m,role_link_menu rlm where rlm.fk_menu_id=m.menu_id and rlm.fk_role_id=?)";
		return (List<Menu>) this.baseDao.listSQL(sql, roleId, Menu.class, true);
	}

	public List<Menu> getSelectedMenuListByRoleId(Integer roleId) {
		String sql = "select m.* from web_sys_menu m,role_link_menu rlm where rlm.fk_menu_id=m.menu_id and rlm.fk_role_id=?";
		return (List<Menu>) this.baseDao.listSQL(sql, roleId, Menu.class, true);
	}

	public List<Menu> getUnSelectedMenuListByRoleId(Integer roleId) {
		String sql = "select mm.* from web_sys_menu mm where mm.menu_id not in " +
				"(select m.menu_id from web_sys_menu m,role_link_menu rlm where rlm.fk_menu_id=m.menu_id and rlm.fk_role_id=?)" +
				" and mm.parent_id is not null";
		return (List<Menu>) this.baseDao.listSQL(sql, roleId, Menu.class, true);
	}

}
