package com.shxt.framework.log.model;

import java.io.Serializable;
import java.util.Date;

public class LogonLog implements Serializable {
	
	private Integer logon_id;
	private String ip;
	private String account;
	private String user_name;
	private Date login_time;
	public Integer getLogon_id() {
		return logon_id;
	}
	public void setLogon_id(Integer logonId) {
		logon_id = logonId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date loginTime) {
		login_time = loginTime;
	}
	
	

}
