<template>
    <div id="course-table">
        <el-table
                :data="data"
                width="100%"
                v-loading="tableIssLoading"
                @cell-click="handleClick">
            <el-table-column prop="week" label="星期"></el-table-column>
            <el-table-column label="上午">
                <el-table-column prop="one.className" label="8:00-9:50"></el-table-column>
                <el-table-column prop="two.className" label="10:00-11:50"></el-table-column>
            </el-table-column>
            <el-table-column label="下午">
                <el-table-column prop="three.className" label="12:30-2:20"></el-table-column>
                <el-table-column prop="four.className" label="2:30-4:20"></el-table-column>
                <el-table-column prop="five.className" label="4:30-6:20"></el-table-column>
            </el-table-column>
            <el-table-column label="晚上">
                <el-table-column prop="six.className" label="7:00-8:50"></el-table-column>
            </el-table-column>
        </el-table>
        <el-dialog title="操作"
                   :visible.sync="openDialog"
                   width="30%"
        >
            <el-row>
                <span>{{week}}第{{course}}节</span>
            </el-row>
            <br>
            <el-row>
                <el-popover
                        :disabled="disablePopover"
                        placement="bottom"
                        trigger="click">
                    <el-table :data="classes" @row-click="createOrUpdateCourse">
                        <el-table-column label="班级名" prop="name">
                        </el-table-column>
                    </el-table>
                    <el-button
                            type="primary"
                            :disabled="create"
                            @click="fetchClassData"
                            slot="reference">
                        添加
                    </el-button>
                </el-popover>
                <el-popover
                        style="margin-left: 10px"
                        :disabled="disablePopover"
                        placement="bottom"
                        trigger="click">
                    <el-table :data="classes" @row-click="createOrUpdateCourse">
                        <el-table-column label="班级名" prop="name">
                        </el-table-column>
                    </el-table>

                    <el-button :disabled="update"
                               @click="fetchClassData"
                               slot="reference">
                        修改
                    </el-button>
                </el-popover>
                <el-button
                        style="margin-left: 10px"
                        type="danger"
                        :disabled="remove"
                        @click="removeCourse">
                    删除
                </el-button>
            </el-row>
        </el-dialog>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue, Watch} from "vue-property-decorator";
    import CourseColumn from '@/model/CourseTableClass';
    import {Message} from "element-ui";


    @Component
    export default class CourseTable extends Vue {
        @Prop() loading: boolean | undefined;
        tableIssLoading = false;
        // table 数据源
        data: Array<CourseColumn> = [
            // {
            //     week: "周一",
            //     one: {
            //         id: 10,
            //         name: "白带2班"
            //     },
            //     three: {
            //         id: 20,
            //         name: "黑带3班"
            //     }
            // },
            // {
            //     week: "周二",
            //     three: {
            //         id: 2,
            //         name: "红带3班"
            //     }
            // }
            new CourseColumn("周一"),
            new CourseColumn("周二"),
            new CourseColumn("周三"),
            new CourseColumn("周四"),
            new CourseColumn("周五"),
            new CourseColumn("周六"),
            new CourseColumn("周日")
        ];

        // 处理点击单元格
        weekList = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"];
        courseList = {
            "one": "一",
            "two": "二",
            "three": "三",
            "four": "四",
            "five": "五",
            "six": "六"
        };

        // 打开dialog
        openDialog = false;
        // 三个按钮是否可用
        create = true;
        update = true;
        remove = true;
        // 点击的单元格信息
        week = "";
        time = "";
        course = "";
        // 点击单元格的id
        courseId = -1;

        //popover可见性
        disablePopover = true;

        // 添加或修改班级班级，若存在班级先删除
        createOrUpdateCourse(row: any) {
            if (this.courseId !== -1) {
                this.removeCourse();
            }
            const course = {
                classId: row.id,
                week: this.week,
                time: this.time
            }
            this.$axios
                .post("/course/add", course)
                .then(resp => resp.data === 1 ? Message.success("添加成功") : Message.error("网络连接错误"))
                .catch(() => Message.error("网络连接错误"))
                .finally(() => {
                    this.disablePopover = true;
                    this.openDialog = false;
                    this.fetchCourseData();
                })
        }

        removeCourse() {
            this.$axios
                .delete(`/course/rm/${this.courseId}`)
                .then(resp => resp.data === 1 ? Message.success("删除成功") : Message.error("网络连接错误"))
                .catch(() => Message.error("网络连接错误"))
                .finally(() => {
                    this.disablePopover = true;
                    this.openDialog = false;
                    this.fetchCourseData();
                })
        }

        handleClick(row: any, column: any) {
            // 行数
            const rowNum = this.weekList.indexOf(row.week);
            // 列名
            const colName = column.property.split(".")[0];
            if (colName !== "week") {
                this.openDialog = true
                // 周数
                this.week = row.week;
                // 列名
                this.time = colName;
                // 第几次课
                this.course = Reflect.get(this.courseList, colName);
                // cell中课程id
                const cell = Reflect.get(this.data[rowNum], colName);
                // 存在课程的cell
                if (cell) {
                    this.create = true;
                    this.update = false;
                    this.remove = false;
                    this.courseId = cell.id;
                    // 弹出框
                }
                // 不存在课程的cell
                else {
                    this.courseId = -1;
                    this.create = false;
                    this.update = true;
                    this.remove = true;
                }
            }
        }

        classes: Array<any> = [];

        fetchClassData() {
            this.disablePopover = false;
            this.$axios
                .get("/classInfo/all")
                .then(resp => {
                    const data: Array<any> = resp.data;
                    this.classes = data.map((e: any) => {
                        return {
                            id: e.id,
                            name: e.name
                        }
                    });
                })
                .catch(() => Message.error("网络连接错误"));
        }

        fetchCourseData() {
            this.tableIssLoading = true;
            this.$axios
                .get("/course/allAndClassName")
                .then(resp => {
                    const data = resp.data;
                    // 先清空数据
                    this.data.forEach(e => e.setOtherParamsNull());
                    data.forEach((e: any) => {
                        switch (e.week) {
                            case "周一":
                                this.data[0].setOtherParams(e);
                                break;
                            case "周二":
                                this.data[1].setOtherParams(e);
                                break;
                            case "周三":
                                this.data[2].setOtherParams(e);
                                break;
                            case "周四":
                                this.data[3].setOtherParams(e);
                                break;
                            case "周五":
                                this.data[4].setOtherParams(e);
                                break;
                            case "周六":
                                this.data[5].setOtherParams(e);
                                break;
                            case "周日":
                                this.data[6].setOtherParams(e);
                                break;
                        }
                    });
                })
                .catch(() => Message.error("网络连接错误"))
                .finally(() => this.tableIssLoading = false);
        }

        mounted() {
            this.fetchCourseData();
        }

        // 监听prop
        @Watch("loading")
        isLoading(value: boolean) {
            if (value) {
                this.fetchCourseData();
                this.$emit("update:loading", false);
            }
        }
    }
</script>

<style scoped>
    #course-table {
        text-align: center;
    }
</style>