package com.shxt.framework.role.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.shxt.framework.menu.model.Menu;
/**
 * 
 * @author 刘文铭
 *
 */
public class Role implements Serializable {
	/**角色的主键ID*/
	private Integer role_id;
	/**角色的名称，不允许重复*/
	private String role_name;
	/**1代表是可用，2代表禁用，3代表删除*/
	private String role_status ="1";
	/**1代表不锁定，2代表锁定，锁定之后不能进行禁用和删除操作*/
	private String flag = "1";
	/**角色的描述，职能描述*/
	private String role_desc;
	/**角色的头像,如果头像不存在显示该图片*/
	private String photo;
	
	
	private Set<Menu> menuSet;
	
	public Role(){
		menuSet = new HashSet<Menu>();
	}
	
	
	
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String roleName) {
		role_name = roleName;
	}
	public String getRole_status() {
		return role_status;
	}
	public void setRole_status(String roleStatus) {
		role_status = roleStatus;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRole_desc() {
		return role_desc;
	}
	public void setRole_desc(String roleDesc) {
		role_desc = roleDesc;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public Set<Menu> getMenuSet() {
		return menuSet;
	}



	public void setMenuSet(Set<Menu> menuSet) {
		this.menuSet = menuSet;
	}
	
	

}
