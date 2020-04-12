<template>
    <el-card id="student-list" :body-style="{padding: 0}">
        <div slot="header">
            <span>全部学员</span>
            <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
        </div>
        <el-table class="table"
                  :data="tableData"
                  v-loading="isLoading"
                  stripe
                  border>
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
            <el-table-column label="选项">
                <template slot-scope="scope">
                    <el-button circle size="mini" @click="handleInfo(scope.$index, scope.row)">查看</el-button>
                    <el-button circle type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>
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
        tableData = [];
        selectStudents = [];

        handleInfo(index: number, row: any) {
            1
        }

        handleDelete(index: number, row: any) {
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
    #student-list {

    }
</style>