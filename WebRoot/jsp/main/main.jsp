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
    <title>信息管理系统界面</title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        * { padding:0; margin:0; }
        html, body { height:100%; border:none 0; }
    </style>
    <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <!-- 引入artDailog支持的库 -->
    <link rel="stylesheet" href="<%=path %>/resource/admin/artDialog/css/ui-dialog.css">
    <script type="text/javascript" src="<%=path %>/resource/admin/artDialog/dist/dialog-plus.js"></script>
    <!-- 暂时不启用 -->
    <!--
    <script type="text/javascript">
        $(function(){
            //顶部导航切换
            $(".nav li a").click(function(){
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>
    -->
    <script type="text/javascript">
        $(function(){
            //导航切换
            $(".menuson li").click(function(){
                $(".menuson li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function(){
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if($ul.is(':visible')){
                    $(this).next('ul').slideUp();
                }else{
                    $(this).next('ul').slideDown();
                }
            });
        })
        
        
    </script>


</head>
<body >
    <!--头部导航信息  开始-->
    <div style="background:url(<%=path %>/resource/admin/images/topbg.gif) repeat-x;height:88px;">
        <!-- Logo设置 -->
        <div class="topleft">
            <a href="login.html" target="_parent"><img src="<%=path %>/resource/admin/images/logo.png" title="系统首页" /></a>
        </div>
        <!-- Logo设置 结束-->

        <!-- 头部导航信息 -->
        <!--
        <ul class="nav">
            <li><a href="default.html" target="rightFrame" class="selected"><img src="<%=path %>/resource/admin/images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
            <li><a href="imgtable.html" target="rightFrame"><img src="<%=path %>/resource/admin/images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
            <li><a href="imglist.html"  target="rightFrame"><img src="<%=path %>/resource/admin/images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
            <li><a href="tools.html"  target="rightFrame"><img src="<%=path %>/resource/admin/images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
            <li><a href="computer.html" target="rightFrame"><img src="<%=path %>/resource/admin/images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
            <li><a href="tab.html"  target="rightFrame"><img src="<%=path %>/resource/admin/images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
        </ul>
        -->
        <!-- 头部导航信息 -->

        <div class="topright">
            <ul>
                <li><span><img src="<%=path %>/resource/admin/images/help.png" title="系统说明"  class="helpimg"/></span><a href="#">系统说明</a></li>
                <li><a href="javascript:void(0)">关于我们</a></li>
                <li><a href="javascript:void(0)" onclick="toLoginOut()" target="_top">安全退出</a></li>
                <script type="text/javascript">
                    function toLoginOut(){
                    	if(window.confirm("您确定要安全退出该系统吗？")){
                    		window.top.location.href="sys/loginOutLoginAction.action";
                    	}
                    	
                    }
                
                </script>
            </ul>

            <div class="user">
                <span>${session_user.account}</span>
                <!--  
                <i>消息</i>
                <b>5</b>
                -->
            </div>

        </div>

    </div>

    <!--头部导航信息  结束-->

    <!-- 主体部分 开始 -->
    <div style="height: 100%;">
        <!--左侧导航信息  开始-->
        <div style="background:#f0f9fd;width:187px;float: left;height: 100%">
            <div class="lefttop"><span></span>系统导航信息</div>
            <s:if test="#session.session_user.role==null">
                <h3 style="color:red">该用户未分配角色，请联系管理员</h3>
            </s:if>
            <s:else>
                <s:if test="#session.session_user.role.role_status==2">
                    <h3 style="color:red">该用户角色已禁用，请联系管理员</h3>
                </s:if>
                <s:else>
                    <dl class="leftmenu">
                        <s:if test="parentNodeList.size()>0">
                            <!-- 首先迭代父节点 -->
	                        <s:iterator value="parentNodeList" var="parent">
	                        <dd>
	                            <div class="title">
	                                <span><img src="<%=path %>/resource/admin/images/leftico01.png" /></span><s:property value="#parent.menu_name"/>
	                            </div>
	                            <ul class="menuson">
	                                <!-- 迭代子节点 -->
	                                <s:iterator value="childNodeList" var="child">
	                                    <!-- 比较父节点ID 关键步骤 -->
	                                    <s:if test="#parent.menu_id==#child.parent_id">
	                                          <li><cite></cite><a href="sys/<s:property value="#child.url"/>" target="<s:property value="#child.target"/>"><s:property value="#child.menu_name"/></a><i></i></li>
	                                    </s:if>
	                                </s:iterator>
	                                <!-- 激活状态 
	                                <li class="active"><cite></cite><a href="index.html" target="rightFrame">首页模版</a><i></i></li>
	                                -->
	                            </ul>
	                        </dd>
	                        </s:iterator>
                        </s:if>
                        <s:else>
                             <h3 style="color:red">没有操作菜单</h3>
                        </s:else>
	                    
	                </dl>
                </s:else>
                
            
            </s:else>
            


        </div>
        <!--左侧导航信息  结束-->

        <!-- 右侧DIV布局 -->
        <div>
            <!-- 使用iframe加载右侧内容 -->
            <iframe id="rightFrame" name="rightFrame" scrolling="no" frameborder="0"    onload="setIframeHeight('rightFrame');setIframeWidth('rightFrame')"   src="index.html"></iframe>
            <script type="text/javascript">
                window.dialog = dialog;
            </script>
        </div>

    </div>





    

    <!-- iframe自适应高度，Iframe显示不完整 -->
    <script type="text/javascript">
        function setIframeHeight(iframeId){
            var pTar = document.getElementById(iframeId);
            if (pTar) { //ff
                if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
                    pTar.height = pTar.contentDocument.body.offsetHeight;

                } //ie
                else if (pTar.Document && pTar.Document.body.scrollHeight) {
                    pTar.height = pTar.Document.body.scrollHeight;
                }

            }
        };
        function setIframeWidth(iframeId) {
            var pTar = document.getElementById(iframeId);

            if (pTar) { //ff
                if (pTar.contentDocument && pTar.contentDocument.body.offsetWidth) {
                    pTar.width = document.body.offsetWidth-187;
                } //ie
                else if (pTar.Document && pTar.Document.body.scrollWidth) {
                    pTar.width = document.body.scrollWidth-187;
                }
            }

        }
    </script>
</body>
</html>
