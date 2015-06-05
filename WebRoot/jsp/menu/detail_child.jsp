<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>节点详细信息</title>
		<link href="<%=path%>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
	</head>

	<body>

		<div class="formbody">
			<ul class="forminfo">
				<li>
					<label>
						菜单名称
					</label>
					<cite>${menu.menu_name}</cite>
				</li>
				<li>
                       <label>
                                                   访问路径
                       </label>
                       <cite>${menu.url}</cite>
                   </li>
                   <li>
                       <label>
                                                   所属位置
                       </label>
                       <cite>${menu.postion}</cite>
                   </li>
			</ul>
		</div>


	</body>

</html>