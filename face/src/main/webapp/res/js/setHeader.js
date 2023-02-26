$(function () {
    $.ajax({
        beforeSend: function(request){
            request.setRequestHeader("token",sessionStorage.getItem("token"));
            request.setRequestHeader("phone",sessionStorage.getItem("phone"));
            request.setRequestHeader("name",sessionStorage.getItem("name"));
        },
        url: "../../user/verifyLogin",
        type: "GET",
        success: function (responseText) {//请求成功的回调函数
            if(responseText.success==true){
            }else{
            }
        },
        error: function (responseText) {//请求失败的回调函数
            $(window).attr("location","../../index.jsp");
        }
    });
});