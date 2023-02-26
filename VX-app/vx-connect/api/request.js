const { dev } = require('../api/env')
const { dev1 } = require('../api/env')
const { code } = require('./env')
//封装request 
function request(method, url, data) {
  return new Promise((resolve, reject) => {
    let header = {
      'content-type': 'application/json'
    };
    wx.request({
      url: dev1.baseUrl + url,
      method: method,
      header: header,
      data: data,
      success: (res) => {
        console.log("从接口获取到的数据:", res.data);
        var success = res.data.success;
        console.log(success)
        if (success === code.codeSuccess) {
          resolve(res.data);
        } else {
          wx.showToast({
            title: res.data.msg,
            icon: "error",
          });
        }
      },
      fail: (err) => {
        reject("接口有误，请检查！");
      }
    })
  })
}
//进一步封装request的post请求
function postRequest(url, data) {
  return request('POST', url, data)
}
//进一步封装request的get请求
function getRequest(url, data) {
  return request('GET', url, data)
}
module.exports = { request, postRequest, getRequest }
