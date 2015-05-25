package com.shxt.base.action;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	/**
	 * 用于跳转到JSP资源
	 * */
	protected String toJsp;
	/**
	 * 用于跳转到Action资源的变量
	 * */
	protected String toAction;
	/**
	 * 用于提示信息
	 */
	protected String message;
	/**
	 * 用户判断是否成功的标识
	 * success代表成功
	 * error 代表失败
	 * */
	protected String flag;
	/**针对于JSP的请求转发-默认值*/
	protected String DISPATCHER = "dispatcher";
	/**针对于JSP的重定向*/
	protected String REDIRECT = "redirect";
	/**针对于Action跳转的请求转发*/
	protected String CHAIN = "chain";
	/**针对于Action跳转的重定向*/
	protected String REDIRECTACTION = "redirectAction";
	/**针对于JSON的跳转显示*/
	protected String JSON = "json";
	/**
	 * 用于传递JSON的数据格式
	 * @return
	 */
	protected Object jsonResult;
	
	

	public String getToJsp() {
		return toJsp;
	}

	public void setToJsp(String toJsp) {
		this.toJsp = toJsp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToAction() {
		return toAction;
	}

	public void setToAction(String toAction) {
		this.toAction = toAction;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Object getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Object jsonResult) {
		this.jsonResult = jsonResult;
	}
	

}
