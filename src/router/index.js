import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'
import User from '../views/User.vue'
import Login from "../views/login.vue"
import Register from "../views/Register.vue"
import Books from "../views/Books.vue"
import News from "../views/News.vue"
const routes = [
  {
    // 主页
    path: '/',
    name: 'layout',
    component: Layout,
    redirect: "/user",
    children:[
        {
          path: 'user',
          name: 'user',
          component: User,
       },
       {
          path: '/person',
          name: '个人中心',
          component:()=>import("../views/Person.vue"),
       },
       {
          path: '/books',
          name: '23',
          component: Books,
       },
       {
        path: '/news',
        name: 'news',
        component: News,
     }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/register',
    name: '注册',
    component: Register,
  },


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
