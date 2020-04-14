<template>
    <div>
        <el-card class="course-table" :body-style="{padding: 0}">
            <div slot="header">
                <span style="font-size: large">课程表</span>
                <el-button style="float: right;padding: 3px" type="text" size="medium" round>清空</el-button>
            </div>
            <course-table :loading.sync="tableLoading"/>
        </el-card>
    </div>
</template>
<script lang="ts">
    import {Vue, Component} from "vue-property-decorator";
    import CourseTable from "@/components/courseComponents/CourseTable.vue";

    @Component({
        components: {
            CourseTable
        }
    })
    export default class Course extends Vue {
        tableLoading = true;
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
