package com.shxt.framework.dict.service;

import java.util.List;

import com.shxt.framework.dict.model.AddressDict;
import com.shxt.framework.user.model.User;

public interface IDictService {
	
	public List<AddressDict> getProvinceList();
	public List<AddressDict> getCityListByProId(Integer pro_id );
	public List<User> getUserByName(String name );
}
