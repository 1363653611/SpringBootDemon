<%@ page language="java" contentType="text/html; Charset=UTF-8"  import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <base href="<%=basePath%>"/>

    <title>My JSP 'page.jsp' starting page</title>

</head>

<body>
    <pre>
        welcome to Spring MVC World!
    </pre>
</body>
</html>