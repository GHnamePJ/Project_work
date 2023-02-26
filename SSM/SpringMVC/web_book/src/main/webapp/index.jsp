<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>电子书城</title>
    <script src="res/js/jquery-1.9.1.min.js"></script>
    <script src="res/js/json2.js"></script>
    <script>
        $(document).ready(function(){
            $("#tj").click(function(){
                var uname=$("#uname").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/demo/"+uname,//请求url
                    type: "GET",//请求方式
                    success:function(responseText){//请求成功的回调函数
                        if(responseText.length>1){
                            // alert(responseText);
                            var json=JSON.parse(responseText[0]);
                            var json1=JSON.parse(responseText[1]);
                            document.write("编号:"+json.onum+"<br\>用户编号:"+json.unum+"<br\>订单日期:"+json.date+"<br\><br\>编号:"+json1.onum+"<br\>用户编号:"+json1.unum+"<br\>订单日期:"+json1.date);
                        }else{
                            alert(responseText);
                        }
                    },
                    error:function(responseText){//请求失败的回调函数
                        alert(responseText);
                    },
                });
            });
            $("#add").click(function(){
                var param_array=$("#add_order").serializeArray();
                var onum=param_array[0].value;
                var unum=param_array[1].value;
                var date=param_array[2].value;
                // 将数据用json保存起来
                var json={"onum":onum,"unum":unum,"date":date};
                // 将json数据转换为json字符串
                var json_str=JSON.stringify(json);
                $.ajax({
                    url: "${pageContext.request.contextPath}/demo1",//请求url
                    type: "POST",
                    data: json_str,
                    contentType:"application/json",
                    dataType: "json",
                    success: function(responseText){//请求成功的回调函数
                        alert("已新增一条订单  编号：   "+responseText.onum+"  用户编号："+responseText.unum+"  订单日期："+responseText.date);
                    },
                    error:function(responseText){//请求失败的回调函数
                        alert(responseText);
                    }
                });
            });
        });
    </script>
</head>
<body>
    <h4>查询订单</h4>
    用户名:<input type="text" name="uname" id="uname">
    <br /><br />
    <input type="button" value="submit" id="tj">

    <form id="add_order">
        <h4>新增订单</h4>
        <%--id写小写，不然就找不到参数--%>
        编&nbsp &nbsp号：<input id="onum" type="text" name="onum">
        <br><br>
        客户编号：<input id="unum" type="text" name="unum">
        <br><br>
        订单日期：<input id="date" type="text" name="date">
        <br><br>
        <button id="add" type="button">保存</button>
        <%--button的type类型必须是button--%>
    </form>
</body>
</html>
