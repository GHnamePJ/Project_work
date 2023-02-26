function hasToken(){
  const {key} = require('../api/env').Tokenkey
//判断是否存在token
  if (!wx.getStorageSync(key)) {
    wx.reLaunch({
      url: '/pages/login/login',
    })
  }

}

module.exports.hasToken = hasToken;