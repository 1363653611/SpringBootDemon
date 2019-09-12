<%@ page language="java" contentType="text/html; Charset=UTF-8"  import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <base href="<%=basePath%>"/>

    <title>SSE demon</title>


</head>

<body>
   <dev id="msgFromPush"></dev>
<script type="text/javascript" src="assets/js/jquery-3.2.1.js"></script>
<script type="text/javascript">

    if(!Window.EventSource){
//EventSource 只有新式浏览器才有（chrome\fireFox）
        var source = new EventSource('push');
        s ='';
        //浏览器监听服务器推送的技术
        source.addEventListener("message",function (e) {
            s+= e.data+"<br/>";
            $("#msgFromPush").html(s);
        });
        source.addEventListener('open',function (e) {
            console.log("链接打开。")
        },false);
    }else {
        console.log("你的浏览器不支持SSE.")
    }
</script>
</body>
</html>