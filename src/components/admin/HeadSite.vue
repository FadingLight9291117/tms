<template>
    <div id="headSite">
        <el-button
                id="stateButton"
                icon="el-icon-s-operation"
                circle
                @click="stateChange()"
        ></el-button>
        <el-breadcrumb sepa rator-class="el-icon-arrow-right">
            <!--    这里应该用vuex，维持一个名称列表        -->
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/admin' }">活动详情</el-breadcrumb-item>
        </el-breadcrumb>
        <div id="user">
            <el-dropdown id="dropdown" style="height: 100%">
                <el-button id="dropBtn" plain round>
                    <el-avatar>User</el-avatar>
                </el-button>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>个人中心</el-dropdown-item>
                    <el-dropdown-item>首页</el-dropdown-item>
                    <!--        @click.native 只能这样写            -->
                    <el-dropdown-item :divided="true" @click.native="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>

<script lang="ts">
    import Component from "vue-class-component";
    import Vue from "vue";
    import {Message} from "element-ui";

    const HeadSiteOptions = Vue.extend({
        props: {
            state: {
                type: Boolean,
                required: true
            }
        }
    });

    @Component
    export default class HeadSite extends HeadSiteOptions {
        get localState(): boolean {
            return this.state;
        }

        set localState(value: boolean) {
            this.$emit("update:state", value);
        }

        // 改变侧边栏的状态，即展开和关闭
        stateChange() {
            this.localState = !this.localState;
        }

        // 退出登录
        logout() {
            this.$cookies.remove("type");
            this.$cookies.remove("account");
            this.$cookies.remove("password");
            this.$router.replace("/adminLogin");
            Message({type: "success", message: "您已退出登录！"});
        }

        // mounted() {
        //     // 获取路由
        //     const path = this.$route.path;
        //     path
        //         .split("/")
        //         .filter(e => e != "")
        //         .forEach(e => this.paths.push(e));
        // }
        //
        // paths: Array<string> = [];
    }
</script>

<style scoped>
    #headSite {
        padding: 0;
        margin: 0;
        height: 100%;
    }

    #stateButton {
        height: 100%;
        width: 60px;
        border-color: white;
    }

    .el-breadcrumb {
        display: inline-block;
        vertical-align: middle;
    }

    /*让图标居中*/
    #user {
        float: right;
        height: 100%;
        line-height: 50px;
    }

    #dropBtn {
        padding: 0;
        display: inline-block;
        vertical-align: middle;
        margin-right: 10px;
        width: 40px;
        height: 40px;
    }
</style>
