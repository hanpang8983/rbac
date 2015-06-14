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
		<title>子节点添加</title>
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
			
			
			$.post("sys/addChildMenuAction.action", $("#menuForm").serialize(), function(data) {
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
		
		function changeStatus(){
			if($("#is_open").val()=="off"){
				$(".js").hide();
				$(".url").show();
			}else{
				$(".js").show();
				$(".url").hide();
			}
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
							父节点列表
						</label>
						<s:select list="parentNodeList" name="menu.parent_id" id="parent_id" cssClass="select_show"
						  listKey="menu_id" listValue="menu_name"
						  headerKey="" headerValue="请选择父节点"
						></s:select>
						<i>必须要选择哟!</i>
					</li>
					<li class="url">
                        <label>
                                                    访问路径
                        </label>
                        <input name="menu.url" id="url" type="text"
                            class="dfinput" />
                    </li>
                    <li>
                        <label>
                                                    启用脚本
                        </label>
                        <select name="menu.is_open" id="is_open" class="select_show" onchange="changeStatus()">
			                 <option value="off">不启用</option>
			                 <option value="on">启用</option>
			            </select>
			            </li>
                    </li>
                    <li class="js" style="display: none;">
                        <label>
                                                    脚本方法名
                        </label>
                        <input name="menu.method" id="method" type="text"
                            class="dfinput" />
                    </li>
                    <li class="js"style="display: none;">
                        <label>脚本内容</label>
                        <textarea name="menu.js_script" id="js_script" cols="" rows="" class="textinput"></textarea></li>
					<li>
						<label>
							&nbsp;
						</label>
						<input  type="button" class="btn" value="确认保存"
							onclick="toSub();" />
					</li>
				</ul>
			</form>

		</div>


	</body>

</html>