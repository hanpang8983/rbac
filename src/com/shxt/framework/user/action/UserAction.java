package com.shxt.framework.user.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.shxt.base.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.base.exception.RbacException;
import com.shxt.base.utils.PropertiesConfigHelper;
import com.shxt.framework.role.model.Role;
import com.shxt.framework.role.service.IRoleService;
import com.shxt.framework.role.service.RoleServiceImpl;
import com.shxt.framework.user.model.User;
import com.shxt.framework.user.query.UserQuery;
import com.shxt.framework.user.service.IUserService;
import com.shxt.framework.user.service.UserServiceImpl;

public class UserAction extends BaseAction {
	
	private PageBean pageBean;
	
	private UserQuery query;
	
	private List<Role> roleList;
	
	private User user;
	
	private File photo;
	private String photoFileName;
	private String photoContentType;
	
	private Integer user_id;
	
	private String account;
	
	
	
	private IUserService userService;
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	private IRoleService roleService;
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
	public String find(){
		
		if(pageBean==null){
			pageBean = new PageBean();
		}
		
		//获取角色列表
		this.roleList = roleService.getStartRoleList();
		
		this.pageBean = userService.find(query,pageBean);
		
		this.toJsp = "user/list";
		
		return DISPATCHER;
	}
	/**
	 * 跳转到用户添加页面--需要传递数据角色列表
	 * @return
	 */
	public String toAdd(){
		try {
			//获取启用的角色列表
			roleList = roleService.getStartRoleList();
			
			this.toJsp="user/add";
		} catch (RbacException e) {
			e.printStackTrace();
		}
	
		return DISPATCHER;
	}
	
	public String add(){
		try {
			if(photo!=null){
				String path = ServletActionContext.getServletContext().getRealPath("/upload/user");
				
				String ext = FilenameUtils.getExtension(photoFileName);
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(10000)+"."+ext;
				
				File newFile = new File(path+"/"+saveName);
				newFile.createNewFile();
				
				FileUtils.copyFile(photo, newFile);
				
				user.setPhoto(saveName);
			}
			if(user.getRole().getRole_id()==null){
				user.setRole(null);
			}
			/**增加数据库中的默认密码*/
			user.setPassword("123456");
			/***/
			Map<String, Object> session = ActionContext.getContext().getSession();
			User loginUser = (User) session.get(PropertiesConfigHelper.getStringValue("RBAC_SESSION"));
			user.setCreate_name(loginUser.getUser_name());
			
			
			userService.add(user);
			this.message="用户添加成功,谢谢合作!";
			this.flag = "success";
			
		} catch (Exception e) {
			e.printStackTrace();
			this.message="用户添加失败,异常信息为:"+e.getMessage();
			this.flag = "error";
			
		}
		
		this.toJsp = "message";
		
		return DISPATCHER;
	}
	
	public String toUpdate(){
		user = userService.getUserById(user.getUser_id());
		
		//获取启用的角色列表
		roleList = roleService.getStartRoleList();
		
		this.toJsp = "user/update";
		return DISPATCHER;
	}
	
	public String update(){
		try {
			if(photo!=null){
				String path = ServletActionContext.getServletContext().getRealPath("/upload/user");
				
				String ext = FilenameUtils.getExtension(photoFileName);
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(10000)+"."+ext;
				
				File newFile = new File(path+"/"+saveName);
				newFile.createNewFile();
				
				FileUtils.copyFile(photo, newFile);
				
				user.setPhoto(saveName);
			}
			if(user.getRole().getRole_id()==null){
				user.setRole(null);
			}
			userService.update(user);
			this.message="用户更新成功,谢谢合作!";
			this.flag = "success";
			
		} catch (Exception e) {
			e.printStackTrace();
			this.message="用户更新失败,异常信息为:"+e.getMessage();
			this.flag = "error";
			
		}
		
		this.toJsp = "message";
		
		return DISPATCHER;
	}
	
	public String toUpdateStatus(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.updateStatus(user_id);
			map.put("flag", "success");
			map.put("message", "变更状态成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", "error");
			map.put("message", "变更状态失败，请联系管理员!");
		}
		
		this.jsonResult = map;
		return JSON;
	}
	
	public String checkAccount(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.checkAccount(account);
			map.put("flag", "success");
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message", e.getMessage());
		}
		System.out.println(map);
		this.jsonResult = map;
		return JSON;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public UserQuery getQuery() {
		return query;
	}

	public void setQuery(UserQuery query) {
		this.query = query;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer userId) {
		user_id = userId;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	//--------------------------测试统计-------------------------------------
	public String tjTest(){
		
		this.toJsp = "user/tj_test";
		return REDIRECT;
	}
	
	public String tjDataXML() throws IOException{
		//使用二期的知识完成传递
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//xml字符串
		StringBuilder sb = new StringBuilder();
		sb.append("<chart caption=\"Sales by salesperson\" subcaption=\"\" yaxisname=\"Sales\" numberprefix=\"$\" bgcolor=\"FFFFFF\" useroundedges=\"1\" showborder=\"0\">");
		sb.append("<set label=\"Rita\" value=\"82000\" />");
		sb.append("<set label=\"James\" value=\"72000\" />");
		sb.append("<set label=\"Jenny\" value=\"60000\" />");
		sb.append("</chart>");
		
		out.write(sb.toString());
		
		out.flush();
		out.close();
		
		return NONE;
	}
	
	//
	public String tjDataJSON() throws IOException{
		//使用二期的知识完成传递
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Map<String, Object> mapsx = new HashMap<String, Object>();
		mapsx.put("bgcolor","FFFFFF");
		mapsx.put("bgalpha", "100");
		mapsx.put("caption", "用户统计图");
		mapsx.put("numberSuffix", "人");
		mapsx.put("is2d", "1");
		mapsx.put("issliced", "1");
		mapsx.put("showplotborder", "1");
		mapsx.put("plotborderthickness", "1");
		mapsx.put("plotborderalpha", "100");
		mapsx.put("plotbordercolor", "FFFFFF");
		mapsx.put("enablesmartlabels", "1");
		mapsx.put("showBorder", "1");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("chart", mapsx);
		jsonMap.put("data", this.userService.getCharDatas());
		
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(jsonMap));
		out.write(gson.toJson(jsonMap));
		
		out.flush();
		out.close();
		
		return NONE;
	}

}
