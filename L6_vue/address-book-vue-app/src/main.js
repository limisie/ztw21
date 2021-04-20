import Vue from 'vue'
import VueRouter from 'vue-router';

import App from './App.vue';
import Books from './Books.vue';
import Authors from './Authors.vue';
import Persons from './Persons.vue';

Vue.use(VueRouter)

const routes = [
  { path: '/books', component: Books },
  { path: '/authors', component: Authors },
  { path: '/friends', component: Persons },
]

const router = new VueRouter({
  routes
})

new Vue({
  el: '#app',
  render: h => h(App),
  router: router
})