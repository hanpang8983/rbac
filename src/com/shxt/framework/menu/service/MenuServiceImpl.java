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
	
	public List<Menu> getLeftParentAllList(){
		try {
			String sql = "select menu_id,menu_name from web_sys_menu where parent_id is null and postion='LEFT'";
			return (List<Menu>) this.baseDao.listSQL(sql, Menu.class, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
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
	
	public void addParent(Menu menu){
		//判断改节点是否存在
		String hql = "select count(menu_id) from Menu where menu_name=? and parent_id is null";
		Long count = (Long) this.baseDao.query(hql, menu.getMenu_name().trim());
		if(count>0){
			throw new RbacException("该父节点菜单已经存在，请重新输入");
		}else{
			this.baseDao.add(menu);
		}
	}
	
	public void updateParent(Menu menu){
		//通过主键获取数据
		Menu old_menu = (Menu) this.baseDao.load(Menu.class, menu.getMenu_id());
		if(!old_menu.equals(menu.getMenu_name().trim())){
			String hql = "select count(*) from Menu where menu_name=? and parent_id is null and postion='LEFT'";//父节点查询重名
			Long count = (Long) this.baseDao.query(hql, menu.getMenu_name());
			if(count>0){
				throw new RbacException("该父节点已经被注册，请重新填写!");
			}
		}
		//更新操作
		old_menu.setIcon(menu.getIcon());
		old_menu.setMenu_name(menu.getMenu_name());
		
		//无用语句
		this.baseDao.update(old_menu);
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
	
	public void addChild(Menu menu){
		//对数据进行检查
		String hql = "select count(menu_id) from Menu where menu_name=? and parent_id=?";
		Long count = (Long) this.baseDao.query(hql, new Object[]{menu.getMenu_name().trim(),menu.getParent_id()});
		if(count>0){
			Menu parentMenu = (Menu) this.baseDao.load(Menu.class, Integer.parseInt(menu.getParent_id()));
			throw new RbacException("在["+parentMenu.getMenu_name()+"]下已经存在该名称了，请重新何时!");
		}else{
			this.baseDao.add(menu);
		}
	}
	
	public void updateChild(Menu menu){
		//这里需要做一系列的验证
		//获取现在的数据库中的信息
		Menu old_menu = (Menu) this.baseDao.load(Menu.class, menu.getMenu_id());
		if(!menu.getMenu_name().trim().equals(old_menu.getMenu_name())){
			String hql = "select count(menu_id) from Menu where menu_name=? and parent_id=?";
			Long count = (Long) this.baseDao.query(hql, new Object[]{menu.getMenu_name().trim(),menu.getParent_id()});
			if(count>0){
				throw new RbacException("该菜单已经存在，请重新输入!");
			}
		}else{
			//相同的情况下需要判断节点下是否相同
			if(!old_menu.getParent_id().equals(menu.getParent_id())){
				String hql = "select count(menu_id) from Menu where menu_name=? and parent_id=?";
				Long count = (Long) this.baseDao.query(hql, new Object[]{menu.getMenu_name().trim(),menu.getParent_id()});
				if(count>0){
					throw new RbacException("该菜单已经存在，请重新选择!");
				}
			}
		}
		
		old_menu.setMenu_name(menu.getMenu_name());
		old_menu.setParent_id(menu.getParent_id());
		//对url进行处理
		if(menu.getUrl()==null||menu.getUrl().trim().length()==0){
			old_menu.setUrl("javascript:void(0)");
		}else{
			old_menu.setUrl(menu.getUrl());
		}
		
		
		
		this.baseDao.update(old_menu);
	}

	public void deleteChild(Integer menuId) {
		// 删除所有的中间表的子节点数据
		String sql = "delete from role_link_menu where fk_menu_id=?";
		this.baseDao.updateBySQL(sql, menuId);
		
		sql = "delete from web_sys_menu where menu_id=?";
		this.baseDao.updateBySQL(sql, menuId);
		
	}
	
	public Menu load(Integer menu_id){
		return (Menu) this.baseDao.load(Menu.class, menu_id);
	}
	
	

}
