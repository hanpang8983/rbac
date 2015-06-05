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
		<title>子节点更新</title>
		<link href="<%=path%>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/resource/admin/js/jquery.js"></script>
		<script type="text/javascript">
		function toSub() {
			var menu_name = $.trim($("#menu_name").val());
			if (menu_name.length == 0 || menu_name.length > 20) {
				alert("你输入的菜单名称不合法，请重新输入");
				$("#menu_name").val("");
				$("#menu_name").focus();
				return false;
			}
			var parent_id = $.trim($("#parent_id").val());

			if(parent_id.length==0){
				alert("请选择父节点菜单，认真点选择!");
                return false;
			}
			
			
			$.post("sys/updateChildMenuAction.action", $("#menuForm").serialize(), function(data) {
				if (data.flag == "success") {
					//关闭窗口，传递参数
					var dialog = top.dialog.get(window);
					dialog.close(data.flag);
					dialog.remove();
				} else {
					alert(data.message);
					$("#menu_name").focus();
					return false;
				}
			});
		}
</script>
	</head>

	<body>

		<div class="formbody">

			<form method="post" id="menuForm">
				<ul class="forminfo">
					<li>
						<label>
							菜单名称
						</label>
						<input name="menu.menu_name" id="menu_name" type="text"
							class="dfinput" value="${menu.menu_name}" />
						<i>标题不能超过20个字符</i>
					</li>
					<li>
						<label>
							父节点列表
						</label>
						<s:select list="parentNodeList" name="menu.parent_id" id="parent_id" cssClass="select_show"
						  listKey="menu_id" listValue="menu_name"
						  headerKey="" headerValue="请选择父节点"
						></s:select>
						<i>必须要选择哟!</i>
					</li>
					<li>
                        <label>
                                                    访问路径
                        </label>
                        <input name="menu.url" id="url" type="text"
                            class="dfinput" value="${menu.url}"/>
                    </li>
					<li>
						<label>
							&nbsp;
						</label>
						<input  type="button" class="btn" value="确认更新"
							onclick="toSub();" />
					</li>
				</ul>
				
				<input type="hidden" name="menu.menu_id" value="${menu.menu_id}">
				
			</form>

		</div>


	</body>

</html>