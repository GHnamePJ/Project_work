// components/query-data/query-data.js
import {
  queryData
} from '../../api/query'
Component({
  data: {
    //天气因素

    //是否有数据
    data: false,
    // 展示的气象数据
    pageWeather: [],
    // 气象数据
    weather: [],
    // 当前页数
    page: 1,
    // 总页数
    allPages: 0,
    // 总数据量
    count: 0,
    // 每页条数
    pageCount: 7,
    //选择页数(数组)
    selectArray: [],
  },
  // 自定义函数
  methods: {
    //查询
    query() {
      console.log("点击查询")
      var that = this
      queryData("请求气象数据").then(res => {
        console.log("查询了哦",res)
        //总数据量
        this.data.count = res.data.length
        console.log("总数据量：", this.data.count)
        //总页数(总数据量/每页条数，向上取整)
        this.data.allPages = Math.ceil(this.data.count / this.data.pageCount)
        that.setData({
          allPages: this.data.allPages,
        })
        //加载跳转内容(数组赋值)
        console.log(typeof parseInt(this.data.allPages))
        for (var i = 0; i < parseInt(this.data.allPages); i++) {
          var newarray = {
            id: i,
            text: i + 1
          }
          that.data.selectArray = that.data.selectArray.concat(newarray);
          that.setData({ //只能存值
            selectArray: that.data.selectArray
          });
        }
        console.log("总页数", this.data.allPages)
        //控制表格是否显示
        if (this.data.count === 0) {
          that.setData({
            data: false
          })
        } else {
          that.setData({
            data: true
          })
        }
        //把拿到的所有数据储存进列表weather[]
        for (var i = 0; i < res.data.length; i++) {
          var newarray = {
            temperature: res.data[i].temperature,
            humidity: res.data[i].humidity,
            windSpeed: res.data[i].windSpeed,
            windDirection: res.data[i].windDirection,
            precipitation: res.data[i].precipitation,
            illumination: res.data[i].illumination,
            pm10: res.data[i].pm10,
            pm25: res.data[i].pm25
          };
          //加入数据
          that.data.weather = that.data.weather.concat(newarray);
          that.setData({ //只能存值
            weather: that.data.weather
          });
        };
        //加载第一页
        var page = this.data.page
        var pageCount = this.data.pageCount
        var k = 0
        //清空显示数据
        this.setData({
          pageWeather: []
        })
        console.log("初始化第一页：", page, "显示:", (page - 1) * pageCount, "-", page * pageCount - 1, "条数据")
        //显示指定页数的数据
        for (var i = (page - 1) * pageCount; i <= page * pageCount - 1; i = i + 1) {
          this.data.pageWeather[k] = this.data.weather[i]
          k = k + 1
        }
        that.setData({ //视图层的同步更新
          pageWeather: this.data.pageWeather
        })
      }).catch(err => {})
    },
    // 高级查询
    advancedQuery() {
      console.log("点击高级查询")
      // 重置数据
      this.setData({ //视图层的同步更新
        data: false,
        
        selectArray: []
      })
    },
    // 上一页
    previous() {
      var page = this.data.page
      var pageCount = this.data.pageCount
      var k = 0
      if (this.data.page === 1) { //第一页
        wx.showToast({
          title: '当前已是第一页',
          icon: 'none',
        })
        //清空上一次显示数据
        this.setData({ //视图层的同步更新
          pageWeather: [],
          page: this.data.page
        })
        console.log("当前页：", page, "显示:", (page - 1) * pageCount, "-", page * pageCount - 1, "条数据")
        //显示指定页数的数据
        for (var i = (page - 1) * pageCount; i <= page * pageCount - 1; i = i + 1) {
          this.data.pageWeather[k] = this.data.weather[i]
          k = k + 1
        }
        this.setData({ //视图层的同步更新
          pageWeather: this.data.pageWeather
        })
      } else { //上一页正常显示数据
        this.setData({
          pageWeather: [],
        })
        this.data.page = this.data.page - 1
        page = this.data.page
        console.log("当前页：", page, "显示:", (page - 1) * pageCount, "-", page * pageCount - 1, "条数据")
        //显示指定页数的数据
        for (var i = (page - 1) * pageCount; i <= page * pageCount - 1; i++) {
          this.data.pageWeather[k] = this.data.weather[i]
          k = k + 1
        }
        this.setData({ //视图层的同步更新
          pageWeather: this.data.pageWeather,
          page: this.data.page
        })
      }
    },
    // 下一页
    nextPage() {
      var page = this.data.page
      var pageCount = this.data.pageCount
      var k = 0
      if (this.data.page === this.data.allPages) { // 最后一页
        wx.showToast({
          title: '当前已是最后一页',
          icon: 'none',
        })
        this.setData({ //视图层的同步更新
          pageWeather: [],
          page: this.data.page
        })
        console.log("当前页：", page, "显示:", (page - 1) * pageCount, "-", this.data.count - 1, "条数据")
        //显示指定页数的数据
        for (var i = (page - 1) * pageCount; i < this.data.count; i++) {
          this.data.pageWeather[k] = this.data.weather[i]
          k = k + 1
        }
        this.setData({ //视图层的同步更新
          pageWeather: this.data.pageWeather
        })
      } else { //正常显示下一页数据
        this.data.page = this.data.page + 1
        page = this.data.page
        if (this.data.page === this.data.allPages) { //最后一页数据展示
          this.setData({ //视图层的同步更新
            pageWeather: [],
            page: this.data.page
          })
          console.log("当前页==：", page, "显示:", (page - 1) * pageCount, "-", this.data.count - 1, "条数据")
          for (var i = (page - 1) * pageCount; i < this.data.count; i++) {
            this.data.pageWeather[k] = this.data.weather[i]
            k = k + 1
          }
          this.setData({ //视图层的同步更新
            pageWeather: this.data.pageWeather
          })
        } else { //下一页正常展示
          this.setData({
            pageWeather: [],
            page: this.data.page
          })
          console.log("当前页：", page, "显示:", (page - 1) * pageCount, "-", page * pageCount - 1, "条数据")
          for (var i = (page - 1) * pageCount; i <= page * pageCount - 1; i++) {
            this.data.pageWeather[k] = this.data.weather[i]
            k = k + 1
          }
          this.setData({ //视图层的同步更新
            pageWeather: this.data.pageWeather
          })
        }
      }
    },
    //子组件select传递点击页数
    getDate: function (e) {
      console.log("跳转至：", e.detail.text)
      this.data.page = parseInt(e.detail.text)
      // 最后一页（）
      var page = this.data.page
      var pageCount = this.data.pageCount
      var k = 0
      if (this.data.page === this.data.allPages) { // 最后一页
        this.setData({ //视图层的同步更新
          pageWeather: [],
          page: this.data.page
        })
        console.log("当前页：", page, "显示:", (page - 1) * pageCount, "-", this.data.count - 1, "条数据")
        //显示指定页数的数据
        for (var i = (page - 1) * pageCount; i < this.data.count; i++) {
          this.data.pageWeather[k] = this.data.weather[i]
          k = k + 1
        }
        this.setData({ //视图层的同步更新
          pageWeather: this.data.pageWeather
        })
      } else {
        for (var i = (page - 1) * pageCount; i <= page * pageCount - 1; i++) {
          this.data.pageWeather[k] = this.data.weather[i]
          k = k + 1
        }
        this.setData({ //视图层的同步更新
          pageWeather: this.data.pageWeather
        })
      }

    }

  },
  // 组件生命周期
  lifetimes: {
    // 在组件实例进入页面节点树时执行
    attached: function () {
    },
    // 在组件在视图层布局完成后执行
    ready: function () {
      var query = wx.createSelectorQuery()
      query.select('.table').boundingClientRect(function (rect) {
        var height = rect.height
        console.log(height)
      })

    }
  }
})