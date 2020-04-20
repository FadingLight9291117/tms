<template>
    <div>
        <el-card class="card" header="发布新闻">
            <el-form :model="formData" label-position="right" label-width="40px">
                <el-form-item label="标题">
                    <el-input v-model="formData.title"></el-input>
                </el-form-item>
                <el-form-item label="内容">
                    <el-input v-model="formData.content" type="textarea" :rows="10"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submit">发布</el-button>
                    <el-button @click="cancel">取消</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script lang="ts">
    import {Vue, Component} from "vue-property-decorator";
    import {Message} from "element-ui";

    @Component
    export default class Editor extends Vue {
        formData = {
            title: "",
            content: "",
            date: ""
        }

        submit() {
            const date = new Date();
            const data = {
                title: this.formData.title,
                date: `${date.getFullYear()}-${date.getMonth()}-${date.getDate()}`,
                content: this.formData.content
            }
            this.$axios
                .post("/news/add", data)
                .then(resp => {
                    if (resp.data === 1) {
                        this.$router.push({name: "news"});
                        Message.success("发布成功");
                    }
                })
                .catch(() => Message.error("网络连接错误"));
        }

        cancel() {
            this.$router.push({name: "news"});
        }
    }
</script>

<style scoped>
    .card {
        margin: 20px 20px 0 20px;
    }
</style>