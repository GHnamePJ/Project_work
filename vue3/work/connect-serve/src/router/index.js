import { createRouter, createWebHistory } from 'vue-router'
import Connect_Serve from '../views/Connect_Serve.vue'

const routes = [
  {
    path: '/',
    name: 'Connect_Serve',
    component: Connect_Serve
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
