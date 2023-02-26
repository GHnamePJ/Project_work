//引入封装的reuest请求
const { getRequest, postRequest } = require("./https");


//基于业务封装的接口
module.exports = {
  /* 获取气象站编号信息 */
  getStations :(data)=>{
    return postRequest("qx/stations", data);
  },
  /* 查询所有数据信息 */
  queryData(data){
        return postRequest("qx/stations/date",data)
      },

  /* 获取指定气象站的数据日期范围 */
  getStationsDate:(data)=>{
    return postRequest("qx/stations/date",data)
  },

  /** 获取气象信息预测信息 */
  getPredict:(data)=>{
    return postRequest("qx/predict",data)
  },

  /**计算任一时间段内气象数据的协相关矩阵 */
  getCorrelation:(data)=>{
    return postRequest("qx/correlation",data)
  },

  /** 获取任一小时的分钟气象信息*/
  getStatHour:(data)=>{
    return postRequest("qx/stat_hour",data)
  },

  /** 获取任一天的小时气象信息 */
  getStatDay:(data)=>{
    return postRequest("qx/stat_day",data)
  },

  /** 获取任意时间段以天为单位的气象数据 */
  getStatDayRange:(data)=>{
    return postRequest("qx/stat_day_range",data)
  },
  /** 复杂查询 */
  getQuery:(data)=>{
    return postRequest("qx/query",data)
  }

}