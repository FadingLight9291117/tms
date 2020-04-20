<template>
    <div>
        <el-card
                class="card"
                :body-style="{padding: 0}"
        >
            <template slot="header">
                全部教练(<span style="color: red">{{tableData.length}}</span>)
            </template>
            <el-table
                    :data="tableData"
                    v-loading="tableIsLoading"
                    height="480px"
            >
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="date" label="日期"></el-table-column>
                <el-table-column label="照片">
                    <template slot-scope="scope">
                        <img v-if="scope.row.picture === ''" width="50" height="50" src="../../../assets/head.jpeg" alt=""/>
                    </template>
                </el-table-column>
                <el-table-column prop="name" label="姓名"></el-table-column>
                <el-table-column prop="dan" label="段位"></el-table-column>
                <el-table-column label="详细信息">
                    <template slot-scope="scope">
                        <el-button type="primary" size="mini" @click="handleDetail(scope.$index, scope.row)">查看
                        </el-button>
                        <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <el-dialog
                title="详情"
                :visible.sync="showDialog"
                width="30%"
        >
            <el-form
                    label-position="right"
                    label-width="90px"
            >
                <el-form-item v-for="(value, key) in tableData[dialogIndex]" :key="key" :label="key">
                    <span v-if="key !== 'picture'" style="color: black">{{value}}</span>
                    <img v-else-if="value === ''" width="100" height="100" src="../../../assets/head.jpeg" alt=""/>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button type="primary" size="mini" @click="showDialog = false">确认</el-button>
            </span>

        </el-dialog>
    </div>
</template>

<script lang="ts">
    import {Vue, Component} from "vue-property-decorator";
    import {Message} from "element-ui";

    @Component
    export default class CoachInfo extends Vue {
        tableIsLoading = false;
        showDialog = false;
        dialogIndex = 0;
        tableData = [];

        handleDetail(index: number, row: any) {
            this.dialogIndex = index;
            this.showDialog = true;
        }

        handleDelete(index: number, row: any) {
            this.tableData.splice(index, 1);
            // ajax操作
        }

        fetchStudent() {
            this.tableIsLoading = true;
            this.$axios
                .get("/coach/all")
                .then(resp => this.tableData = resp.data)
                .catch(() => Message.error("网络连接错误"))
                .finally(() => this.tableIsLoading = false);
        }

        mounted() {
            this.fetchStudent();
        }
    }
</script>

<style scoped>
    .card {
        margin: 20px 20px 0 20px;
    }
</style>