import { createRouter, createWebHistory } from 'vue-router'
// 导入路由所需的组件
import Home from '@/views/Home.vue'
import Nav from '@/components/Nav.vue'
import Content from '@/components/Content.vue'

//定义一组路由
const routes = [
  {
    path:'/',
    name:'Home',
    component:Home

  },
  {
    path:'/Nav',
    name:'Nav',
    component:Nav
  },
  {
    path:'/Content',
    name:'Content',
    component:Content
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
