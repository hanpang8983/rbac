<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
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
		<title>无标题文档</title>
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
			$.post("sys/addMenuAction.action", $("#menuForm").serialize(), function(data) {
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
							class="dfinput" />
						<i>标题不能超过20个字符</i>
					</li>
					<li>
						<label>
							图标选择
						</label>
						<cite> <input type="radio" name="menu.icon"
								value="icon01.png" checked="checked">
							<img alt="图标" src="<%=path%>/resource/menu/icon01.png">&nbsp;&nbsp;
							<input type="radio" name="menu.icon" value="icon02.png">
							<img alt="图标" src="<%=path%>/resource/menu/icon02.png">&nbsp;&nbsp;
							<input type="radio" name="menu.icon" value="icon03.png">
							<img alt="图标" src="<%=path%>/resource/menu/icon03.png">&nbsp;&nbsp;
							<input type="radio" name="menu.icon" value="icon04.png">
							<img alt="图标" src="<%=path%>/resource/menu/icon04.png">&nbsp;&nbsp;
						</cite>
					</li>
					<li>
						<label>
							&nbsp;
						</label>
						<input name="ddd" type="button" class="btn" value="确认保存"
							onclick="toSub();" />
					</li>
				</ul>
			</form>

		</div>


	</body>

</html>