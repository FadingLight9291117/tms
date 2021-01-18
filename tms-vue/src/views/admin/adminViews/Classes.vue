<template>
    <div id="classes">
        <el-row>
            <el-col :span="24">
                <el-card :body-style="{padding: 0}">
                    <el-table :data="allClass" v-loading="classTableLoading" max-height="300">
                        <el-table-column label="创建时间">
                            <template slot-scope="scope">
                                <i class="el-icon-time"></i>
                                <span>{{ scope.row.date }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="班级名称">
                            <template slot-scope="scope">
                                <el-popover trigger="hover" placement="top">
                                    <p>描述: {{ scope.row.info }}</p>
                                    <div slot="reference">
                                        <el-tag size="medium">{{ scope.row.name }}</el-tag>
                                    </div>
                                </el-popover>
                            </template>
                        </el-table-column>
                        <el-table-column label="教练人数">
                            <template slot-scope="scope">
                                <i class="el-icon-notebook-1"></i>
                                <span>{{ scope.row.coachNum }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="学员人数">
                            <template slot-scope="scope">
                                <i class="el-icon-notebook-2"></i>
                                <span>{{ scope.row.num }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" @click="handleDetail(scope.$index, scope.row)">详情
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="9">
                <coach-list :class-id="classId" class="list coach-list"/>
            </el-col>
            <el-col :span="9">
                <student-list :class-id="classId" class="list student-list"/>
            </el-col>
            <el-col :span="6">
                <el-row>
                    <el-button type="primary" class="button1 button" @click="createClass()"><span class="button-words">创建班级</span>
                    </el-button>
                    <create-class v-if="show" :show.sync="show"/>
                </el-row>
                <el-row>
                    <el-button type="danger"
                               class="button2 button"
                               :disabled="classId === -1"
                               @click="deleteClass"><span
                            class="button-words">删除{{className}}</span>
                    </el-button>
                </el-row>
            </el-col>
        </el-row>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import StudentList from "@/components/admin/classesComponents/StudentList.vue";
    import CoachList from "@/components/admin/classesComponents/CoachList.vue";
    import {Message} from "element-ui";
    import CreateClass from "@/components/admin/classesComponents/CreateClass.vue";

    @Component({
        components: {
            StudentList,
            CoachList,
            CreateClass
        }
    })
    export default class Classes extends Vue {
        //显示创建班级dialog
        show = false;
        allClass = [];
        classTableLoading = false;
        showDetail = false;
        classId = -1;

        get className(): string {
            return this.classId != -1 ? (this.allClass.filter((e: any) => e.id === this.classId) as any)[0].name : "班级";
        }

        handleDetail(index: number, row: any) {
            this.classId = row.id;
        }

        fetchData() {
            this.classTableLoading = true;
            this.$axios
                .get("/classInfo/all")
                .then(resp => {
                    this.allClass = resp.data;
                    this.classTableLoading = false;
                })
                .catch(() => {
                    Message.error("网络连接错误");
                    this.classTableLoading = false;
                });
        }

        // 创建班级
        createClass() {
            this.show = true;
            const a = setInterval(() => {
                if (!this.show) {
                    this.fetchData();
                    clearInterval(a);
                }
            }, 1000)
        }

        // 删除选定班级
        deleteClass() {
            console.log(this.classId);
            if (this.classId != -1) {
                this.$axios
                    .delete(`/class/rmClass/${this.classId}`)
                    .then(resp => {
                        if (resp.data === 1) {
                            this.classId = -1;
                        }
                    }).finally(() => this.fetchData());
            }
        }

        mounted() {
            this.fetchData()
        }
    }
</script>

<style scoped lang="scss">
    #classes {
        margin: 20px 20px 0 20px;
    }

    .el-row {
        margin-bottom: 20px;

        &:last-child {
            margin-bottom: 0;
        }


        text-align: center;
    }

    .button {
        height: 200px;
        width: 100%;
    }


    .button-words {
        font-size: large;
    }
</style>