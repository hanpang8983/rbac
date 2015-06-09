<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>

    <script type="text/javascript">

        /**
         * 图片预览功能--没有封装
         *
         * @return {Boolean}
         */
        function setImagePreview() {

            var docObj = document.getElementById("photo");

            if (!docObj.value.match(/.jpg|.gif|.png|.bmp/i)) {
                alert('图片格式无效！');
                document.getElementById("photo").value = "";
                return false;
            }

            var imgObjPreview = document.getElementById("preview");
            if (docObj.files && docObj.files[0]) {
                // 火狐下，直接设img属性
                imgObjPreview.style.display = 'block';
                imgObjPreview.style.width = 'auto';
                imgObjPreview.style.height = 'auto';
                // imgObjPreview.src = docObj.files[0].getAsDataURL();

                // 火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
                imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);

            } else {
                // IE下，使用滤镜
                docObj.select();
                docObj.blur();
                var imgSrc = document.selection.createRange().text;
                var localImagId = document.getElementById("localImag");

                // 必须设置初始大小
                localImagId.style.width = "auto";
                localImagId.style.height = "auto";
                // 图片异常的捕捉，防止用户修改后缀来伪造图片
                try {
                    document.selection.empty();
                    localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    localImagId.filters
                            .item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                } catch (e) {
                    alert("您上传的图片格式不正确，请重新选择!");
                    return false;
                }
                imgObjPreview.style.display = 'none';
                document.selection.empty();
            }
            return true;
        }
         
        function autoWirte(){
            var id_card = $.trim($("#id_card").val());
           if(id_card.length==18){
               //通过身份号码给日期和性别赋值
                var year = id_card.substr(6, 4);
                var month = id_card.substr(10, 2);
                var day = id_card.substr(12, 2);

                var birthday = year+"-"+month+"-"+day;

                document.getElementById("birthday").value=birthday;

                var sex = id_card.substr(16, 1);
                var str = "";
                if(parseInt(sex)%2==1){
                    str = "男";
                }else{
                    str="女";
                }
                $("#sex").val(str);
           }
       }
         
       function toSub(){
           //对数据进行校验
           
           //对身份证号码暂时不做验证
           var id_card = $.trim($("#id_card").val());
           if(id_card.length==0||id_card.length<18){
               alert("请输入合法的身份证号码");
               return false;
           }
           
           var user_name = $.trim($("#user_name").val());
           if(user_name.length==0){
               alert("用户姓名，必须要填写呀！");
               return false;
           }
           
           //可以提交表单了，对于身份证和邮件应该还继续验证
           userForm.submit();
           
           
       }

    </script>
</head>

<body>

    <div class="mformbody">
        <form action="sys/updateUserAction.action" name="userForm" method="post" enctype="multipart/form-data">
        <ul class="mforminfo">
            <li><label>身份证号码</label>
            <s:textfield name="user.id_card" id="id_card" cssClass="mdfinput" cssStyle="width: 400px;" maxlength="18" onkeyup="autoWirte()"></s:textfield>
            <li><label>账号</label>
                <input name="user.account"  type="text" class="mdfinput" style="background: #ccc;" readonly="readonly"  value="${user.account}"/>
            </li>
            <li><label>姓名</label><input name="user.user_name" id="user_name" type="text" class="mdfinput" value="${user.user_name}"/></li>
            <li><label>邮件</label><input name="user.email" id="email" type="text" class="mdfinput" value="${user.email}"/></li>
            <li><label>联系方式</label><input name="user.telphone" id="telphone" type="text" class="mdfinput" value="${user.telphone}"/></li>
            <li><label>出生日期</label><input name="user.birthday" id="birthday" type="text" class="mdfinput" value="${user.birthday}"/></li>
            <li><label>性别</label>
                <s:select list="#{'男':'男','女':'女'}" cssClass="select_show" 
                cssStyle="width: 150px;" headerKey="" headerValue="请选择性别"
                    name="user.sex" 
                ></s:select>
              
            </li>
            <li style="width: 400px;"><label>角色</label>
                <s:select name="user.role.role_id" id="role_id" list="roleList" listKey="role_id" listValue="role_name" cssClass="select_show" headerKey="" headerValue="请选择角色" ></s:select>
            </li>
            <li style="width: 300px;height: 170px;"><label>头像</label>
                <!-- 按钮相关标签结构在下面的a标签中-->
                <a href="javascript:void(0);" style="display:block;width:100px;height:45px;position:relative;overflow:hidden;text-decoration:none;">
                    <!--下面的按钮就是所看到的按钮-->
                    <input type="button" class="uploadbtn" value="上传" >
                    <!--下面的file标签设置为完全透明覆盖在上面的按钮上，用户点击按钮其实就是点击的file标签，为了让鼠标在按钮上所有浏览器都显示小手，我们必须把file标签的预览按钮定位到按钮上，而且要足够大;注意：这里千万别给file标签加width样式，否则你的小手样式将无法兼容所有浏览器-->
                    <input type="file" name="photo" id="photo" style="height:45px;font-size:100px;position:absolute;cursor:pointer;top:0;right:0;filter:alpha(opacity=0);-moz-opacity:0;opacity:0;z-index:2;"  onchange="setImagePreview()">
                </a>
            </li>
            <li style="width: 350px;height: 170px;">
                <div id="localImag"><img id="preview" src="<%=path %>/upload/user/<s:property value="user.photo" default="guest.png"/>"/></div>
            </li>
            <li><label>&nbsp;</label><input  type="button" class="mbtn" value="确认更新" onclick="toSub()"/></li>
        </ul>
        <!-- 隐藏域 -->
                                                             <s:hidden name="user.user_id"></s:hidden>
        </form>

    </div>


</body>

</html>