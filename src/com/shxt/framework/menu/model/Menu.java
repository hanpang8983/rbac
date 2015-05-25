package com.shxt.framework.menu.model;

import java.io.Serializable;

public class Menu implements Serializable {
	private static final long serialVersionUID = -5661479708577098813L;
	/**主键ID*/
	private Integer menu_id;
	/**菜单名称*/
	private String menu_name;
	/**父节点ID*/
	private String parent_id;
	/**访问路径*/
	private String url;
	/**节点使用图标*/
	private String icon;
	/**默认左侧显示*/
	private String postion = "LEFT";
	/**默认*/
	private String target = "rightFrame";
	public Integer getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Integer menuId) {
		menu_id = menuId;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menuName) {
		menu_name = menuName;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parentId) {
		parent_id = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPostion() {
		return postion;
	}
	public void setPostion(String postion) {
		this.postion = postion;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	

}
