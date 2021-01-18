<template>
    <div>
        <header style="width: 100%">
            <el-menu
                    mode="horizontal"
                    @select="handleSelect"
                    class="menu"
                    background-color="#24292e"
                    text-color="#fff"
                    active-text-color="#ffd04b"
            >
                <el-menu-item index="index">首页</el-menu-item>
                <el-menu-item style="float: right"><span class="sign-up" @click="handleSignUp">报名</span>
                </el-menu-item>
                <el-menu-item style="float: right" @click="handleLogin">登录</el-menu-item>
            </el-menu>
        </header>
        <main>
            <h1>登录</h1>
            <h1>LOGIN</h1>
            <el-form
                    class="form"
                    :data="formData"
                    label-position="top"
                    size="small">
                <el-form-item label="账号">
                    <el-input v-model="formData.phone"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input type="password" v-model="formData.password"></el-input>
                </el-form-item>
                <br>
                <el-form-item>
                    <el-button type="primary" size="medium" style="width: 300px" @click="login">登录</el-button>
                </el-form-item>
            </el-form>
        </main>
    </div>
</template>

<script lang="ts">
    import {Vue, Component} from "vue-property-decorator";
    import {Message} from "element-ui";

    @Component
    export default class Login extends Vue {
        handleLogin() {
            this.$router.push({name: "login"})
        }

        handleSelect(key: string) {
            this.$router.push({name: key})
        }

        handleSignUp() {
            this.$router.push({name: "signUp"})
        }

        formData = {
            phone: "",
            password: ""
        }

        login() {
            this.auth()
        }

        auth() {
            const data = {
                type: "student",
                phone: this.formData.phone,
                password: this.formData.password
            }
            this.$axios
                .post("/login/auth", data)
                .then(resp => {
                    if (resp.data === true) {
                        Message.success("登录成功")
                        this.$axios
                            .post("/login", data)
                            .then(resp => {
                                this.$store.commit("user/setInfo", resp.data)
                                this.$store.commit("user/setType", data.type)
                                this.$router.push({name: "home"})
                            })
                            .catch(() => Message.error("网络连接错误"))
                    } else {
                        Message.error("账号或密码错误")
                    }
                })
                .catch(() => Message.error("网络连接错误"))
        }
    }
</script>

<style scoped>
    .el-menu.el-menu--horizontal {
        border-bottom: none;
    }

    .menu {
        text-align: left;
        padding-left: 30px;
    }

    .sign-up {
        border-radius: 10px;
        border: 1px solid;
        padding: 10px;
        margin: 0;
    }

    main {
        padding-top: 50px;
        text-align: center;
    }

    .form {
        text-align: left;
        display: inline-block;
        width: 500px;
    }
</style>