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
    <title> | </title>
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
                    height:350,
                    title: '新建父节点页面',
                    url:'MenuParentUpdateServlet.do?menu_id='+menu_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
        
        /************************************************************/
        function toAddChildDialog(){
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:350,
                title: '新建子节点页面',
                url:'MenuChildAddServlet.do',//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
        	               
			var d = dialog({
			    align: 'top',
			    content: content,
			    quickClose: true// 点击空白处快速关闭
			});
			d.show(obj);
			
        }
        
        function toUpdateChildDialog(menu_id){
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:350,
                title: '新建子节点页面',
                url:'MenuChildAddServlet.do',//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
        
        function deleteChildDialog(menu_id){
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:350,
                title: '新建子节点页面',
                url:'MenuChildAddServlet.do',//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
                width:700,
                height:350,
                title: '新建子节点页面',
                url:'MenuChildAddServlet.do',//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
                <li class="click" onclick="toAddParentDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>新建父节点</li>
                <li class="click" onclick="toUpdateParentDialog()"><span><img src="<%=path %>/resource/admin/images/t02.png" /></span>编辑</li>
                <li><span><img src="<%=path %>/resource/admin/images/t03.png" /></span>删除</li>
                <li><span><img src="<%=path %>/resource/admin/images/t04.png" /></span>统计</li>
            </ul>
            <ul class="toolbar1">
               <li onclick="toAddChildDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>添加子菜单</li>
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
                        <a href="javascript:void(0)" onclick="toChildShowWin(this,'${child.menu_id}')">${child.menu_name}</a>&nbsp;&nbsp;|&nbsp;&nbsp;
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
