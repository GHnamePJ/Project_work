import WxValidate from "../../utils/WxValidate"
const { changePassword } = require('../../api/mineapi')
const { codesuccess } = require('../../api/env.js').code
Page({
  /**
   * 页面的初始数据
   */
  data: {
    form: {
      password: "",
      new_password: "",
      confirm_password: ""
    },
    click_color1: "2rpx solid #bebebe",
    click_color2: "2rpx solid #bebebe",
    click_color3: "2rpx solid #bebebe",
    value:""
  },
  //监听input改变颜色
  focus_input_color(e) {
    var inputId = e.target.id
    // console.log("获取焦点id：" , e)
    if (inputId === "input1") {
      this.setData({
        click_color1: "2rpx solid blue;"
      })
    }
    if (inputId === "input2") {
      this.setData({
        click_color2: "2rpx solid blue;"
      })
    } else if (inputId === "input3") {
      this.setData({
        click_color3: "2rpx solid blue;"
      })
    } else {
    }
  },
  blur_input_color(e) {
    var inputId = e.target.id
    console.log("input内容:",e)
    console.log("获取失去焦点id：" + e.target.id)
    if (inputId === "input1") {
      this.setData({
        click_color1: "2rpx solid #bebebe;"
      })
    }
    if (inputId === "input2") {
      this.setData({
        click_color2: "2rpx solid #bebebe;"
      })
    } else if (inputId === "input3") {
      this.setData({
        click_color3: "2rpx solid #bebebe;"
      })
    } else {
    }
    //是否全部填写
    
  },
  // 改变按钮样式
  
  //密码框规则
  initValidate() {
    const rules = {
      password: {
        required: true,
        minlength: 5,
        maxlength: 20,
      },
      new_password: {
        required: true,
        minlength: 5,
        maxlength: 20,
      },
      confirm_password: {
        required: true,
        minlength: 5,
        maxlength: 20,
        equalTo: 'new_password'
      },
    }
    const messages = {
      password: {
        required: '请输入密码',
        minlength: "密码最少5位数",
        maxlength: '密码最多20位数',
      },
      new_password: {
        required: '请输入新密码',
        minlength: "密码最少5位数",
        maxlength: '密码最多20位数',
      },
      confirm_password: {
        required: '请确认密码',
        minlength: "密码最少5位数",
        maxlength: '密码最多20位数',
        equalTo: "两次密码输入不一致"
      },
    }
    this.WxValidate = new WxValidate(rules, messages)
  },
  //确认修改，最后验证
  change_password(e) {
    console.log(e)
    const params = e.detail.value
    //校验表单
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      console.log(error)
      wx.showToast({
        icon: "none",
        title: error.msg,
      })
      return false
    } else {
      //发送请求
      var password = e.detail.value.password
      var newpassword = e.detail.value.new_password
      var user = { "password": password, "newpassword": newpassword }
      changePassword(user).then(res => {
        console.log(codesuccess)
        if (res.code === codesuccess) {
          wx.showToast({
            title: "修改成功",
            icon: 'success',
          })
          setTimeout(function () {
            wx.switchTab({
              url: '/pages/mine/mine',
            })
          }, 500)
        }
      }).catch(err => {
        wx.showToast({
          title: "请求失败",
          icon: 'error',
          duration: 2000
        })
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //表单验证
    this.initValidate()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})