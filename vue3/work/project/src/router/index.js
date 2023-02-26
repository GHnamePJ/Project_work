import { createRouter, createWebHistory } from 'vue-router'
import v_for_demo from "@/views/v_for_demo.vue"
import text_demo from "@/views/text_demo.vue"
import computed_demo from "@/views/computed_demo.vue"
import v_bind_demo from "@/views/v_bind_demo.vue"

const routes = [
  {
    path:"/",
    name:"v_for_demo",
    component:v_for_demo
  },
  {
    path:"/text_demo",
    name:"text_demo",
    component:text_demo
  },
  {
    path:"/computed_demo",
    name:"computed_demo",
    component:computed_demo
  },
  {
    path:"/v_bind_demo",
    name:"v_bind_demo",
    component:v_bind_demo
  }
 
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
