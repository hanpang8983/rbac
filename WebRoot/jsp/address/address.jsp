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
        select{
            width: 200px;
            height: 30px;
            font-size: 15px;
            font-weight: bold;
           margin-left: 5px;
        }


    </style>
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript">
        $(function(){
        	toLoadProvince();
        });
        //加载所有省份
        function toLoadProvince(){
        	
        	$.post("sys/toLoadProvinceDictAction.action",function(data){
        		// $("#province").html("")
        		for(var i=0;i<data.length;i++){
        			$("#province").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
        		}
        	})
        }
        
        //加载所有省份
        function toLoadCity(){
            
           var pro_id = $("#province").val();
           $("#city").html("<option value=''>请选择城市</option>");
           $.post("sys/toLoadCityDictAction.action",{pro_id:pro_id},function(data){
                for(var i=0;i<data.length;i++){
                    $("#city").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                }
            })
        }
    </script>
</head>
<body>

        <div class="reg">

         二级联动:
            <select id="province" onchange="toLoadCity()">
                <option value="">请选择省份</option>
            </select>
            <select id="city">
                <option value="">请选择城市</option>
            </select>



        </div>

</body>
</html>
