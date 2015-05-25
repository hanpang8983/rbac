package com.shxt.base.servlet;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.hibernate.util.PropertiesHelper;

import com.shxt.base.utils.PropertiesConfigHelper;
import com.shxt.framework.dict.model.AddressDict;

public class InitServlet extends HttpServlet {

	public void destroy() {

	}

	public void init() throws ServletException {
		PropertiesConfigHelper.load("rbac.properties");
	}

}
