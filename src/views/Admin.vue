<template>
    <div id="admin">
        <el-container class="content">
            <navigation :state="isCollapse" class="navigation"/>
            <el-container>
                <el-header class="header">
                    <head-site :state.sync="isCollapse"/>
                </el-header>
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script lang="ts">
    import Vue from "vue";
    import Component from "vue-class-component";
    import Navigation from "@/components/Navigation.vue";
    import HeadSite from "@/components/HeadSite.vue";
    import {Route} from "vue-router";
    // 用户认证

    @Component({
        components: {
            Navigation,
            HeadSite
        }
    })
    export default class Admin extends Vue {
        isCollapse = false;
    }
</script>

<style scoped>
    #admin,
    .content {
        height: 100%;
        text-align: left;
    }

    .el-header,
    .el-main,
    .el-footer {
        margin: 0;
        padding: 0;
    }

    .el-main {
        background: #f0f2f5;
    }

    .header {
        box-shadow: 0 0 6px rgba(0, 0, 0, 0.04);
    }
</style>
