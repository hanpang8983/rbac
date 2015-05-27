package com.shxt.framework.role.service;

import java.util.List;

import com.shxt.framework.role.model.Role;

public interface IRoleService {
	/**
	 * 获取启用的角色列表
	 * @return
	 */
	public List<Role> getStartRoleList();
	/**
	 * 获取全部的角色列表
	 * @return
	 */
	public List<Role> getRoleAllList();
	
	public Role getRoleById(Integer role_id);
	
	public void updateAuthorize(Integer role_id,Integer[] menuIds);
	
	public Long getCheckRoleName(String role_name);
	
	public void add(Role role);
	public void update(Role role);
	public void delete(Integer role_id);

}
