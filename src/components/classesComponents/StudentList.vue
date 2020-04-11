<template>
    <div id="student-list">
        <el-button type="primary" round style="width: 100%">添加学员</el-button>
        <el-table class="table"
                  :data="tableData"
                  v-loading="isLoading"
                  stripe>
            <el-table-column label="照片">
                <template slot-scope="scope">
                    <span>{{scope.row.picture}}</span>
                </template>
            </el-table-column>
            <el-table-column label="姓名">
                <template slot-scope="scope">
                    <i class="el-icon-user"></i>
                    <span>{{scope.row.name}}</span>
                </template>
            </el-table-column>
            <el-table-column label="个人信息">
                <template slot-scope="scope">
                    <el-button round size="mini" @click="handleInfo(scope.$index, scope.row)">查看
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button type="danger"  style="width: 100%" round :disabled="selectStudents.length === 0">删除学员</el-button>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue, Watch} from "vue-property-decorator";
    import {Message} from "element-ui";

    @Component
    export default class StudentList extends Vue {
        @Prop()
        classId: number | undefined;

        get id() {
            return this.classId;
        }

        isLoading = false;
        tableData = [
            {
                name: "陈诗琦",
                picture: ""
            },
            {
                name: "陈诗琦",
                picture: ""
            }];
        selectStudents = [];

        handleInfo(index: number, row: any) {
            1
        }

        fetchTableData(id: number) {
            if (id != -1) {
                this.isLoading = true;
                this.$axios
                    .get(`/student/classId/${id}`)
                    .then(resp => {
                        this.tableData = resp.data;
                        this.isLoading = false;
                    })
                    .catch(() => {
                        Message({type: "error", message: "网络连接失败"});
                        this.isLoading = false;
                    })
            } else {
                this.tableData = [];
            }
        }

        @Watch("classId")
        onChangeClassId(newValue: number) {
            this.fetchTableData(newValue);
        }

        // mounted() {
        //     this.fetchTableData(this.id as number);
        // }

    }
</script>

<style scoped>

</style>