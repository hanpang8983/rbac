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
    <title>二级联动</title>
     <title>智能提示</title>
    <style type="text/css">
        .reg{
            padding:10px;
            color:#CC0000;
            width:500px;
            height:400px;
            position:absolute;
            top:50%; left:50%;
            margin-left:-250px;
            margin-top:-200px;
            background:#fff;
            display:block;
        }
        input{
            width: 200px;
            height: 30px;
            font-size: 15px;
            font-weight: bold;

        }
        .myauto{
            padding-top: 1px;
            border: 1px solid #7C7C7C;
            width: 202px;
            height: 200px;
            z-index: 999;
            position: relative;
            margin-left: 72px;
            display: none;
        }
        .myauto span{
            display: block;
            height: 20px;
            padding-top: 3px;
        }
        .myauto .sel{
            background: #c7c7c7;
            font-weight: bold;
            cursor: pointer;
        }


    </style>
    
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript">
    
       
    
        function toKeyUp(){
        	$(".myauto").show();
        	$(".myauto").html("");
        	
        	//获取数据
        	var user_name=$.trim($("#user_name").val());
        	$.post("sys/autoUserNameDictAction.action",{user_name:user_name},function(data){
        		//$("#dd").html($("#dd").html()+data+"<br/>");
        		if(data!=null&&data.length>0){
        			for(var i=0;i<data.length;i++){
        				if(i==0){
        					$(".myauto").append("<span class='sel'>"+data[i].user_name+"</span>")
        				}else{
        					$(".myauto").append("<span>"+data[i].user_name+"</span>")
        				}
        			}
        		}
        		
        		$(".myauto span").hover(
        		function () {
				    $(this).addClass("sel");
				  },
				  function () {
				    $(this).removeClass("sel");
				  }
        		)
        		
        		$(".myauto span").click(function(){
        			//alert($(this).text())
        			$("#user_name").val($(this).text())
                    $(".myauto").hide();
        		});
        	});
        }
    </script>
</head>
<body>

        <div class="reg">

         智能提示:<input type="text" name="user_name" id="user_name" onkeyup="toKeyUp()">
            <div class="myauto" >
                <span class="sel">dfdf</span>
                <span>dfdf</span>
                <span>dfdf</span>
                <span>dfdf</span>
            </div>



        </div>
        <div id="dd"></div>
</body>
</html>
