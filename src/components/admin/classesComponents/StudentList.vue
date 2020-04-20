<template>
    <el-card id="student-list" :body-style="{padding: 0}">
        <div slot="header">
            <span>全部学员</span>
            <el-popover
                    placement="bottom"
                    trigger="click"
                    title="添加学员"
                    v-model="showPopper"
            >
                <el-transfer
                        v-loading="isLoading"
                        filterable
                        :filter-method="filterMethod"
                        filter-placeholder="查询"
                        :data="allCandidateStudents"
                        v-model="newStudents"
                        :titles="['待选','已选']">
                </el-transfer>
                <el-row style="text-align: center">
                    <el-button type="primary" @click="addStudents">添加</el-button>
                </el-row>
                <el-button slot="reference"
                           style="float: right; padding: 3px 0"
                           type="text"
                           @click="openAddStudents"
                           :disabled="id === -1">添加
                </el-button>
            </el-popover>
        </div>
        <el-table class="table"
                  :data="tableData"
                  v-loading="isLoading"
                  stripe
                  border
                  max-height="500"
        >
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
            return this.classId as number;
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

        // 添加学员
        // 打开popper
        showPopper = false;
        allCandidateStudents: Array<any> = [];
        newStudents: Array<number> = [];

        filterMethod(query: string, item: any) {
            return item.label.indexOf(query) > -1;
        }

        openAddStudents() {
            this.$axios
                .get(`/student/allExceptClass/${this.id}`)
                .then(resp => {
                    resp.data.forEach((e: any) => {
                        this.allCandidateStudents.push({
                            key: e.id,
                            label: e.name
                        });
                    })
                })
                .catch(() => Message.error("网络连接错误"));
        }

        /**
         * 这里还存在一个小问题：添加教练后，如何使父组件中的table中的教练数量增加？
         */
        addStudents() {
            this.$axios
                .post("/class/addStudents", {
                    classId: this.id,
                    people: this.newStudents
                })
                .then(resp => {
                    if (resp.data === 1) {
                        Message.success("创建成功");
                        this.fetchTableData(this.id);
                    } else {
                        Message.error("网络连接错误");
                    }
                })
                .catch(() => Message.error("网络连接错误"))
                .finally(() => {
                    this.showPopper = false;
                    this.newStudents = [];
                });
        }
    }
</script>

<style scoped>
    #student-list {

    }
</style>