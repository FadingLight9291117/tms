<template>
    <div id="login">
        <el-form id="form" ref="form" :model="form">
            <h1>系统登陆</h1>
            <el-form-item>
                <el-input v-model="form.account" prefix-icon="el-icon-user" placeholder="账号"></el-input>
            </el-form-item>
            <el-form-item>
                <el-input type="password" v-model="form.password" prefix-icon="el-icon-lock" show-password placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button style="width: 100%" type="primary" @click="onSubmit">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script lang="ts">
    import Vue from "vue";
    import Component from "vue-class-component";

    @Component
    export default class Login extends Vue {
        private form = {
            account: "",
            password: ""
        };

        public onSubmit() {
               console.log(`${this.form.account} ${this.form.password}`)
        }
    }
</script>

<style scoped>
    #login {
        height: 100%;
        background: url("../assets/bg1.jpg") no-repeat;
    }

    #form {
        background-color: white;
        display: inline-block;
        margin-top: 10%;
        width: 30%;
        padding: 5px 20px;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    }
</style>