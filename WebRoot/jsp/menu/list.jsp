<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title> 菜单管理 </title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <!-- 引入artDailog支持的库 -->
    <link rel="stylesheet" href="<%=path%>/resource/admin/artDialog/css/ui-dialog.css">
    <script src="<%=path%>/resource/admin/artDialog/dist/dialog-plus.js"></script>
    <script type="text/javascript">
        function toAddParentDialog(){
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:250,
                title: '新建父节点页面',
                url:'sys/toAddParentMenuAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
        
        function toUpdateParentDialog(){
            //获取选中的单选框的值
            
            if(!$("input[name='id']:checked").val()){
                alert("请选中一条记录进行操作!");
                return false;
            }else{
                var menu_id = $("input[name='id']:checked").val();
                
                //成功需要注意jquery的版本必须是1.7+以上
                var d = top.dialog({
                    width:700,
                    height:250,
                    title: '编辑菜单信息',
                    url:'sys/toUpdateParentMenuAction.action?menu_id='+menu_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
        }
        
        function deleteParentDialog(){
        	var menu_id = $("input[name='id']:checked").val();
        	if(menu_id){
        		//使用Ajax进行删除操作
        		if(window.confirm("您确定要删除该记录吗?")){
        			$.get("sys/deleteParentMenuAction.action",{menu_id:menu_id},function(data){
        				if(data.flag=="success"){
        					var radio  = $("input[name='id']:checked");
        					radio.parent().parent().hide("slow",function(){
        						$(this).remove();
        					});
        				}else{
        					alert(data.message);
        					return false;
        				}
        			});
        		}
        	}else{
        		alert("请选择一条记录进行删除操作");
        		return ;
        	}
        }
        
        /************************************************************/
        function toAddChildDialog(){
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:450,
                title: '新建子节点页面',
                url:'sys/toAddChildMenuAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
        
        function toChildShowWin(obj,menu_id){
        	var content = '<a href="javascript:void(0)" onclick="toUpdateChildDialog(\''+menu_id+'\')">编辑</a> &nbsp;&nbsp;|&nbsp;&nbsp;'
        	               +'<a href="javascript:void(0)" onclick="deleteChildDialog(\''+menu_id+'\')">删除</a> &nbsp;&nbsp;|&nbsp;&nbsp;' 
        	               +'<a href="javascript:void(0)" onclick="toLookChildDialog(\''+menu_id+'\')">查看</a>';
        	               
			var show_dialog = dialog({
				id:"show_dialog",
			    align: 'top',
			    content: content,
			    quickClose: true// 点击空白处快速关闭
			});
			show_dialog.show(obj);
			
        }
         function deleteChildDialog(menu_id){
              if(window.confirm("您确定删除该菜单信息吗?")){
                    $.get("sys/deleteChildMenuAction.action",{menu_id:menu_id},function(data){
                        if(data.flag=="success"){
                           var id = "#child_"+menu_id;
                           $(id).slideUp("slow",function(){
                               $(this).remove();
                           });
                          dialog.get("show_dialog").remove();
                        }else{
                            alert(data.message);
                            return false;
                        }
                    });
                }else{
                	dialog.get("show_dialog").remove();
                }
        }
        function toUpdateChildDialog(menu_id){
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:250,
                title: '编辑子节点页面',
                url:'sys/toUpdateChildMenuAction.action?menu_id='+menu_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
        
       
        
        function toLookChildDialog(menu_id){
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:400,
                height:150,
                title: '节点详细页面',
                url:'sys/lookChildMenuAction.action?menu_id='+menu_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
    </script>
</head>


<body>

    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
           <li>菜单管理 | 父节点管理</li>
        </ul>
    </div>
    
    <div class="rightinfo">
    
        <div class="tools">
            <ul class="toolbar">
                <li class="click" onclick="toAddParentDialog()" style="cursor: pointer;"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>新建父节点</li>
                <li class="click" onclick="toUpdateParentDialog()" style="cursor: pointer;"><span><img src="<%=path %>/resource/admin/images/t02.png" /></span>编辑</li>
                <li class="click" onclick="deleteParentDialog()" style="cursor: pointer;"><span><img src="<%=path %>/resource/admin/images/t03.png" /></span>删除</li>
            </ul>
            <ul class="toolbar1">
               <li class="click" onclick="toAddChildDialog()"  style="cursor: pointer;"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>添加子菜单</li>
            </ul>
        </div>
    
    
    <table class="tablelist">
        <thead>
            <tr>
                <th width="50"></th>
                <th width="50" >编号</th>
                <th>菜单名称</th>
                <th>图标</th>
                <th>子节点信息</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${menuList}" var="parent" varStatus="vs">
            <tr>
                <td><input name="id" type="radio" value="${parent.menu_id}" /></td>
                <td >${vs.count}</td>
                <td>${parent.menu_name}</td>
                <td>${parent.icon}</td>
                <td>
                     <c:forEach items="${parent.menuList}" var="child">
                        <a href="javascript:void(0)" id="child_${child.menu_id}" onclick="toChildShowWin(this,'${child.menu_id}')">${child.menu_name}</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                     </c:forEach>
                </td>
            </tr>
        </c:forEach>
        
      
        </tbody>
    </table>
    
    </div>
   
    <script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
     $(function(){
          $(".tablelist tbody tr ").click(function(){
              $(this).find(":radio").prop("checked",true);
          })
      })
    </script>
  
</body>

</html>
