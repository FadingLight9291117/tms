<template>
    <el-menu
            id="navigation"
            @select="handleSelect"
            :collapse="this.state"
            background-color="#545c64"
            text-color="#ffffff"
            active-text-color="#ffd04b"
    >
        <el-menu-item index="1">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
        </el-menu-item>
        <el-submenu index="2">
            <template slot="title">
                <i class="el-icon-s-order"></i>
                <span slot="title">报名信息</span>
            </template>
            <el-menu-item index="2-1">学员报名</el-menu-item>
            <el-menu-item index="2-2">教练报名</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-menu"></i>
                <span slot="title">教务管理</span>
            </template>
            <el-menu-item index="3-1">分班</el-menu-item>
            <el-menu-item index="3-2">排课</el-menu-item>
        </el-submenu>
        <el-menu-item index="4">
            <i class="el-icon-document"></i>
            <span slot="title">在线教学</span>
        </el-menu-item>
        <el-submenu index="5">
            <template slot="title">
                <i class="el-icon-setting"></i>
                <span slot="title">信息管理</span>
            </template>
            <el-menu-item index="5-1">学员信息</el-menu-item>
            <el-menu-item index="5-2">教练信息</el-menu-item>
        </el-submenu>
        <el-menu-item index="6">
            <i class="el-icon-edit"></i>
            <span slot="title">新闻发布</span>
        </el-menu-item>
    </el-menu>
</template>

<script lang="ts">
    import Vue from "vue";
    import Component from "vue-class-component";

    const NavigationProps = Vue.extend({
        props: {
            state: {
                type: Boolean,
                default: true
            }
        }
    });

    @Component
    export default class Navigation extends NavigationProps {
        handleSelect(key: string, keyPath: Array<string>): void {
            console.log(`key: ${key}, keyPath: ${keyPath}`);
        }
    }
</script>

<style scoped>
    #navigation {
        height: 100%;
    }

    #navigation:not(.el-menu--collapse) {
        text-align: left;
        width: 240px;
    }
</style>
