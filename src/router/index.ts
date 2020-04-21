import Vue from "vue";
import VueRouter, {Route} from "vue-router";
import Login from "@/views/admin/Login.vue";
import Admin from "@/views/admin/Admin.vue";
import Error from "@/views/Error.vue";
import StudentSignUp from "@/views/admin/adminViews/StudentSignUp.vue";
import Home from "@/views/admin/Home.vue";
import CoachSignUp from "@/views/admin/adminViews/CoachSignUp.vue";
import Classes from "@/views/admin/adminViews/Classes.vue";
import Course from "@/views/admin/adminViews/Course.vue";
import StudentInfo from "@/views/admin/adminViews/StudentInfo.vue";
import CoachInfo from "@/views/admin/adminViews/CoachInfo.vue";
import {Message} from "element-ui";
import News from "@/views/admin/adminViews/News.vue";
import Editor from "@/components/admin/newsComponents/Editor.vue";
import OnlineWork from "@/views/admin/adminViews/OnlineWork.vue";
import AddWork from "@/components/admin/onlineWorkComponents/AddWork.vue";

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
        component: AddWork
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
            },
            {
                path: "news",
                name: "news",
                component: News
            },
            {
                path: "editor",
                name: "editor",
                component: Editor
            },
            {
                path: "onlineWork",
                name: "onlineWork",
                component: OnlineWork
            },
            {
                path: "addWork",
                name: "addWork",
                component: AddWork
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
