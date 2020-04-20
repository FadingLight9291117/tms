<template>
    <div>
        <el-card class="course-table" :body-style="{padding: 0}">
            <div slot="header">
                <span style="font-size: large">课程表</span>
                <el-button style="float: right;padding: 3px" type="text" size="medium" round @click="clear">清空
                </el-button>
            </div>
            <course-table :loading.sync="tableLoading"/>
        </el-card>
    </div>
</template>
<script lang="ts">
    import {Vue, Component} from "vue-property-decorator";
    import CourseTable from "@/components/admin/courseComponents/CourseTable.vue";
    import {Message} from "element-ui";

    @Component({
        components: {
            CourseTable
        }
    })
    export default class Course extends Vue {
        tableLoading = false;

        // 清空所有课程
        clear() {
            this.$axios
                .delete("/course/clear")
                .then(resp => resp.data === 1 ? Message.success("清空成功") : Message.error("网络连接错误"))
                .catch(() => Message.error("网络连接错误"))
                .finally(() =>{
                    this.tableLoading = true;
                });
        }
    }
</script>

<style scoped>
    .el-card__header {
        background-color: rgb(84, 92, 100);
    }

    .course-table {
        margin: 20px 20px 0 20px;
    }
</style>
