<%@ page language="java" contentType="text/html; Charset=UTF-8"  import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <base href="<%=basePath%>"/>

    <title>My JSP 'vera.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <style  type="text/css">
        html {
            margin: 0px;
            padding: 0px 0px 0px 5px;
        }
        body {
            color: #3C3C3C;
            background-color:#FFFFFF;
            font-family: Verdana, Arial, Helvetica, sans-serif;
            margin: 0px;
            padding: 0px;
        }
        div{
            display: block;
        }
        div.center{
            max-width:1140px;
            min-width:960px;
            margin:0pt auto;
            z-index:99
        }
        div.center{
            width:910px;
            min-width:800px;
        }
    </style>
</head>

<body>
    <pre>
        welcome to Spring MVC World!
    </pre>
</body>
</html>