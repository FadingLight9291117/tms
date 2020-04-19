import Vue from "vue";
import VueRouter, {Route} from "vue-router";
import Login from "@/views/Login.vue";
import Admin from "@/views/Admin.vue";
import Error from "@/views/Error.vue";
import StudentSignUp from "@/views/adminViews/StudentSignUp.vue";
import Home from "@/views/Home.vue";
import CoachSignUp from "@/views/adminViews/CoachSignUp.vue";
import Classes from "@/views/adminViews/Classes.vue";
import Course from "@/views/adminViews/Course.vue";
import StudentInfo from "@/views/adminViews/StudentInfo.vue";
import CoachInfo from "@/views/adminViews/CoachInfo.vue";
import {Message} from "element-ui";

Vue.use(VueRouter);

// 验证是否登录
const userAuthenticated = (next: Function) => {
    const data = {
        type: Vue.$cookies.get("type"),
        phone: Vue.$cookies.get("account"),
        password: Vue.$cookies.get("password")
    };
    if (data.type != null && data.phone != null && data.password != null)
        Vue.$axios
            .post("/login/auth", data)
            .then(resp => resp.data ? next() : next("/login"))
            .catch(() => Message.error("网络连接错误"));
    else
        next("/login")
};

const routes = [
    {
        path: "/",
        component: () => import("../views/adminViews/StudentInfo.vue")
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
        beforeEnter(to: Route, from: Route, next: any) {
            userAuthenticated(next);
        },
        children: [
            {
                path: "studentSignUp",
                name: "studentSignUp",
                component: StudentSignUp
            },
            {
                path: "coachSignUp",
                name: "coachSignUp",
                component: CoachSignUp
            },
            {
                path: "classes",
                name: "classes",
                component: Classes
            },
            {
                path: "course",
                name: "course",
                component: Course
            },
            {
                path: "studentInfo",
                name: "studentInfo",
                component: StudentInfo
            },
            {
                path: "coachInfo",
                name: "coachInfo",
                component: CoachInfo
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
