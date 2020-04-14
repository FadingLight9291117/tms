<template>
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
</template>

<script lang="ts">
    import {Vue, Component, Prop, Watch} from "vue-property-decorator";
    import CourseColumn from "@/model/CourseTableClass";


    @Component
    export default class CourseTable extends Vue {
        @Prop() loading: boolean | undefined;
        // table 数据源
        data: Array<any> = [
            {
                week: "周一",
                one: {
                    id: 10,
                    name: "白带2班"
                },
                three: {
                    id: 20,
                    name: "黑带3班"
                }
            },
            {
                week: "周二",
                three: {
                    id: 2,
                    name: "红带3班"
                }
            }
            // new CourseColumn("周一"),
            // new CourseColumn("周二"),
            // new CourseColumn("周三"),
            // new CourseColumn("周四"),
            // new CourseColumn("周五"),
            // new CourseColumn("周六"),
            // new CourseColumn("周日")
        ];

        // 处理点击单元格
        weekList = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"];

        handleClick(row: any, column: any) {
            const rowNum = this.weekList.indexOf(row.week);
            const colName = column.property.split(".")[0];
            if (colName !== "week") {
                const cell = Reflect.get(this.data[rowNum], colName);
                // 存在课程的cell
                if (cell) {
                    const id = cell.id;
                    console.log(id);
                    // 弹出框
                }
                // 不存在课程的cell
                else {
                    console.log("不存在课程");
                }

            }
        }

        fetchData() {
            1
        }

        @Watch("loading")
        isLoading(value: boolean) {
            if (value) {
                this.fetchData();
                this.$emit("update:loading", false);
            }
        }
    }
</script>

<style scoped>

</style>