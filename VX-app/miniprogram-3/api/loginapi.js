//引入封装的reuest请求
const { getRequest, postRequest } = require("./https");
//基于业务封装的接口
module.exports = {
  /* 登录 */
  login: (data) => {
    return postRequest("qx/login", data);
  },

  /* 退出登录 */
  logout:(data)=>{
    return postRequest("qx/logout", data);
  }
};
