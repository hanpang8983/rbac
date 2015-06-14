package com.shxt.framework.menu.dto;

import java.util.List;

public class MenuDTO {
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
	/**是否启用脚本模式*/
	private String is_open = "off";
	/**脚本代码*/
	private String js_script;
	/**方法名*/
	private String method;
	
	private List<MenuDTO> menuList;

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

	public List<MenuDTO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuDTO> menuList) {
		this.menuList = menuList;
	}

	public String getIs_open() {
		return is_open;
	}

	public void setIs_open(String isOpen) {
		is_open = isOpen;
	}

	public String getJs_script() {
		return js_script;
	}

	public void setJs_script(String jsScript) {
		js_script = jsScript;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
