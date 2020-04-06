import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import Admin from "@/views/Admin.vue";
import Error from "@/views/Error.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/admin",
    name: "admin",
    component: Admin
  },
  {
    path: "/login",
    name: "login",
    component: Login
  },
  {
    path: "*",
    name: "error",
    component: Error
  }
];

const router = new VueRouter({
  routes
});

export default router;
