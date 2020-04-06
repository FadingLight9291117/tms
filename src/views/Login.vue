<template>
  <div id="login">
    <el-form id="form" status-icon ref="form" :model="form">
      <h1>系统登陆</h1>
      <el-form-item
        prop="phone"
        :rules="[
          { required: true, message: '手机号不能为空' },
          { type: 'number', message: '手机号必须为数字' }
        ]"
      >
        <el-input
          v-model.number="form.phone"
          prefix-icon="el-icon-user"
          placeholder="手机号"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item
        prop="password"
        :rules="[{ required: true, message: '密码不能为空' }]"
      >
        <el-input
          type="password"
          v-model="form.password"
          prefix-icon="el-icon-lock"
          show-password
          placeholder="密码"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item
        prop="type"
        :rules="[{ required: true, message: '身份不能为空' }]"
      >
        <el-select v-model="form.type" placeholder="请选择身份">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="type">
        <el-button
          type="primary"
          @click="onSubmit"
          v-loading.fullscreen.lock="loading"
          >登录
        </el-button>
      </el-form-item>
      <el-alert
        v-show="alert"
        type="error"
        description="账号或密码错误"
        center
        show-icon
      />
      <br />
    </el-form>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Message } from "element-ui";

@Component
export default class Login extends Vue {
  // 加载动画
  loading = false;
  // 登录错误提示alert
  alert = false;
  // 登录表单
  form = {
    phone: "",
    password: "",
    type: ""
  };
  // 身份选项
  options = [
    {
      label: "管理员",
      value: "admin"
    },
    {
      label: "教练",
      value: "coach"
    },
    {
      label: "学员",
      value: "student"
    }
  ];

  // 提交表单，带验证
  onSubmit() {
    // 表单校验
    // 表单验证这里出错， 必须这样写才正确
    (this.$refs.form as any).validate((valid: any) => {
      if (valid) {
        this.loading = true;
        this.$axios.post("/login", this.form).then(response => {
          const data = response.data;
          if (data === 0) {
            this.loading = false;
            this.alert = true;
            setTimeout(() => (this.alert = false), 3000);
          } else if (data === 1) {
            this.loading = false;
            Message({
              message: "登录成功",
              type: "success"
            });
            this.$router.push("/admin");
          }
        });
      } else {
        return false;
      }
    });
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
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}

.el-form-item {
  text-align: left;
}

.el-button {
  width: 100%;
}
</style>
