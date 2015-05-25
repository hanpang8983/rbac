package com.shxt.framework.role.action;

import java.util.List;

import com.shxt.base.action.BaseAction;
import com.shxt.framework.menu.model.Menu;
import com.shxt.framework.menu.service.IMenuService;
import com.shxt.framework.menu.service.MenuServiceImpl;
import com.shxt.framework.role.model.Role;
import com.shxt.framework.role.service.IRoleService;
import com.shxt.framework.role.service.RoleServiceImpl;

public class RoleAction extends BaseAction {
	
	private List<Role> roleList;
	
	private Role role;
	
	private List<Menu> selectedMenuList;
	private List<Menu> unSelectedMenuList;
	
	private Integer role_id;
	
	private Integer[] menuIds;
	
	private IRoleService roleService ;
	private IMenuService menuService ;
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public String list(){
		roleList = this.roleService.getRoleAllList();
		this.toJsp = "role/list";
		return DISPATCHER;
	}
	
	public String toAuthorize(){
		try {
			//获取该角色拥有的菜单
			selectedMenuList = menuService.getSelectedMenuListByRoleId(role_id);
			//获取该角色没有拥有的菜单不能包含父节点
			unSelectedMenuList = menuService.getUnSelectedMenuListByRoleId(role_id);
			//传递角色信息
			role = roleService.getRoleById(role_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		this.toJsp = "role/authorize";
		return DISPATCHER;
	}
	
	public String updateAuthorize(){
		//TODO:
		try {
			
			this.roleService.updateAuthorize(role.getRole_id(), menuIds);
			
			this.message="角色更新数据成功,谢谢合作!";
			this.flag = "success";
			
		} catch (Exception e) {
			e.printStackTrace();
			this.message="角色更新数据失败,异常信息为:"+e.getMessage();
			this.flag = "error";
			
		}
		
		this.toJsp = "message";
		
		return DISPATCHER;
	}
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}

	public List<Menu> getSelectedMenuList() {
		return selectedMenuList;
	}

	public void setSelectedMenuList(List<Menu> selectedMenuList) {
		this.selectedMenuList = selectedMenuList;
	}

	public List<Menu> getUnSelectedMenuList() {
		return unSelectedMenuList;
	}

	public void setUnSelectedMenuList(List<Menu> unSelectedMenuList) {
		this.unSelectedMenuList = unSelectedMenuList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(Integer[] menuIds) {
		this.menuIds = menuIds;
	}

}
