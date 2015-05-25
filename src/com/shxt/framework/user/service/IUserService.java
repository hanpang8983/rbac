package com.shxt.framework.user.service;

import com.shxt.base.dao.PageBean;
import com.shxt.framework.user.model.User;
import com.shxt.framework.user.query.UserQuery;

public interface IUserService {
	public User login(User user);
	public PageBean find(UserQuery query,PageBean pageBean);
	
	public void add(User user);
	public void update(User user);
	public User getUserById(Integer user_id);
	public void updateStatus(Integer user_id);
}
