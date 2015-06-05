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
    <title> | </title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
     <script type="text/javascript">
        function toPage(type){
            var page_now = $("#page_now").val();
            var total_pages = $("#total_pages").val();
            
            switch(type){
              case "1"://首页
                  $("#page_now").val(1);
                  break;
              case "2":
                  if(parseInt(page_now)<=1){
                      alert("没有上一页了");
                      return false;
                  }
                  $("#page_now").val(parseInt(page_now)-1);
                  break;
              case "3":
                  if(parseInt(page_now)>=parseInt(total_pages)){
                      alert("没有下一页了");
                      return false;
                  }
                  
                  $("#page_now").val(parseInt(page_now)+1);
                  
                  break;
                  
              case "4"://尾页
                   $("#page_now").val($("#total_pages").val());
                  break;
              case "5"://查询
                   $("#page_now").val(1);
                  break;
            }
            
            searchForm.submit();
        }
    </script>
    
    <script type="text/javascript">
        $(function(){
        	$(".tablelist tbody tr ").click(function(){
        		$(this).find(":radio").prop("checked",true);
        	})
        })
        
        /**
         * 关于用户添加操作
         */
         function toAddDialog(){
        	//成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:700,
                title: '新建用户',
                url:'sys/toAddUserAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue=="success") {
                   // alert(this.returnValue);
                   //自动刷新
                   window.location.reload();
                }

            }
            });
            d.showModal();
         }
        /**
         * 关于用户的更新操作
         * */
         function toUpdateDialog(){
            //判断改用户选择
            var user_id = $("input[name='id']:checked").val();
            if(user_id){
            	//成功需要注意jquery的版本必须是1.7+以上
	            var d = top.dialog({
	                width:700,
	                height:700,
	                title: '新建用户',
	                url:'sys/toUpdateUserAction.action?user.user_id='+user_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
	                onclose: function () {
	                if (this.returnValue=="success") {
	                   // alert(this.returnValue);
	                   //自动刷新
	                   window.location.reload();
	                }
	
	            }
	            });
	            d.showModal();
            }else{// "" false null undefined 0
            	alert("请选中一条记录进行操作!");
                return;
            }
         }
        
        function toLogOffDialog(){
        	//判断改用户选择
            var radio = $("input[name='id']:checked");
        	
        	if(radio.val()){
        		
        		var session_user_id = '${session_user.user_id}';
        		
        		if(session_user_id==radio.val()){
        			alert("当前用户正在使用，不能进行该操作");
        			return false;
        		}
        		
        		
        		var status = $.trim(radio.parent().parent().find("span").text());
	            if(status=="可用"){
	                status="<font color='red'>禁用</font>"
	            }else{
	                status="<font color='red'>可用</font>"
	            }
	            var d = top.dialog({
	                title: '提示信息',
	                content: '你是否要对改账号进行'+status+'操作吗?',
	                okValue: '确定',
	                ok: function () {
	                    this.title('提交中…');
	                    var params = {
	                        user_id:radio.val()
	                    }
	                    $.post("sys/toUpdateStatusUserAction.action",params,function(data){
	                    	
	                    	if(data.flag=="success"){
	                    		alert(data.message);

	                    		if($(status).text()=="可用"){
	                    	      radio.parent().parent().find("span").css("color","green");
	                    	      radio.parent().parent().find("span").html($(status).text());
	                    		}else{
	                    	      radio.parent().parent().find("span").css("color","red");
	                    	      radio.parent().parent().find("span").html($(status).text());
	                    		}
	                    		
	                    		return;
	                    	}else{
	                    		alert(data.message);
	                    		return;
	                    	}
	                    });
	                   
	                },
	                cancelValue: '取消',
	                cancel: function () {}
	            });
	            d.showModal();
        	}else{
        		alert("请选中一条记录进行操作!");
                return;
        	}
        	
        	
        	
        	
        }
    </script>

</head>

<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">首页</a>
				</li>
			</ul>
		</div>
        <!-- 工具栏 -->
		<div class="tools" style="padding-top: 5px;">
		     <!-- 查询条件 -->
			<form action="sys/findUserAction.action" method="post"
				name="searchForm">
				<ul class="seachform">
					<li>
						<label>姓名</label>
						<s:textfield name="query.user_name" cssClass="scinput" />
					</li>
					<li>
						<label>性别</label>
						<s:select cssClass="select_show" list="#{'男':'男','女':'女'}"
							name="query.sex" headerKey="" headerValue="选择性别"></s:select>
					</li>

					<li>
						<label>&nbsp</label>
						<input type="button" class="scbtn" value="查询"
							onclick="toPage('5')" />
					</li>
				</ul>
				<s:hidden name="pageBean.totalPages" id="total_pages"></s:hidden>
				<s:hidden name="pageBean.pageNow" id="page_now"></s:hidden>
			</form>
			<!-- 其他操作 -->
	        <ul class="seachform1">
		        <li style="cursor: pointer;" onclick="toAddDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>新建</li>
		        <li style="cursor: pointer;" onclick="toUpdateDialog()"><span><img src="<%=path %>/resource/admin/images/t02.png" /></span>编辑</li>
		        <li style="cursor: pointer;" onclick="toLogOffDialog()"><span><img src="<%=path %>/resource/admin/images/t03.png" /></span>变更</li>
	        </ul>
		    
       </div>
	    

		<table class="tablelist">
	        <thead>
		        <tr>
			        <th></th>
			        <th>编号</th>
			        <th>姓名</th>
			        <th>账号</th>
			        <th>身份证号</th>
			        <th>性别</th>
			        <th>生日</th>
			        <th>联系方式</th>
			        <th>电子邮件</th>
			        <th>角色名称</th>
			        <th>状态</th>
		        </tr>
	        </thead>
        <tbody>
            <s:iterator value="pageBean.datas" var="user" status="st">
                <tr>
		            <td><input name="id" type="radio" value="<s:property value="#user.user_id"/>" /></td>
		            <td><s:property value="#st.count+((pageBean.pageNow-1)*pageBean.pageSize)"/></td>
		            <td><s:property value="#user.user_name"/></td>
		            <td><s:property value="#user.account"/></td>
		            <td><s:property value="#user.id_card"/></td>
		            <td><s:property value="#user.sex"/></td>
		            <td><s:property value="#user.birthday"/></td>
		            <td><s:property value="#user.telphone"/></td>
		            <td><s:property value="#user.email" escape="false" default="<font color='#CCC'>未知</font>"/></td>
		            <td><s:property value="#user.role.role_name" escape="false" default="<font color='red'>没有分配角色</font>"/></td>
		            <td>
		                  <s:if test="#user.account_status==1">
		                      <span style="color:green;font-weight:blod;">可用</span>
		                  </s:if>
		                  <s:else>
		                       <span style="color:red;font-weight:blod;">禁用</span>
		                  </s:else>
		            </td>
	            </tr> 
            </s:iterator>
	       
        
      
    
        </tbody>
    </table>
    
	     <div class="pagin">
	        <div class="message">共<i class="blue">${pageBean.totalCount}</i>条记录，当前显示第&nbsp;<i class="blue">${pageBean.pageNow}&nbsp;/${pageBean.totalPages}</i>页</div>
	        <ul class="paginList">
	        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('1')">首页</a></li>
	        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('2')">上一页</a></li>
	        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('3')">下一页</a></li>
	        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('4')">尾页</a></li>
	        </ul>
	    </div>
     </div>
     
   
    
 
       

    
    <script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
    </script>
    </body>
    </html>
