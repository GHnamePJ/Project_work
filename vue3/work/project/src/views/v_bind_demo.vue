<!--v-bind对象语法绑定class-->
<template>
    <div>
        <!-- <div class="static" :class="{active:isActive,'text-danger':hasError}"></div> -->
        <div :class="classObject"></div>
    </div>
    
</template>
<script>
import { computed } from '@vue/runtime-core';
import {reactive,toRefs} from "vue"
export default {
    name:"v_bind_demo",
    setup(){
        const data = reactive({
            isActive:true,
            hasError:false
        });
        // ？？？？
        const classObject = computed(() =>{
            // console.log(data.error)
            return{
                active:data.isActive && !data.error,
                "text-danger":data.error && data.error.type ==="fatal"
            }
        })
        return{
            classObject
        }
    }
}
</script>