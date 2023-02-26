// 引入env中的url(开发环境)
const { baseUrl } = require("./env.js").dev;
// 不携带token的url
const { exceptionAdd } = require("./env.js").exceptionTokenUrl;
//后端返回的成功状态码
const { codesuccess } = require("./env.js").code;
//保存token的键的名
const { key } = require("./env.js").Tokenkey;

//请求头处理函数
function CreateHeader(url) {
  let header = {};
  // if (type == 'POST_PARAMS'){
  //   header = {
  //     'content-type': 'application/x-www-form-urlencoded'
  //   }
  // }else{
  header = {
    "content-type": "application/x-www-form-urlencoded"
    //   }
  };
  // console.log("exceptionAdd:",exceptionAdd);
  // console.log("判断是否有重复登录（-1无）:", exceptionAdd[0].indexOf(url));
  // console.log("判断是否有重复注册（-1无）:", exceptionAdd[1].indexOf(url));
  // if (
  //   exceptionAdd[0].indexOf(url) === -1 &&
  //   exceptionAdd[1].indexOf(url) === -1
  // ) {
  //   //排除请求的地址不须要token的地址
  //   let token = wx.getStorageSync(key); //获取保存在本地的token
  //   header["Authorization"] = token; //将token添加到请求头
  // }
  return header;
}

/**
 * 二次封装wx.request
 * url:请求的接口地址
 * method:请求方式 GET,POST....
 *  data:要传递的参数
 *isSubDomain:表示是否添加二级子域名 true代表添加, false代表不添加
 */
function request(url, method, data) {
  // console.log("这是我封装的网络请求:", baseUrl);
  //这里使用ES6的写法拼接的字符串
  let header = CreateHeader(url); //请求头
  // console.log("header", header);
  let _url = `${baseUrl}/${url}`; //url
  console.log("URL:" + _url);

  return new Promise((resolve, reject) => {
    // wx.showLoading({
    //   title: "正在加载",
    // });

    // 发送网络请求
    wx.request({
      url: _url,
      data: data,
      header: header,
      method: method,
      // 响应成功 回调
      success: (res) => {
        // console.log("从接口获取到的数据:", res);
        let { success } = res.data;
        if (success === codesuccess) {
          resolve(res.data);
          // wx.hideLoading();
        } else {
          // wx.hideLoading();
          wx.showToast({
            title: res.data.msg,
            icon: "error",
          });
        }
      },
      // 响应失败 回调
      fail() {
        // wx.hideLoading();
        reject("接口有误，请检查");
      },
    });
  }).catch((error) => {});
}

//post请求，数据在body中
function postRequest(url, data) {
  return request(url, "POST", data);
}
//get请求，数据在body中
function getRequest(url, data) {
  return request(url, "GET", data);
}

module.exports.getRequest = getRequest;
module.exports.postRequest = postRequest;
