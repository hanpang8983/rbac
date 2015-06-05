package com.shxt.framework.menu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shxt.base.action.BaseAction;
import com.shxt.base.exception.RbacException;
import com.shxt.base.utils.PropertiesConfigHelper;
import com.shxt.framework.menu.dto.MenuDTO;
import com.shxt.framework.menu.model.Menu;
import com.shxt.framework.menu.service.IMenuService;
import com.shxt.framework.menu.service.MenuServiceImpl;
import com.shxt.framework.user.model.User;

public class MenuAction extends BaseAction {
	private List<Menu> parentNodeList;
	
	private List<Menu> childNodeList;
	
	private IMenuService menuService ;
	
	private List<MenuDTO> menuList;
	
	private Menu menu;
	
	private Integer menu_id;
	

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menuId) {
		menu_id = menuId;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public String loadLeftNav(){
		//--判断改用户是否拥有角色
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get(PropertiesConfigHelper.getStringValue("RBAC_SESSION"));
		if(user.getRole()!=null){
			
			//查询该角色下拥有的菜单信息
			this.childNodeList = menuService.getChildNodeByRoleId(user.getRole().getRole_id());
			
			//查询该角色下菜单对应的父节点信息
			this.parentNodeList = menuService.getParentNodeByRoleId(user.getRole().getRole_id());
		}
		
		//指定跳转的页面
		this.toJsp = "main/main";// -> /jsp/main/main.jsp
		//跳转到主界面
		return DISPATCHER;
	}
	
	public String list(){
		try {
			menuList = this.menuService.getMenuListAll();
		} catch (RbacException e) {
			e.printStackTrace();
		}
		this.toJsp = "menu/list";
		return DISPATCHER;
	}
	
	public String toAddParent(){
		
		this.toJsp = "menu/add_parent";
		return REDIRECT;
	}
	
	public String addParent(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.menuService.addParent(menu);
			map.put("flag", "success");
			map.put("message", "添加父节成功，谢谢合作");
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message", e.getMessage());
		}
		this.jsonResult = map;
		return JSON;
	}
	
	public String toUpdateParent(){
		
		this.menu = this.menuService.load(menu_id);
		
		this.toJsp = "menu/update_parent";
		return DISPATCHER;
	}
	
	public String updateParent(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.menuService.updateParent(menu);
			map.put("flag", "success");
			map.put("message", "更新父节成功，谢谢合作");
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message", e.getMessage());
		}
		this.jsonResult = map;
		return JSON;
	}
	
	public String deleteParent(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.menuService.deleteParent(menu_id);
			map.put("flag", "success");
			map.put("message", "删除父节成功，谢谢合作");
		} catch (RbacException e) {
			e.printStackTrace();
			map.put("flag", "error");
			map.put("message", e.getMessage());
		}
		this.jsonResult = map;
		
		return JSON;
	}
	
	
	public String toAddChild(){
		//需要左侧的列表
		this.parentNodeList = this.menuService.getLeftParentAllList();
		
		this.toJsp = "menu/add_child";
		return DISPATCHER;
	}
	
	public String addChild(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.menuService.addChild(menu);
			map.put("flag", "success");
			map.put("message", "添加子菜单成功，谢谢合作");
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message", e.getMessage());
		}
		this.jsonResult = map;
		return JSON;
	}
	
	public String toUpdateChild(){
		
		//需要左侧的列表
		this.parentNodeList = this.menuService.getLeftParentAllList();
		
		this.menu = this.menuService.load(menu_id);
		
		this.toJsp = "menu/update_child";
		return DISPATCHER;
	}
	
	public String updateChild(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.menuService.updateChild(menu);
			map.put("flag", "success");
			map.put("message", "更新子菜单信息成功，谢谢合作");
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message", e.getMessage());
		}
		this.jsonResult = map;
		return JSON;
	}
	
	
	
	public String deleteChild(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.menuService.deleteChild(menu_id);
			map.put("flag", "success");
			map.put("message", "删除子节点成功，谢谢合作");
		} catch (RbacException e) {
			e.printStackTrace();
			map.put("flag", "error");
			map.put("message", e.getMessage());
		}
		this.jsonResult = map;
		
		return JSON;
	}
	
	public String lookChild(){
		
		this.menu = this.menuService.load(menu_id);
		
		this.toJsp = "menu/detail_child";
		return DISPATCHER;
	}
	
	
	

	public List<Menu> getParentNodeList() {
		return parentNodeList;
	}

	public void setParentNodeList(List<Menu> parentNodeList) {
		this.parentNodeList = parentNodeList;
	}

	public List<Menu> getChildNodeList() {
		return childNodeList;
	}

	public void setChildNodeList(List<Menu> childNodeList) {
		this.childNodeList = childNodeList;
	}
	public List<MenuDTO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuDTO> menuList) {
		this.menuList = menuList;
	}
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
