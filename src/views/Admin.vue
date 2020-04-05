<template>
    <div id="admin">
        <el-container class="content">
            <navigation :state="navigationCollapse" class="navigation"/>
            <el-container>
                <el-header>
                    <header/>
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
    import Header from "@/components/Header.vue";
    import Footer from "@/components/Footer.vue"

    @Component({
        components: {
            Navigation,
            Header,
            Footer
        }
    })
    export default class Admin extends Vue {
        navigationCollapse = false
    }
</script>

<style scoped>
    #admin, .content {
        height: 100%;
        text-align: left;
    }

    .el-header, .el-main, .el-footer {
        margin: 0;
        padding: 0;
    }

    .el-main {
        background: #f0f2f5;
    }

</style>