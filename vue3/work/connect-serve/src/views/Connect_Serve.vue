<template>
    <p>今天也是元气满满的一天！</p>
    <button @click="toTea">请求喝奶茶！</button>
</template>
<script>
import axios from "axios" 
export default{
    name:"Connect_Serve",
    setup(){
        const toTea = ()=>{
            axios.get("http://localhost:8099/tea",{}).then((res)=>{
                 alert(res.data);
            }).catch((error)=>{
                console.log(error);
            })
            
        }
        return{
            toTea
        }
    }
}

</script>



