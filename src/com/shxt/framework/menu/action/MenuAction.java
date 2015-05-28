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
			for (MenuDTO menuDTO : menuList) {
				System.out.println(menuDTO.getMenu_name()+"--子节点个数"+menuDTO.getMenuList().size());
			}
		} catch (RbacException e) {
			e.printStackTrace();
		}
		this.toJsp = "menu/list";
		return DISPATCHER;
	}
	
	public String toAddParent(){
		
		this.toJsp = "menu/add";
		return REDIRECT;
	}
	
	public String add(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.menuService.add(menu);
			map.put("flag", "success");
			map.put("message", "添加父节成功，谢谢合作");
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message", e.getMessage());
		}
		this.jsonResult = map;
		return JSON;
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
