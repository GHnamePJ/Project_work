// pages/addres/addres.js
Page({
  data: {
    obj:[
      {
        id:1,
        addres:"粤嵌717",
        name:"胖橘",
        gender:"女士",
        phone:"12345678910"
      },
      {
        id:2,
        addres:"广轻211",
        name:"鱼",
        gender:"女士",
        phone:"12389777890"
      },
      {
        id:3,
        addres:"广轻211",
        name:"鱼",
        gender:"女士",
        phone:"12389777890"
      },
      {
        id:4,
        addres:"广轻211",
        name:"鱼",
        gender:"女士",
        phone:"12389777890"
      },
      {
        id:5,
        addres:"广轻211",
        name:"鱼",
        gender:"女士",
        phone:"12389777890"
      },
      {
        id:6,
        addres:"广轻211",
        name:"鱼",
        gender:"女士",
        phone:"12389777890"
      },
    ]
  },
  addres(event){
    console.log(event);
    var index = event.currentTarget.dataset.index;
    console.log(index);
    var addres = this.data.obj[index].addres;
    console.log(addres);
    var addres = wx.setStorageSync('addres', addres);
    wx.switchTab({
      url: '/pages/menu/menu',
    })
  }
  
})