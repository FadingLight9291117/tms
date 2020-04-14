<template>
    <el-card id="coach-list" :body-style="{padding: 0}">
        <div slot="header">
            <span>全部教练</span>
            <el-popover
                    placement="bottom"
                    trigger="click"
                    title="添加教练"
                    v-model="showPopper"
            >
                <el-transfer
                        v-loading="isLoading"
                        filterable
                        :filter-method="filterMethod"
                        filter-placeholder="查询"
                        :data="allCandidateCoaches"
                        v-model="newCoaches"
                        :titles="['待选','已选']">
                </el-transfer>
                <el-row style="text-align: center">
                    <el-button type="primary" @click="addCoaches">添加</el-button>
                </el-row>
                <el-button slot="reference"
                           style="float: right; padding: 3px 0"
                           type="text"
                           @click="openAddCoaches"
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
                    <el-button circle size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除
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
    export default class CoachList extends Vue {
        @Prop()
        classId: number | undefined;

        get id() {
            return this.classId as number;
        }

        set id(value: number) {
            this.$emit("update:classId", value);
        }

        isLoading = false;
        tableData: Array<any> = [];

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
                    .get(`/coach/classId/${id}`)
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

        // 添加教练
        // 打开popper
        showPopper = false;
        allCandidateCoaches: Array<any> = [];
        newCoaches: Array<number> = [];

        filterMethod(query: string, item: any) {
            return item.label.indexOf(query) > -1;
        }

        openAddCoaches() {
            this.$axios
                .get(`/coach/allExceptClass/${this.id}`)
                .then(resp => {
                    resp.data.forEach((e: any) => {
                        this.allCandidateCoaches.push({
                            key: e.id,
                            label: e.name
                        })
                    })
                })
                .catch(() => Message.error("网络连接错误"));
        }

        /**
         * 这里还存在一个小问题：添加教练后，如何使父组件中的table中的教练数量增加？
         */
        addCoaches() {
            this.$axios
                .post("/class/addCoaches", {
                    classId: this.id,
                    people: this.newCoaches
                })
                .then(resp => {
                    if (resp.data === 1) {
                        Message.success("添加成功")
                        this.fetchTableData(this.id);
                    } else
                        Message.error("网络连接错误")
                })
                .catch(() => Message.error("网络连接错误"))
                .finally(() => {
                    this.showPopper = false
                    this.newCoaches = [];
                });
        }

    }
</script>

<style scoped>
</style>