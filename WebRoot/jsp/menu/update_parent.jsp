<%@ page language="java" import="java.util.*"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib  prefix="s" uri="/struts-tags"%>
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
            $.post("sys/updateParentMenuAction.action", $("#menuForm").serialize(), function(data) {
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
                            class="dfinput" value="${menu.menu_name}"/>
                        <i>标题不能超过20个字符</i>
                    </li>
                    <li>
                        <label>
                                                        图标选择
                        </label>
                        <cite> 
                            <input type="radio" name="menu.icon"
                                value="icon01.png" 
                                <s:if test="menu.icon=='icon01.png'">checked="checked"</s:if>
                                >
                            <img alt="图标" src="<%=path%>/resource/menu/icon01.png">&nbsp;&nbsp;
                            <input type="radio" name="menu.icon" value="icon02.png"
                                <s:if test="menu.icon=='icon02.png'">checked="checked"</s:if>
                            >
                            <img alt="图标" src="<%=path%>/resource/menu/icon02.png">&nbsp;&nbsp;
                            <input type="radio" name="menu.icon" value="icon03.png"
                            <s:if test="menu.icon=='icon03.png'">checked="checked"</s:if>
                            >
                            <img alt="图标" src="<%=path%>/resource/menu/icon03.png">&nbsp;&nbsp;
                            <input type="radio" name="menu.icon" value="icon04.png"
                            <s:if test="menu.icon=='icon04.png'">checked="checked"</s:if>
                            >
                            <img alt="图标" src="<%=path%>/resource/menu/icon04.png">&nbsp;&nbsp;
                        </cite>
                    </li>
                    <li>
                        <label>
                            &nbsp;
                        </label>
                        <input name="ddd" type="button" class="btn" value="确认更新"
                            onclick="toSub();" />
                    </li>
                </ul>
                
                <!-- 隐藏域 -->
                <input type="hidden" name="menu.menu_id" value="${menu.menu_id}">
                <input type="hidden" id="old_menu_name" value="${menu.menu_name}">
                
            </form>

        </div>


    </body>

</html>