const FactorNumToName = function (factorNum) {
  const factor = {
    1: '温度',
    2: '湿度',
    3: '风速',
    4: '风向',
    5: '降雨量',
    6: '光照',
    7: 'PM2.5',
    8: 'PM10'
  }
  return factor[factorNum] ? factor[factorNum] : '因素';
}



const FactorNumToCompany = function (factorNum) {
  const factor = {
    1: '℃',
    2: '%',
    3: 'km/h',
    4: ' ',
    5: 'mm',
    6: 'lx',
    7: 'μg/m3',
    8: 'μg/m3'
  }
  return factor[factorNum] ? factor[factorNum] : '因素';
}

const FactorNumToEnglish = function (factorNum) {
  const factor = {
    1: 'temperature',
    2: 'humidity',
    3: 'windSpeed',
    4: 'windDirection',
    5: 'precipitation',
    6: 'illumination',
    7: 'pm25',
    8: 'pm10'
  }
  return factor[factorNum] ? factor[factorNum] : '因素';
}
const FactorEnglishToNum = function (factorEnglish) {
  const factor = {
    'temperature': 1,
    'humidity': 2,
    'windSpeed': 3,
    'windDirection': 4,
    'precipitation': 5,
    'illumination': 6,
    'pm25': 7,
    'pm10': 8
  }
  return factor[factorEnglish] ? factor[factorEnglish] : '因素';
}

const FactorEnglishToChinese = function (factorEnglish) {
  const factor = {
    "temperature": '温度',
    "humidity": '湿度',
    "windSpeed": '风速',
    "windDirection": '风向',
    "precipitation": '降雨量',
    "illumination": '光照',
    "pm25": 'PM2.5',
    "pm10": 'PM10'
  }
  return factor[factorEnglish] ? factor[factorEnglish] : '因素';
}

const FactorChineseToEnglish = function (factorChinese) {
  const factor = {
    '温度': "temperature",
    '湿度': "humidity",
    '风速': "windSpeed",
    '风向': "windDirection",
    '降雨量': "precipitation",
    '光照': "illumination",
    'PM2.5': "PM2.5",
    'PM10': "pm10"
  }
  return factor[factorChinese] ? factor[factorChinese] : '因素';
}

const FactorNameToNum = function (factorName) {
  const factor = {
    '温度': 1,
    '湿度': 2,
    '风速': 3,
    '风向': 4,
    '降雨量': 5,
    '光照': 6,
    'PM2.5': 7,
    'PM10': 8
  }
  return factor[factorName] ? factor[factorName] : '因素';
}

const FactorNameToCompany = function (factorName) {
  const factor = {
    '温度': "℃",
    '湿度': "%",
    '风速': "km/h",
    '风向': " ",
    '降雨量': "mm",
    '光照': "lx",
    'PM2.5': " μg/m3",
    'PM10': " μg/m3"
  }
  return factor[factorName] ? factor[factorName] : '因素';
}

const isFuture= function(str) {
  let d = new Date(str.replace(/-/g, '/'))
  let todaysDate = new Date()
  if (d.setHours(0, 0, 0, 0) > todaysDate.setHours(0, 0, 0, 0)) {
    return true
  } else {
    return false
  }
}

var FactorChineseToTip = function(factorChinese){
  var factor = {
    '温度': "",
    // 高湿：80%以上\n最适合湿度：60%左右\n低湿：40%以下
    '湿度': '[{ value: 40, name: "低湿 40%以下" },{ value: 60, name: "最适合湿度 60%左右" },{ value: 80, name: "高湿 80%以上" }]',
    '风速': "风速有十二级：\n0级：无风\n1级：软风\n2级：轻风\n3级：微风\n4级：和风\n5级：清劲风\n6级：强风\n7级：疾风\n8级：大风\n9级：烈风\n10级：狂风\n11级：暴风\n12级：台风或飓风",
    '风向': "http://mms0.baidu.com/it/u=3027927461,4132417695&fm=253&app=120&f=JPEG&fmt=auto&q=75?w=610&h=500",
    '降雨量': "小雨：0.1-9.9\n中雨：10.0-24.9\n大雨：25.0-49.9\n暴雨：50.0-99.9\n大暴雨：100.0-249.9\n特大暴雨：≥250",
    '光照': "https://img1.baidu.com/it/u=167319871,3219516471&fm=253&fmt=auto&app=138&f=JPEG?w=499&h=330",
    'PM2.5': "优：0-35\n良：35-75\n轻度污染：75-115\n中度污染：115-150\n重度污染：150-250\n严重污染：250以上",
    'PM10': "一级：50μg/m3\n二级：150μg/m3"
  }
  return factor[factorChinese] ? factor[factorChinese] : '因素';
}


const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : `0${n}`
}

module.exports = {
  formatTime,
  FactorNumToName,
  FactorNameToNum,
  FactorEnglishToChinese,
  FactorEnglishToNum,
  FactorNumToEnglish,
  FactorNumToCompany,
  FactorNameToCompany,
  FactorChineseToEnglish,
  isFuture,
  FactorChineseToTip
  // getMinIndex
}