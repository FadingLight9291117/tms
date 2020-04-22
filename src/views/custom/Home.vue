<template>
    <div id="home">
        <header style="position: fixed; width: 100%">
            <el-menu
                    :default-active="activeIndex"
                    mode="horizontal"
                    @select="handleSelect"
                    class="menu"
                    background-color="#24292e"
                    text-color="#fff"
                    active-text-color="#ffd04b"
            >
                <el-menu-item index="home">首页</el-menu-item>
                <el-submenu index="2">
                    <template slot="title">工作</template>
                    <el-menu-item index="2-1">选项1</el-menu-item>
                    <el-submenu index="2-2">
                        <template slot="title">选项2</template>
                        <el-menu-item index="3">选项3</el-menu-item>
                    </el-submenu>
                </el-submenu>
                <el-menu-item style="float: right"><span style="border: 1px  solid; padding: 10px">注册</span>
                </el-menu-item>
                <el-menu-item style="float: right">登录</el-menu-item>
            </el-menu>
        </header>
        <div class="bg">
            <h1>跆拳道</h1>
        </div>
    </div>
</template>

<script lang="ts">
    import Vue from "vue";
    import Component from "vue-class-component";

    @Component
    export default class Home extends Vue {
        activeIndex = "home";

        handleSelect(key: string) {
            this.$router.push({name: key})
        }
    }
</script>

<style scoped lang="scss">
    #home {
        text-align: left;
        height: 100%;
        width: 100%;
    }

    .el-menu.el-menu--horizontal {
        border-bottom: none;
    }

    .menu {
        text-align: left;
        padding-left: 30px;
    }

    .bg {
        height: 600px;
        background-color: #282c34;
        color: #61dafb;
        text-align: center;
        padding-top: 100px;
        letter-spacing: 0.01em;
        font-weight: 400;
        font-size: 60px;
        line-height: 1.15;
    }
</style>
