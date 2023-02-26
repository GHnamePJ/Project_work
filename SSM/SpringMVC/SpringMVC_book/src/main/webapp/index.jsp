<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>电子书城系统</title>
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/json2.js"></script>
    <script>
        $(document).ready(function(){
            $("#select").click(function(){
                var uname=$("#uname").val();
                alert(uname)
                $.ajax({
                    url:"${pageContext.request.contextPath}/demo/"+uname,
                    type:"GET",
                    // contentType:"application/json",
                    dataType:"json",
                    success:function(responseText){
                        alert(responseText);
                        console.log(responseText)
                    },
                    error:function(){
                        alert("error");
                    }
                })
            });
        });
    </script>
</head>
<body>
<form id="select_userInfo">
    <h4>查询用户信息</h4>
    用户名：<input id="uname" type="text" name="uname" >
    <button id="select" type="submit">查询</button>
</form>
<form id="add_order">
    <h4>新增订单</h4>
    编&nbsp &nbsp号：<input id="Onum" type="text" name="Oname">
    <br><br>
    客户编号：<input id="uname2" type="text" name="uname">
    <br><br>
    订单日期：<input id="data" type="text" name="data">
    <br><br>
    <button id="add" type="submit">保存</button>
</form>
<script>

    // $("#add").click(function(){
    //     var param_array=$("#add_order").serializeArray();
    //     var Oname=param_array[0].value;
    //     var Uname=param_array[1].value;
    //     var data=param_array[2].value;
    //     // 将数据用json保存起来
    //     var json={"Oname":Oname,"Uname":Uname,"data":data};
    //     // 将json数据转换为json字符串
    //     var json_str=JSON.stringify(json);
    //     $.ajax({
    //         url:"demo.html",
    //         type:"POST",
    //         contentType:"application/json",
    //         dataType:"json",
    //         data:json_str,
    //         success:function(responseText){
    //             alter(responseText);
    //         },
    //         error:function(responseText){
    //             alert("error");
    //         }
    //     });
    // });
</script>

</body>
</html>
