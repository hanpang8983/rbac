package com.shxt.base.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shxt.base.utils.PropertiesConfigHelper;

public class SessionFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//获取访问路径
		String path = request.getServletPath();
		
		HttpSession session = request.getSession();
		//排除监控的路径
		if(path.endsWith(PropertiesConfigHelper.getStringValue("LOGIN_ACTION"))
				||path.endsWith(PropertiesConfigHelper.getStringValue("LOGIN_JSP"))
				||path.endsWith(PropertiesConfigHelper.getStringValue("INDEX_JSP"))){
			//那么我不判断Session
			chain.doFilter(request, response);
		}else{
			if(session.getAttribute(PropertiesConfigHelper.getStringValue("HTTP_SESSION"))==null){
				//response.sendRedirect(request.getContextPath()+"/index.jsp");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.write("<script type='text/javascript'>alert('用户没有登录，请重新登录!');window.top.location.href='"+request.getContextPath()+"/index.jsp';</script>");
				
				out.flush();
				out.close();
			}else{
				chain.doFilter(request, response);
			}
		}
		

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
