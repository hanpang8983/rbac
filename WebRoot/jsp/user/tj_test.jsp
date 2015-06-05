<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>统计管理</title>
		<script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
		<script type="text/javascript"
			src="<%=path%>/resource/fusioncharts/fusioncharts.js">
		</script>
	</head>
	<body>
		<div id="mzl" style="height: 700px;"></div>
		<script type="text/javascript">
            //我需要一个json的字符串，获取是xml字符串
			//var json = '{"chart": {"yaxisname": "Sales Figure","caption": "Top 5 Sales Person","numberprefix": "$","useroundedges": "1","bgcolor": "FFFFFF,FFFFFF","showborder": "0"},"data": [{"label": "Alex","value": "25000"},{"label": "Mark","value": "35000"},{"label": "David","value": "42300"},{"label": "Graham","value": "35300"},{"label": "John","value": "31300"}]}';
			//alert(typeof(json))
			/*
			$.post("/sys/tjDataXMLUserAction.action",function(xml){
				var chart = new FusionCharts("Column2D.swf", "ChartId", "650", "400", "0", "0");
            
                chart.setDataXML(xml);
                chart.render("mzl");
				
			})*/
			
			
			$.post("<%=path%>/sys/tjDataJSONUserAction.action",function(json){
                
				alert(json);
				
				var chart = new FusionCharts("Pyramid.swf", "ChartId", "650", "400", "0", "0");
            
                chart.setJSONData(json);
                chart.render("mzl");
                
            })
			
			
			/*
			var chart = new FusionCharts("Column2D.swf", "ChartId", "650", "300", "0", "0");
			
			chart.setJSONData(json);
			chart.render("mzl");*/
</script>
	</body>

</html>
