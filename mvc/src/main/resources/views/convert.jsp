<%@ page language="java" contentType="text/html; Charset=UTF-8"  import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head>

    <title>Message convert demon</title>
    <meta http-equiv="Content-Type" content="text/html;Charset=UTF-8"/>
    <!--注意此处标签必须要以 </script> 结尾，否则出现莫名问题-->
    <script src="assets/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script>
        function req() {
            $.ajax({
                url:"convert",
                data:"1-zbcn",
                type:"POST",
                contentType:"application/x-zbcn",
                success:function (data) {
                    $("#resp").html(data)
                }
            });
        }
    </script>
</head>

<body>
    <dev id="resp"></dev>
    <input type="button" onclick="req();" value="请求"/>
</body>
</html>