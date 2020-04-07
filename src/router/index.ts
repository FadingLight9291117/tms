import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/views/Login.vue";
import Admin from "@/views/Admin.vue";
import Error from "@/views/Error.vue";
import SignUpInfo from "@/views/SignUpInfo.vue";
import Home from "@/views/Home.vue";
import Test from "@/views/Test.vue"

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        component: Test
    },
    {
        path: "/home",
        name: "home",
        component: Home
    },
    {
        path: "/admin",
        name: "admin",
        component: Admin,
        children: [
            {
                path: "signUpInfo",
                name: "signUpInfo",
                component: SignUpInfo
            }
        ]
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
