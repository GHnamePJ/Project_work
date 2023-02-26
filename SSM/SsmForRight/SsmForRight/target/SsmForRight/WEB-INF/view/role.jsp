<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/res/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/json2.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#search_btn").click(function () {
                //get请求方式，请求参数置于url后
                var params = $("#searchform").serializeArray();
                var str = params[0].value;
                if(str == "") {
                    alert("请输入查询关键字");
                    return false;
                }
                //当前请求返回结果是json对象
                $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/role/getRole/' + str,
                    contentType: "application/x-www-form-urlencoded",
                    success:function(result){ //返回JSON对象，但JSON对象下的属性无法再识别是否为对象，均为字符串
                        alert("result: " + typeof(result));
                        alert("result.data: " + typeof(result.data));
                        alert("角色名：" + result.data.name);
                    }
                });
            })
        })
    </script>
</head>
<body>
    <form id="searchform">
        <input type="text" id="id_inp" name="roleId"/>
        <input type="button" id="search_btn" value="查询角色"/>
    </form>

</body>
</html>
