package com.shxt.framework.menu.service;

import java.util.List;

import com.shxt.framework.menu.model.Menu;

public interface IMenuService {
	/**
	 * 通过角色获取该角色下对应的菜单信息
	 * @param role_id
	 * @return
	 */
	public List<Menu> getChildNodeByRoleId(Integer role_id); 
	/**
	 * 通过角色获取拥有菜单信息对应的父节点信息
	 * @param role_id
	 * @return
	 */
	public List<Menu> getParentNodeByRoleId(Integer role_id); 
	
	/**
	 * 通过角色获取该角色下拥有的菜单信息
	 * @param role_id
	 * @return
	 */
	public List<Menu> getSelectedMenuListByRoleId(Integer role_id); 
	/**
	 * 通过角色获取该角色下未拥有的菜单信息
	 * @param role_id
	 * @return
	 */
	public List<Menu> getUnSelectedMenuListByRoleId(Integer role_id); 
	
	
	
}
