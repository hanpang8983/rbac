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
    <title>RBAC权限管理系统 | 新建角色 </title>
    <link href="<%=path%>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path%>/resource/admin/js/jquery.js"></script>
     <script type="text/javascript" src="<%=path%>/resource/common/upload_img.js"></script>
    <script type="text/javascript">
        function toSub(){
           var role_name = $.trim($("#role_name").val());
           
           if(role_name.length==0||role_name.length>20){
        	   alert("您输入的数据不合法，请重新输入");
        	   return false;
           }
           
           //验证上传的一定是图片格式
           var photo = $("#photo").val();
           if(photo.length>0){
        	   var flag = uploadValid(photo);
        	   if(!flag){
        		   alert("请上传正确的图片");
        		   $("#photo").val("");
        		   return false;
        	   }
           }
           
           //通过Ajax进行校验该角色名称是否已经被使用
           $.post("sys/toCheckRoleAction.action",{role_name:role_name},function(data){
        	   //判断 
        	   if(data.flag=="success"){//可以提交表单
        		   roleForm.submit();
        	   }else{
        		   $("#error").css("color","red");
        		   $("#error").html(data.message);
        		   $("#error").focus();
        		   return;
        	   }
           });
        }

    </script>
</head>

<body>

<div class="formbody">
    <form action="sys/addRoleAction.action" method="post" name="roleForm" enctype="multipart/form-data">
    <ul class="forminfo">
        <li><label>角色名称</label><input name="role.role_name" id="role_name" type="text" class="dfinput"/><i id="error">标题不能超过20个字符</i></li>
        <li><label>头像</label><input name="photo" id="photo" type="file" class="dfinput" /></li>
        <li><label>描述</label><textarea name="role.role_desc" id="role_desc" cols="" rows="" class="textinput"></textarea></li>
        <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="toSub();"/></li>
    </ul>
    </form>

</div>


</body>

</html>
