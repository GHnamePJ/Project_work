//引入封装的reuest请求
const { getRequest, postRequest } = require("./request");
module.exports={
  // 修改头像
  setPhoto(data){
    return postRequest("user/photo",data);
  },
  //修改密码
  changePassword(data){
    return postRequest("user/change_password",data);
  }
}
