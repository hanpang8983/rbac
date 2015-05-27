package com.shxt.framework.role.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shxt.base.action.BaseAction;
import com.shxt.base.exception.RbacException;
import com.shxt.base.utils.PropertiesConfigHelper;
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
	
	private String role_name;
	
	//针对于上传操作的
	private File photo;
	private String photoFileName;
	private String photoContentType;
	
	

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

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
	
	public String toAdd(){
		this.toJsp = "role/add";
		return REDIRECT;
	}
	
	public String add(){
		try {
			if(photo!=null){
				String path = ServletActionContext.getServletContext().getRealPath("/upload/role");
				
				String ext = FilenameUtils.getExtension(photoFileName);
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(10000)+"."+ext;
				
				File newFile = new File(path+"/"+saveName);
				newFile.createNewFile();
				
				FileUtils.copyFile(photo, newFile);
				role.setPhoto(saveName);
				
			}
			
			this.roleService.add(role);
			this.message="角色添加成功,谢谢合作!";
			this.flag = "success";
			
		} catch (Exception e) {
			e.printStackTrace();
			this.message="角色添加失败,异常信息为:"+e.getMessage();
			this.flag = "error";
			
		}
		this.toJsp = "message";
		return DISPATCHER;
		
	}
	
	
	public String toUpdate(){
		//获取数据
		role = this.roleService.getRoleById(role_id);
		this.toJsp = "role/update";
		return DISPATCHER;
	}
	
	public String update(){
		try {
			if(photo!=null){
				String path = ServletActionContext.getServletContext().getRealPath("/upload/role");
				
				String ext = FilenameUtils.getExtension(photoFileName);
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(10000)+"."+ext;
				
				File newFile = new File(path+"/"+saveName);
				newFile.createNewFile();
				
				FileUtils.copyFile(photo, newFile);
				role.setPhoto(saveName);
				
			}
			
			this.roleService.update(role);
			this.message="角色更新成功,谢谢合作!";
			this.flag = "success";
			
		} catch (Exception e) {
			e.printStackTrace();
			this.message="角色更新失败,异常信息为:"+e.getMessage();
			this.flag = "error";
			
		}
		this.toJsp = "message";
		return DISPATCHER;
		
	}
	
	public String delete(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.roleService.delete(role_id);
			map.put("flag", "success");
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message", e.getMessage());
		}
		
		this.jsonResult = map;
		
		return JSON;
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
	
	public String toCheck(){
		//验证
		Map<String, Object> map = new HashMap<String, Object>();
		Long count = this.roleService.getCheckRoleName(role_name);
		if(count>0){
			map.put("flag", "error");
			map.put("message", "该角色已经被使用，请重新输入");
		}else{
			map.put("flag", "success");
		}
		//转换
		this.jsonResult = map;
		return JSON;
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
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String roleName) {
		role_name = roleName;
	}

}
