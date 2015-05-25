package com.shxt.framework.menu.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shxt.base.action.BaseAction;
import com.shxt.base.utils.PropertiesConfigHelper;
import com.shxt.framework.menu.model.Menu;
import com.shxt.framework.menu.service.IMenuService;
import com.shxt.framework.menu.service.MenuServiceImpl;
import com.shxt.framework.user.model.User;

public class MenuAction extends BaseAction {
	private List<Menu> parentNodeList;
	
	private List<Menu> childNodeList;
	
	private IMenuService menuService ;
	
	
	
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
	
	
}
