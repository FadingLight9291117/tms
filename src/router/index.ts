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

Vue.use(VueRouter);

// 验证是否登录
const userAuthenticated = () => {
    const data = {
        type: Vue.$cookies.get("type"),
        phone: Vue.$cookies.get("account"),
        password: Vue.$cookies.get("password")
    };

    return !(data.type == null || data.phone == null || data.password == null);

};

const routes = [
    {
        path: "/",
        component: () => import("../components/courseComponents/CourseTable.vue")
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
            if (userAuthenticated()) {
                next();
            } else {
                next("/login");
            }
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
