<template>
    <div id="home">
        <header>
            <el-menu
                    :default-active="activeIndex"
                    mode="horizontal"
                    @select="handleSelect"
                    class="menu"
            >
                <el-menu-item index="1">首页</el-menu-item>
                <el-submenu index="2">
                    <template slot="title">工作</template>
                    <el-menu-item index="2-1">选项1</el-menu-item>
                    <el-submenu index="2-2">
                        <template slot="title">选项2</template>
                        <el-menu-item index="3">选项3</el-menu-item>
                    </el-submenu>
                </el-submenu>
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
        activeIndex = "1";

        handleSelect(key: string, keyPath: Array<string>) {
            console.log(key, keyPath);
        }
    }
</script>

<style scoped lang="scss">
    #home {
        text-align: left;
        height: 100%;
        width: 100%;
        background-color: red;
    }

    .menu {
        text-align: left;
        padding-left: 30px;
    }

    .bg {
        height: 300px;
        background-color: #282c34;
        color: #61dafb;
        text-align: center;
        padding-top: 80px;
        letter-spacing: 0.01em;
        font-weight: 400;
        font-size: 60px;
        line-height: 1.15;
    }
</style>
