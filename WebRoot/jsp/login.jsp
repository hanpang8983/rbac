<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>欢迎登录后台管理系统</title>
<link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
<script src="<%=path %>/resource/admin/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
    $(function(){
        $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
        $(window).resize(function(){
            $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
        })
    });
    
    function toLogin(){
    	
    	return true;
    }
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(<%=path %>/resource/admin/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

    <!-- 漂浮云效果 开始-->
    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>
    <!-- 漂浮云效果 结束-->

    <!-- 顶部导航 -->
    <div class="logintop">
        <span>欢迎登录后台管理界面平台</span>
        <ul>
            <li><a href="javascript:void(0)">帮助</a></li>
            <li><a href="javascript:void(0)">关于</a></li>
        </ul>
    </div>
    <!-- 顶部导航 -->
    
    <div class="loginbody">
    
        <span class="systemlogo"></span>
       
        <div class="loginbox">
            <form action="sys/loginLoginAction.action" method="post" name="loginForm" onsubmit="return toLogin()">
            <ul>
                <li><input name="user.account" id="account" type="text" class="loginuser" placeholder="请输入账号"/></li>
                <li><input name="user.password" id="password" type="text" class="loginpwd" placeholder="请输入密码"/></li>
                <li>
                    <input type="submit" class="loginbtn" value="登录"  />
                    <label style="color: red;font-weight: bold;">
                        <!-- 可以用于错误提示功能 -->
                        ${message}
                        <!--
                        <input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a>
                        -->
                    </label>
                </li>
            </ul>
            </form>
        </div>
    
    </div>
    
    
    
    <div class="loginbm">&reg;2015  <a href="http://www.cnblogs.com/pangxiansheng/">胖先生</a><sup>&copy;</sup>  仅供学习交流，勿用于任何商业用途</div>
    
    
</body>

</html>
