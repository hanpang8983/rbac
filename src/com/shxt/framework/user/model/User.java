package com.shxt.framework.user.model;

import java.util.Date;

import com.shxt.framework.role.model.Role;

public class User implements java.io.Serializable{
	/**主键ID*/
	private Integer user_id;
	/**账号，不允许重复，不允许为空*/
	private String account;
	/**密码，不允许为空*/
	private String password;
	/**用户的姓名*/
	private String user_name;
	/**2种: 男 女 通过身份证号码自动获取*/
	private String sex;
	/**身份证号码 18位*/
	private String id_card;
	/**出生日期-通过身份号码自动获取*/
	private String birthday;
	/**账号的创建日期*/
	private Date create_date = new Date();
	/**账号创建者*/
	private String create_name;
	/**1:可用  2:禁用  3:彻底删除 */
	private String account_status = "1";
	/**右侧显示的界面*/
	private String home_page="content.jsp" ;
	/**1:允许删除 2:不允许删除*/
	private String del_flag = "1";
	/**解禁日期*/
	private Date stop_date;
	/**电子邮件*/
	private String email;
	/**联系方式*/
	private String telphone;
	/**照片*/
	private String photo;
	
	/**多对一关系角色 字段名字交 fk_role_id*/
	private Role role;
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String idCard) {
		id_card = idCard;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date createDate) {
		create_date = createDate;
	}
	public String getCreate_name() {
		return create_name;
	}
	public void setCreate_name(String createName) {
		create_name = createName;
	}
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String accountStatus) {
		account_status = accountStatus;
	}
	public String getHome_page() {
		return home_page;
	}
	public void setHome_page(String homePage) {
		home_page = homePage;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String delFlag) {
		del_flag = delFlag;
	}
	public Date getStop_date() {
		return stop_date;
	}
	public void setStop_date(Date stopDate) {
		stop_date = stopDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
