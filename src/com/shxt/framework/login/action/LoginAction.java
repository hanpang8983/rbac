package com.shxt.framework.login.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shxt.base.action.BaseAction;
import com.shxt.base.utils.PropertiesConfigHelper;
import com.shxt.framework.menu.model.Menu;
import com.shxt.framework.menu.service.IMenuService;
import com.shxt.framework.menu.service.MenuServiceImpl;
import com.shxt.framework.user.model.User;
import com.shxt.framework.user.service.IUserService;
import com.shxt.framework.user.service.UserServiceImpl;
/**
 * 主要完成登录和注销的Acton
 * @author 刘文铭
 *
 */
public class LoginAction extends BaseAction {
	private User user;
	private IUserService userService ;
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String login(){
		if(user==null){
			this.message="输入的不合法数据，请登录后操作";
			return LOGIN;
		}else{
			//调用Service
			
			user = userService.login(user);
			if(user!=null){
				//判断用户的状态是否可用
				if("2".equals(user.getAccount_status())){
					this.message="该账号已经被禁用，请联系管理员";
					return LOGIN;
				}else{
					//保存到Session范围内
					Map<String,Object> session = ActionContext.getContext().getSession();
					session.put(PropertiesConfigHelper.getStringValue("RBAC_SESSION"), user);
					ServletActionContext.getRequest().getSession().setAttribute(PropertiesConfigHelper.getStringValue("HTTP_SESSION"), user.getUser_id());
			
					this.toAction="loadLeftNavMenuAction";
					return REDIRECTACTION;
				}
				
			}else{
				this.message = "账号或者密码错误";
				return LOGIN;
			}
		}
		
		
	}
	
	public String loginOut(){
		//注销Session
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.remove(PropertiesConfigHelper.getStringValue("RBAC_SESSION"));
		
		HttpSession httpsession = ServletActionContext.getRequest().getSession();
		httpsession.removeAttribute(PropertiesConfigHelper.getStringValue("HTTP_SESSION"));
		httpsession.invalidate();
		
		return "loginout";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
