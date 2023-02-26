<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        function jumpToLogin(){
            window.location = "jsp/login.jsp";
        }
    </script>
</head>
<body>
<h2>UserController类测试</h2>
<button type="button" name="toLogin" onclick="jumpToLogin()">进入登录页面</button>
</body>
</html>