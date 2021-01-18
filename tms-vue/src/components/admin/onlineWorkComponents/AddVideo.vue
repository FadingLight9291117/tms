<template>
    <div>
        <el-card header="上传视频">

            <el-upload
                    ref="upload"
                    name="video"
                    :action="url"
                    :on-success="success"
                    :file-list="fileList"
                    :auto-upload="false">
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button style="margin-left: 10px" size="small" type="success" @click="upload">上传</el-button>
            </el-upload>
        </el-card>
    </div>
</template>

<script lang="ts">
    import {Vue, Component} from "vue-property-decorator";
    import {Message} from "element-ui";

    @Component
    export default class AddVideo extends Vue {
        url = "http://localhost:80/upload/video"
        fileList = []

        upload() {
            (this.$refs.upload as any).submit()
        }

        success() {
            Message.success("上传成功")
        }
    }
</script>

<style scoped>
    .el-card {
        margin: 20px 20px 0 20px;
    }
</style>

