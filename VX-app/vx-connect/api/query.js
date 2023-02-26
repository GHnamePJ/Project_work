const {getRequest, postRequest} = require('./request')
// 进一步封装详细请求
module.exports ={
  // 查询角色
  queryRole:(data)=>{
    return postRequest("/userInfo",data)
  },
  //查询天气
  queryData:(data)=>{
    return postRequest("/qx/stations/date",data)
  }
}
