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
        //验证输入信息是否为空
        const verify_code =  $.trim($("#verifyId").val())
        const login_name = $.trim($("#loginname_inp").val())
        const password = $.trim($("#pwd_inp").val())
        if(verify_code=="" || login_name == "" || password == ""){
            alert("请输入完整的登录信息");
            return false;
        }
        // 获得form提交参数
        let params = $("#login_form").serializeArray();
        let obj = {};
        for(p in params){
          obj[params[p].name] = params[p].value;
        }
        const jsonStringRef = JSON.stringify(obj);  //json object string
        alert(jsonStringRef);
        $.ajax({
          type: "POST",
          data: jsonStringRef,
          url: '${pageContext.request.contextPath}/user/loginCheck?t='+new Date().getTime()+ '&verify_code=' + verify_code,
          contentType: "application/json",
          success:function(result){
            alert(typeof(result));
            alert(result.data.loginName + ":login " + result.message);
          }
        });
      })

      $("#refresh_a").click(function(){
          //加上随机时间 防止IE浏览器不请求数据
          let url = '${pageContext.request.contextPath}/generate?t='+ new Date().getTime();
          $('#randCodeImage').attr('src',url);
      })

      $("#register_btn").click(function(){
        const formData = new FormData($("#register_form")[0]);
        $.ajax({
            url:'${pageContext.request.contextPath}/user/register',
            data:formData,
            type: 'POST',
            processData:false,
            contentType:false,
            success:function(data){
              alert(data.message);
            },
            error: function(data) {
              alert("register fail");
            }
        });
      })

    })
  </script>
</head>
<body>
登录验证:
<form id="login_form" >
  用户名：<input type="text" id="loginname_inp" name="loginName"/>
  <br/>
  密码：<input type="text" id="pwd_inp" name="password"/>
  <br/>
  验证码：<input id="verifyId" type="text" name="verify_code" value=""/>
  <img id="randCodeImage" alt="验证码"  src="${pageContext.request.contextPath}/generate" width="90" height="30"/>
  <a href="javascript:void(0);" id="refresh_a">看不清，换一个</a>
  <br/>
  <input type="button" id="login_btn" value="login"/>
</form>
用户注册
<form id="register_form" action="#" enctype="multipart/form-data">
  用户名:<input type="text" name="loginName" value=""/>
  密码:<input type="text" name="password" value=""/>
  简历：<input id="fileupload" type="file" name="file"/>
  <input id="register_btn" type="button" value="注册"/>
</form>
</body>
</html>
