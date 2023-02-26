<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="../js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/json2.js"></script>
    <script>
        $(document).ready(function(){
            $("#login_btn").click(function(){
                var param_array = $("#login_form").serializeArray();//把form表单序列化转换成数组
                var uname = param_array[0].value;
                var upwd = param_array[1].value;
                var obj_json= {"realName": uname, "password": upwd};//对应po类的字段名
                var obj_json_str = JSON.stringify(obj_json); //转换为JSON字符串
                $.ajax({
                    url: "${pageContext.request.contextPath}/check",//请求url
                    type: "POST",//请求方式
                    data: obj_json_str,//POST请求加data
                    contentType:"application/json",//请求参数类型
                    dataType: "json",//返回结果类型
                    success: function(responseText){//请求成功的回调函数
                       alert(responseText);
                    },
                    error:function(){//请求失败的回调函数
                        alert("error");
                    }
                });
            });
        });

    </script>
</head>
<body>
<form id="login_form">
    username:<input type="text" name="username" id="uname">
    <br />
    password:<input type="text" name="password" id="upwd">
    <br />
    <input type="button" value="submit" id="login_btn">
    <br />
</form>
</body>
</html>
