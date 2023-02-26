<!-- 计算属性computed -->
<template>
  <div>
    <p>{{ num }}</p>
    <p>{{ double }}</p>
    <p>修改后num的值：{{tripple}}</p>
    <button @click="addBtn">num+1</button>
  </div>
</template>
<script>
import {computed,ref,reactive,toRefs} from "vue"
export default {
    name:"computed_demo",
    setup(){
        let data = reactive({
            num:1
        })
        let double = computed(() =>{
            return data.num*2
        })
        let tripple = computed({
            get(){
                return data.num + 6
            },
            // ???
            // set(val){
            //     console.log("修改："+val);
            //     return num.value = val+100
            // }
        })
        let addBtn = () =>{
            data.num++
        }
        
        return{
            ...toRefs(data),
            double,
            tripple,
            addBtn
        }
    }
}
</script>