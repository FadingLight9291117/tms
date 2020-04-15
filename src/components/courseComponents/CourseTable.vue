<template>
    <div>
        <el-table
                :data="data"
                width="100%"
                :isLoading="loading"
                @cell-click="handleClick">
            <el-table-column prop="week" label="星期"></el-table-column>
            <el-table-column label="上午">
                <el-table-column prop="one.name" label="8:00-9:50"></el-table-column>
                <el-table-column prop="two.name" label="10:00-11:50"></el-table-column>
            </el-table-column>
            <el-table-column label="下午">
                <el-table-column prop="three.name" label="12:30-2:20"></el-table-column>
                <el-table-column prop="four.name" label="2:30-4:20"></el-table-column>
                <el-table-column prop="five.name" label="4:30-6:20"></el-table-column>
            </el-table-column>
            <el-table-column label="晚上">
                <el-table-column prop="six.name" label="7:00-8:50"></el-table-column>
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
                <el-button
                        type="primary"
                        :disabled="create"
                        @click="createCourse">
                    添加
                </el-button>
                <el-button :disabled="update"
                           @click="updateCourse">
                    修改
                </el-button>
                <el-button type="danger"
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
        course = "";
        // 点击单元格的id
        courseId = -1;

        createCourse() {
            1
        }

        updateCourse() {
            1
        }

        removeCourse() {
            1
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
                    this.create = false;
                    this.update = true;
                    this.remove = true;
                }
            }
        }

        classes: Array<any> = [];

        fetchClassData() {
            this.$axios
                .get("/class/allClass")
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
            this.$axios
                .get("/course/allAndClassName")
                .then(resp => {
                    const data = resp.data;
                    data.forEach((e: any) => {
                        switch (e.week) {
                            case "周一":
                                this.data[0].setOtherPrams(e);
                                break;
                            case "周二":
                                this.data[1].setOtherPrams(e);
                                break;
                            case "周三":
                                this.data[2].setOtherPrams(e);
                                break;
                            case "周四":
                                this.data[3].setOtherPrams(e);
                                break;
                            case "周五":
                                this.data[4].setOtherPrams(e);
                                break;
                            case "周六":
                                this.data[5].setOtherPrams(e);
                                break;
                            case "周日":
                                this.data[6].setOtherPrams(e);
                                break;
                        }
                    });
                })
                .catch(() => Message.error("网络连接错误"));
        }

        mounted() {
            this.fetchCourseData();
        }

        // 监听prop
        @Watch("loading")
        isLoading(value: boolean) {
            if (value) {
                // 使整个表格重新加载
                // this.fetchData();
                this.$emit("update:loading", false);
            }
        }
    }
</script>

<style scoped>

</style>