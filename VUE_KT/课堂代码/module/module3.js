import module1 from './module1.js';//导入默认变量
import {fun} from './module2.js';//导入指定变量
setTimeout(function(){
  module1();
},1000);
fun();