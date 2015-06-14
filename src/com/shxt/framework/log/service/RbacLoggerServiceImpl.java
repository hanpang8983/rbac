package com.shxt.framework.log.service;

import com.shxt.base.dao.IBaseDao;
import com.shxt.framework.log.model.LogonLog;

public class RbacLoggerServiceImpl implements IRbacLoggerService {
	
	private IBaseDao baseDao ;
	
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void addLogonLog(LogonLog logonLog) {
		
		this.baseDao.add(logonLog);

	}



	

}
