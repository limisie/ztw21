import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')

// const NotFound = { template: '<p>Page not found</p>' }

// import Vue from 'vue'
// import VueRouter from 'vue-router'

// Vue.use(VueRouter)

// const routes = [
//   {
//     path: '/',
//     name: 'Home',
//     component: () => import('@/App.vue')
//   },
//   {
//     path: '/books',
//     name: 'Books',
//     component: () => import('@/Books.vue')
//   },
//   {
//     path: '/authors',
//     name: 'Authors',
//     component: () => import('@/Authors.vue')
//   }
// ]

// const router = new VueRouter({
//   mode: 'history',
//   base: process.env.BASE_URL,
//   routes
// })

// export default router