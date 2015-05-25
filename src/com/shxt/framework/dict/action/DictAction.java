package com.shxt.framework.dict.action;

import java.util.List;

import com.shxt.base.action.BaseAction;
import com.shxt.framework.dict.model.AddressDict;
import com.shxt.framework.dict.service.DictServiceImpl;
import com.shxt.framework.dict.service.IDictService;
import com.shxt.framework.user.model.User;

public class DictAction extends BaseAction {

	private String pro_id;
	
	private String user_name;

	public String toLoadProvince() {
		IDictService dictService = new DictServiceImpl();

		List<AddressDict> dataList = dictService.getProvinceList();
		System.out.println(dataList);
		this.jsonResult = dataList;

		return JSON;
	}

	public String toLoadCity() {
		IDictService dictService = new DictServiceImpl();

		List<AddressDict> dataList = dictService.getCityListByProId(Integer
				.parseInt(pro_id));
		System.out.println(dataList);
		this.jsonResult = dataList;
		return JSON;
	}
	
	public String autoUserName(){
		IDictService dictService = new DictServiceImpl();
		
		List<User> userList = dictService.getUserByName(user_name);
		
		this.jsonResult = userList;
		
		return JSON;
	}

	public String getPro_id() {
		return pro_id;
	}

	public void setPro_id(String proId) {
		pro_id = proId;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		user_name = userName;
	}
}
