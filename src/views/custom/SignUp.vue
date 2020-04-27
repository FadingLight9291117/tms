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
            <p>Join us !</p>
            <h1>REGISTRY</h1>
            <el-form :data="formData" label-position="top" size="small">
                <el-form-item label="上传头像">
                    <el-upload
                            class="avatar-uploader"
                            name="img"
                            :action="uploadUrl"
                            :show-file-list="false"
                            :on-success="uploadSuccess"
                    >
                        <img v-if="imgUrl !== ''" :src="imgUrl" class="avatar" alt="加载错误">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="formData.name" placeholder="请输入姓名"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="formData.phone" placeholder="请输入手机号，将作为账号"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input type="password" v-model="formData.password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="formData.sex">
                        <el-radio :label="0">男</el-radio>
                        <el-radio :label="1">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="带位">
                    <el-select v-model="formData.level">
                        <el-option
                                v-for="(item, index) in levelOptions"
                                :key="index"
                                :label="item"
                                :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <br>
                <el-form-item>
                    <el-button @click="onSubmit" size="medium" type="primary" style="width: 300px">立即报名</el-button>
                </el-form-item>
            </el-form>
        </main>
    </div>
</template>

<script lang="ts">
    import {Vue, Component} from "vue-property-decorator";
    import {Message} from "element-ui";

    @Component
    export default class SignUp extends Vue {

        handleLogin() {
            this.$router.push({name: "login"})
        }

        handleSelect(key: string) {
            this.$router.push({name: key})
        }

        handleSignUp() {
            this.$router.push({name: "signUp"})
        }

        uploadUrl = "http://localhost:80/upload/img"
        imgUrl = "";

        uploadSuccess(res: any) {
            this.imgUrl = res.url
        }

        levelOptions = ["白带", "白黄带", "黄带", "黄绿带", "绿带", "绿蓝带", "蓝带", "蓝红带", "红带", "红黑带", "黑带"]

        formData = {
            name: "",
            phone: "",
            password: "",
            level: "白带",
            sex: 0,
            date: "",
        }

        onSubmit() {
            const now = new Date();
            const date = `${now.getFullYear()}-${now.getMonth() + 1}-${now.getDate()}`
            this.$axios
                .post("/signUp/student", {
                    student: {
                        phone: this.formData.phone,
                        password: this.formData.password,
                        name: this.formData.name,
                        sex: this.formData.sex,
                        level: this.formData.level,
                        picture: this.imgUrl,
                        date: date
                    }
                })
                .then(resp => Message.success("注册成功"))
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

    .el-form {
        display: inline-block;
        text-align: left;
        width: 500px;
    }

    .avatar-uploader {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        height: 178px;
        width: 178px;
    }

    .avatar-uploader:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }

</style>