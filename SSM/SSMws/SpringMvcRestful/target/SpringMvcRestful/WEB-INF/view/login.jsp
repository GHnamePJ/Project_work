<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/4/18
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <script src="${pageContext.request.contextPath}/res/js/jquery-1.9.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/res/js/json2.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){
      //登录按钮事件，检查用户登录是否成功
      $("#login_btn").click(function(){
        //获得form提交参数
        var params = $("#loginform").serializeArray();
        var obj = {};
        for(p in params){
          obj[params[p].name] = params[p].value;
        }
        var jsonStringRef = JSON.stringify(obj);
        $.ajax({
          type: "POST",
          data: jsonStringRef,
          url: '${pageContext.request.contextPath}/user/getList',
          contentType: "application/json",
          success:function(result){
            alert(result);
            //可跳转至静态页面，如果访问JSP页面需要请求controller
            window.location = '${pageContext.request.contextPath}/res/html/success.html';
          }
        });
      })
      //搜索按钮点击事件，查询指定用户名的信息
      $("#search_btn").click(function(){
          //get请求方式，请求参数置于url后
          var params = $("#searchform").serializeArray();
          var str = params[0].value;
          //url中的项目路径使用单引号和后面由于有请求参数变量，需要使用双引号使用
          $.ajax({
            type: "GET",
            url: '${pageContext.request.contextPath}/user/getList/'+str,
            // contentType: "application/x-www-form-urlencoded",
            dataType: "json", //后端为JSON字符串，前端指定为json，则返回json对象，若不指定，则由jQuery 将自动根据 HTTP 包 MIME 信息来智能判断
            success:function(result){
              alert(typeof(result));
              if(typeof(result) == "object"){
                alert(result.loginName);
              } else if(typeof(result) == "string") {
                alert(result);
              }
            }
          });
          <%--$.ajax({--%>
          <%--  type: "GET",--%>
          <%--  url: '${pageContext.request.contextPath}/user/getList/' + encodeURIComponent(str),--%>
          <%--  contentType: "application/x-www-form-urlencoded",--%>
          <%--  //dataType: "json", //后端为JSON字符串，前端指定为json，则返回json对象，若不指定，则由jQuery 将自动根据 HTTP 包 MIME 信息来智能判断--%>
          <%--  success:function(result){--%>
          <%--    alert(typeof(result));--%>
          <%--    if(typeof(result) == "object"){--%>
          <%--      alert(result.loginName);--%>
          <%--    } else if(typeof(result) == "string") {--%>
          <%--      alert(result);--%>
          <%--    }--%>
          <%--  }--%>
          <%--});--%>
      });
    })
  </script>
</head>
<body>
登录验证:
<form id="loginform" >
  <input type="text" name="loginName"/>
  <input type="text" name="password"/>
  <input type="button" id="login_btn" value="login"/>
</form>
<br/>
用户信息查询 :
<form id="searchform">
  <input type="text" name="loginName"/>
  <input type="button" id="search_btn"  value="search"/>
</form>
</body>
</html>
