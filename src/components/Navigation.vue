<template>
    <el-menu
            id="navigation"
            @open="handleOpen"
            @close="handleClose"
            @select="handleSelect"
            :collapse="this.state"
            background-color="rgb(48, 65, 86)"
            text-color="rgb(191, 203, 217)"
            active-text-color="rgb(64, 158, 255)"
    >
        <el-submenu index="1">
            <template slot="title">
                <i class="el-icon-location"></i>
                <span slot="title">导航一</span>
            </template>
            <el-menu-item-group>
                <span slot="title">分组一</span>
                <el-menu-item index="1-1">选项1</el-menu-item>
                <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
                <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
                <span slot="title">选项4</span>
                <el-menu-item index="1-4-1">选项1</el-menu-item>
            </el-submenu>
        </el-submenu>
        <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">导航二</span>
        </el-menu-item>
        <el-menu-item index="3" disabled>
            <i class="el-icon-document"></i>
            <span slot="title">导航三</span>
        </el-menu-item>
        <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">导航四</span>
        </el-menu-item>
    </el-menu>
</template>

<script lang="ts">
    import Vue from "vue"
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

        public handleOpen(key: string, keyPath: Array<string>): void {
            console.log(`key: ${key}, keyPath: ${keyPath}`)
        }

        public handleClose(key: string, keyPath: Array<string>): void {
            console.log(`key: ${key}, keyPath: ${keyPath}`)
        }

        public handleSelect(key: string, keyPath: Array<string>): void {
            console.log(`key: ${key}}, keyPath: ${keyPath}`)
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