	//这里使用的接口呢都是自己模拟的，可以根据自己的需求进行添加
	// 基本变量
	module.exports = {
	  //开发环境的url
	  dev: {
	    baseUrl: "http://192.168.124.10:9094"
	  },
	  //测试环境url
	  test: {
	    // baseUrl:"http://www.test.com"
	  },
	  //线上环境url
	  prod: {
	    // baseUrl:'https://api.it120.cc' 
	  },
	  //后端返回的成功状态码
	  code:{
		codesuccess:"1"
	  },
	  // 例外请求不用携带token的url
	  exceptionTokenUrl: 
      {
	    exceptionAdd: ["user/login","user/register"]
    },

	  //保存token的键的名
	  Tokenkey: {
	    key: "token"
    },
    
    // 本地存储气象站信息
    StationKey:{
      station:"station"
    }
	}