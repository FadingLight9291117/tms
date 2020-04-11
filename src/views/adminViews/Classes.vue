<template>
    <div>
        <el-table class="classTable class" :data="allClass" v-loading="classTableLoading">
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
                    <el-button type="primary" size="mini" @click="handleDetail(scope.$index, scope.row)">详情</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="list">
            <coach-list class-id="-1" class="class coach-list"/>
            <br>
            <student-list class-id="-1" class="class student-list"/>
        </div>
    </div>
</template>

<script lang="ts">
    import {Vue, Component} from "vue-property-decorator";
    import StudentList from "@/components/classesComponents/StudentList.vue";
    import CoachList from "@/components/classesComponents/CoachList.vue";

    @Component({
        components: {
            StudentList,
            CoachList
        }
    })
    export default class Classes extends Vue {
        allClass = [
            {
                date: "2020-2-4",
                name: "黑带1班",
                num: 20,
                coachNum: 2,
                info: "最好的班级",

            }
        ];
        classTableLoading = false;
        showDetail = false;
        classId = -1;

        handleDetail(index: number, row: any) {
            this.classId = row.id;
        }
    }
</script>

<style scoped>
    .class {
        margin-top: 30px;
    }

    .list {
        width: 50%;
        float: left
    }
</style>