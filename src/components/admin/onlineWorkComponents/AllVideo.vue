<template>
    <div>
        <el-card header="所有视频" :loading="isLoading">
            <video class="video" v-for="url in urls" :key="url" width="200" height="200" :src="url"></video>
        </el-card>
    </div>
</template>

<script lang="ts">
    import {Vue, Component} from "vue-property-decorator";
    import {Message} from "element-ui";

    @Component
    export default class AllVideo extends Vue {
        isLoading = false;
        urls = [];

        mounted() {
            this.isLoading = true;
            this.$axios
                .get("/file/videos")
                .then(resp => this.urls = resp.data)
                .catch(() => Message.error("网络连接错误"))
                .finally(() => this.isLoading = false)
        }
    }
</script>

<style lang="scss" scoped>
    .video {
        float: left;
        margin-left: 20px;
        margin-bottom: 10px;
    }
    .el-card {
        margin: 20px 20px 0 20px;
    }

</style>