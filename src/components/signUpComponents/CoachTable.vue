<template>
    <div id="coachTable">
        <el-table :data="tableData" style="width: 100%" v-loading="isLoading" :height="height">
            <el-table-column type="expand">
                <template slot-scope="props">
                    <el-form label-position="right" label-width="90px" inline class="table-expand">
                        <el-form-item label="照片">
                            <span>{{props.row.picture}}</span>
                        </el-form-item>
                        <el-form-item label="性别">
                            <span>{{props.row.sex}}</span>
                        </el-form-item>
                        <el-form-item label="出生日期">
                            <span>{{  props.row.birthday }}</span>
                        </el-form-item>
                        <el-form-item label="联系方式">
                            <span>{{ props.row.phone }}</span>
                        </el-form-item>
                        <el-form-item label="段位">
                            <span>{{ props.row.dan }}</span>
                        </el-form-item>
                        <el-form-item label="简介">
                            <span>{{ props.row.intro }}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column label="报名日期">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.date }}</span>
                </template>
            </el-table-column>
            <el-table-column label="姓名">
                <template slot-scope="scope">
                    <i class="el-icon-user-solid"></i>
                    <span style="margin-left: 10px">{{ scope.row.name }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" type="primary" @click="handleAffirm(scope.$index, scope.row)">确认</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<style scoped>
    .table-expand {
        font-size: 0;
    }

    .table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 100%;
    }
</style>

<script lang="ts">
    import Vue from "vue";
    import Component from "vue-class-component";
    import {Message} from "element-ui";

    const CoachTableProps = Vue.extend({
        props: {
            height: {
                type: Number
            }
        }
    });

    @Component
    export default class CoachTable extends CoachTableProps {
        isLoading = true;

        tableData: Array<any> = [];

        getTableData() {
            this.$axios
                .get("/coach/candidates")
                .then(response => {
                    this.tableData = response.data.sort((a: any, b: any) => a.date > b.date ? 0 : 1);
                    this.isLoading = false;
                })
                .catch(error => Message({type: "error", message: "网络连接失败"}));
        }

        mounted() {
            this.getTableData();
        }

        handleDelete(index: number, row: any) {
            this.$axios
                .delete(`/coach/rmCoach/${row.id}`)
                .then(resp => {
                    if (resp.data === 1) {
                        alert("删除成功");
                        this.tableData.splice(index, 1);
                    }
                });
        }

        handleAffirm(index: number, row: any) {
            this.$axios
                .post(`/signUp/affirm`, {
                    students: [],
                    coaches: [row.id]
                })
                .then(resp => {
                    if (resp.data === 1) {
                        alert("确认成功");
                        this.tableData.splice(index, 1);
                    }
                })
        }


    }
</script>
