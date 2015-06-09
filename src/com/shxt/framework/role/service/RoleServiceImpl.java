package com.shxt.framework.role.service;

import java.util.List;

import com.shxt.base.dao.BaseDaoImpl;
import com.shxt.base.dao.IBaseDao;
import com.shxt.base.exception.RbacException;
import com.shxt.framework.menu.model.Menu;
import com.shxt.framework.role.model.Role;

@SuppressWarnings("unchecked")
public class RoleServiceImpl implements IRoleService {

	private IBaseDao baseDao;

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Role> getStartRoleList() {
		return this.getRoleList("1");
	}

	public List<Role> getRoleAllList() {
		return this.getRoleList(null);
	}

	private List<Role> getRoleList(String role_status) {
		String hql = "from Role r where 1=1 ";
		if (role_status != null) {
			hql += " and r.role_status='" + role_status + "'";
		}

		return (List<Role>) this.baseDao.list(hql);
	}

	public Role getRoleById(Integer role_id) {
		return (Role) this.baseDao.load(Role.class, role_id);
	}

	public void updateAuthorize(Integer role_id, Integer[] menuIds) {
		Role role = (Role) this.baseDao.load(Role.class, role_id);
		// 清空该角色下原有的错有菜单信息
		role.getMenuSet().clear();
		// 新加入菜单
		if (menuIds != null && menuIds.length > 0) {
			for (Integer menu_id : menuIds) {
				Menu menu = (Menu) this.baseDao.load(Menu.class, menu_id);
				role.getMenuSet().add(menu);
			}
		}

		this.baseDao.update(role);

	}

	public Long getCheckRoleName(String role_name) {
		String hql = "select count(r.role_id) from Role r where r.role_name=?";
		return (Long) this.baseDao.query(hql, role_name.trim());
	}

	public void add(Role role) {
		this.baseDao.add(role);
	}

	public void update(Role role) {
		// 通过传递过来的数据，查询数据已有的数据
		Role old_role = (Role) this.baseDao.load(Role.class, role.getRole_id());
		old_role.setRole_name(role.getRole_name());
		old_role.setRole_desc(role.getRole_desc());
		// 判断
		if (role.getPhoto() != null) {
			old_role.setPhoto(role.getPhoto());
		}

		// 写这句话也是没有用的
		this.baseDao.update(old_role);
	}

	public void delete(Integer role_id) {
		Role role = (Role) this.baseDao.load(Role.class, role_id);
		// 第一步判断是否允许删除--异常
		if (role.getFlag().equals("2")) {
			throw new RbacException("该角色已经被锁定，无法进行注销操作!");
		} else {
			// 第二部更新数据库中的用户还有关联菜单
			// 删除中间表中的角色记录
			String sql = "delete from role_link_menu where fk_role_id=?";
			this.baseDao.updateBySQL(sql, role_id);
			sql = "update web_sys_user set fk_role_id=null where fk_role_id=?";
			this.baseDao.updateBySQL(sql, role_id);
			sql = "delete from web_sys_role where role_id=?";
			this.baseDao.updateBySQL(sql, role_id);
		}

	}

	public void changeStatus(Integer role_id) {
		String sql = "update web_sys_role set role_status = (case role_status	when '1' then '2' when '2' then	'1'	else '1' end) where role_id=?";
		this.baseDao.updateBySQL(sql, role_id);
	}

}
