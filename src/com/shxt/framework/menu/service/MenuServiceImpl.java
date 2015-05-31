package com.shxt.framework.menu.service;

import java.util.List;

import com.shxt.base.dao.BaseDaoImpl;
import com.shxt.base.dao.IBaseDao;
import com.shxt.base.exception.RbacException;
import com.shxt.framework.menu.dto.MenuDTO;
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
	
	public List<MenuDTO> getMenuListAll(){
		//获取所有的父节点信息
		String sql = "select * from web_sys_menu where parent_id is null order by menu_id asc";
		List<MenuDTO> parentMenuList = (List<MenuDTO>) this.baseDao.listSQL(sql, MenuDTO.class, false);
		//遍历父节点
		if(parentMenuList!=null&&parentMenuList.size()>0){
			for (MenuDTO parentMenu : parentMenuList) {
				sql = "select * from web_sys_menu where parent_id="+parentMenu.getMenu_id();
				parentMenu.setMenuList((List<MenuDTO>)this.baseDao.listSQL(sql, MenuDTO.class, false));
			}
		}
		
		return parentMenuList;
	}
	/*
	private List<MenuDTO> getNodeList(String sql){
		List<MenuDTO> menuList = (List<MenuDTO>) this.baseDao.listSQL(sql, MenuDTO.class, false);
		if(menuList!=null&&menuList.size()>0){
			for (MenuDTO parentMenu : menuList) {
				String child_sql = "select * from web_sys_menu where parent_id="+parentMenu.getMenu_id();
				parentMenu.setMenuList(this.getNodeList(child_sql));
			}
		}
		return menuList;
	}*/
	
	public void add(Menu menu){
		//判断改节点是否存在
		String hql = "select count(menu_id) from Menu where menu_name=? and parent_id is null";
		Long count = (Long) this.baseDao.query(hql, menu.getMenu_name().trim());
		if(count>0){
			throw new RbacException("该父节点菜单已经存在，请重新输入");
		}else{
			this.baseDao.add(menu);
		}
	}
	
	public void deleteParent(Integer menu_id){
		String hql = "select count(menu_id) from Menu where parent_id=?";
		Long count = (Long) this.baseDao.query(hql, String.valueOf(menu_id));
		if(count>0){
			throw new RbacException("该节点下存在["+count+"]个子节点，请先删除子节点才可以操作!");
		}else{
			hql = "delete from Menu where menu_id=?";
			this.baseDao.updateByHql(hql, menu_id);
		}
	}

	public void deleteChild(Integer menuId) {
		// 删除所有的中间表的子节点数据
		String sql = "delete from role_link_menu where fk_menu_id=?";
		this.baseDao.updateBySQL(sql, menuId);
		
		sql = "delete from web_sys_menu where menu_id=?";
		this.baseDao.updateBySQL(sql, menuId);
		
	}

}
