// index.js
import { queryRole } from '../../api/query'
Component({
  properties: {
  },
  data: {
    screenwidth: "",
    user: []
  },
  methods: {
    //发送请求
    ToRequest: function () {
      console.log("点击按钮，发送请求");
      var that = this;
      var user = { "id": "1" }
      queryRole(user).then(res => {
        if (res.success === 1) {
          that.data.user = [];
          for (var i = 0; i < res.data.length; i++) {
            var newarray = {
              id: res.data[i].id,
              username: res.data[i].username,
              password: res.data[i].password
            };
            that.data.user = that.data.user.concat(newarray);
            that.setData({//只能存值
              user: that.data.user
            });
          };
        }else{
        }
      }).catch(err => {
      })
    },

  },
  lifetimes: {
    // 页面加载
    attached: function () {
      var screenwidth = wx.getSystemInfoSync().screenWidth
      console.log("1111")
      this.setData({
        screenwidth: screenwidth
      })
    }
  }



})
