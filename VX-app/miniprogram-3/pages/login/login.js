// pages/login/login.js
// 表单校验
import WxValidate from "../../utils/formValidation";
// 保存token键值对的键名
const {key} = require('../../api/env').Tokenkey
// 导入请求
import {
  login
} from "../../api/loginapi.js";
//后端返回的成功状态码
const { codesuccess } = require("../../api/env").code;

Page({
  /**
   * 页面的初始数据
   */
  data: {
    loginForm: {
      name: "",
      password: "",
    },
  },

  //报错信息（登录页面）
  showModal(error) {
    // 1.使用 id 选择器语法获取 toast 实例
    const toast = this.selectComponent("#my-toast");
    // 2.显示 Toast
    toast.linShow({
      title: error.msg,
    });
  },


  // 定义校验规则(登录页面)
  initValidate() {
    const rules = {
      name: {
        required: true,
        rangelength: [2, 20],
      },
      password: {
        required: true,
        rangelength: [5, 20],
      },
    };
    const messages = {
      name: {
        required: "用户名不能为空",
        rangelength: "用户名长度在2到20之间",
      },
      password: {
        required: "密码不能为空",
        rangelength: "密码长度在5到20之间",
      },
    };
    this.WxValidate = new WxValidate(rules, messages);
  },

  // 提交表单（登录页面）
  formSubmit: function (e) {
    // console.log("登录");
    console.log("表单数据：", e.detail.value);
    let params = e.detail.value;//表单数据
    let errors = this.WxValidate.checkForm(params);
    console.log(errors);
    //校验表单
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      // this.setData({
      // })
      return false;
    } else {
      // 执行request请求
      // 第一步：使用 id 选择器语法获取 toast 实例
      const toast = this.selectComponent("#my-toast");
      console.log("表单数据(添加):", params);
      // 发送网络请求
      login(params)
        .then((res) => {
          console.log(res);
          // 登录成功
          if (res.success === codesuccess) {
            toast.linShow({
              icon: "success",
              title: "登录成功",
            });
            // 登录成功后将 token存入本地
            wx.setStorageSync("token", res.id);
            //登录成功跳转首页面
            wx.switchTab({
              url: "../../pages/mdata/mdata",
              success: function (res) {
                // success
              },
              fail: function () {
                // fail
              },
              complete: function () {
                // complete
              },
            });
          } else if (!res.success === codesuccess) {
            //登录失败
            toast.linShow({
              icon: "error",
              title: "登录失败",
            });
          }
        })
        .catch();
    }
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 初始化校验规则
    this.initValidate();
    if (wx.getStorageSync(key)) {
      wx.switchTab({
        url: '/pages/mdata/mdata',
      })
    console.log("监听页面加载");
  }
},

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    console.log("监听页面初次渲染完成");
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {},

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {},

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {},

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {},

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {},
});