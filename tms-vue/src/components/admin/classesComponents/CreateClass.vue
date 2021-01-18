<template>
    <el-dialog title="创建班级" :visible.sync="isShow" style="text-align: left">
        <el-steps :active="whichStep" finish-status="success" style="margin-bottom: 20px">
            <el-step title="填写班级信息"></el-step>
            <el-step title="分配教练"></el-step>
            <el-step title="分配学员"></el-step>
        </el-steps>
        <el-form ref="form" :model="form" v-if="whichStep === 0" status-icon>
            <el-form-item label="名称(必填)" prop="name" :rules="[ {required: true, message: '名称不能为空'} ]">
                <el-input v-model="form.name" placeholder="请填写班级名称"></el-input>
            </el-form-item>
            <el-form-item label="简介(选填)">
                <el-input v-model="form.info" type="textarea" placeholder="请填写简介"></el-input>
            </el-form-item>
            <el-button @click="affirm1()" type="primary">{{nextStepInfo}}</el-button>
        </el-form>
        <div v-if="whichStep === 1">
            <el-transfer
                    v-loading="isLoading"
                    filterable
                    :filter-method="filterMethod"
                    filter-placeholder="查询"
                    :data="allCoaches"
                    v-model="selectCoaches"
                    :titles="['待选','已选']">
            </el-transfer>
            <br>
            <el-button @click="affirm2()" type="primary">{{nextStepInfo}}</el-button>
        </div>
        <div v-if="whichStep === 2">
            <el-transfer
                    v-loading="isLoading"
                    filterable
                    :filter-method="filterMethod"
                    filter-placeholder="查询"
                    :data="allStudents"
                    v-model="selectStudents"
                    :titles="['待选','已选']">
            </el-transfer>
            <br>
            <el-button @click="affirm3()" type="primary" v-loading.fullscreen.lock="fullscreenLoading">
                {{nextStepInfo}}
            </el-button>
        </div>
        <div v-if="whichStep === 3" class="finish">
            <i class="el-icon-success"></i>
            <span>创建完成</span>
            <br>
            <el-button round size="larger" type="primary" @click="affirm4()">{{nextStepInfo}}</el-button>
        </div>
    </el-dialog>
</template>

<script lang="ts">
    import {Vue, Component, Prop} from "vue-property-decorator";
    import {Message} from "element-ui";

    @Component
    export default class CreateClass extends Vue {
        fullscreenLoading = false;
        isLoading = false;
        @Prop() show: boolean | undefined;

        get isShow() {
            return this.show as boolean;
        }

        set isShow(value: boolean) {
            this.$emit("update:show", value);
        }

        form = {
            name: "",
            info: "",
            date: "",
            num: 0,
            coachNum: 0
        };
        allCoaches: Array<any> = [];
        allStudents: Array<any> = [];
        selectCoaches: Array<number> = [];
        selectStudents: Array<number> = [];

        // 第几步
        whichStep = 0;

        get nextStepInfo() {
            return this.whichStep != 3 ? "下一步" : "确认";
        }

        // 下一步
        nextStep() {
            if (this.whichStep++ > 2) {
                this.whichStep = 0;
                this.isShow = false;
            }
        }

        //第一步
        // 第一步确认
        affirm1() {
            (this.$refs.form as any).validate((valid: any) => {
                if (valid) {
                    this.nextStep();
                    this.fetchCoaches();
                } else
                    return false;
            })
        }

        // 第二步
        filterMethod(query: string, item: any) {
            return item.label.indexOf(query) > -1;
        }

        fetchCoaches() {
            this.isLoading = true;
            this.$axios
                .get("/coach/all")
                .then(resp => {
                    resp.data.forEach((e: any) => {
                        this.allCoaches.push({
                            key: e.id,
                            label: e.name
                        });
                    });
                })
                .catch(() => Message.error("网络连接出错"))
                .finally(() => this.isLoading = false);
        }

        // 第二步确认
        affirm2() {
            this.nextStep();
            this.fetchStudents();
        }

        // 第三步
        fetchStudents() {
            this.isLoading = true;
            this.$axios
                .get("/student/all")
                .then(resp => {
                    resp.data.filter((e: any) => {
                        this.allStudents.push({
                            key: e.id,
                            label: e.name
                        });
                    });
                })
                .catch(() => Message.error("网络连接错误"))
                .finally(() => this.isLoading = false);
        }

        // 第三步确认
        affirm3() {
            this.submitData();
            // this.nextStep();
        }

        submitData() {
            this.fullscreenLoading = true;
            const date = new Date();
            this.form.date = `${date.getFullYear()}-${date.getMonth()}-${date.getDate()}`;
            this.$axios
                .post("/class/addClassAndPeople", {
                    cClass: this.form,
                    coaches: this.selectCoaches,
                    students: this.selectStudents
                })
                .then(() => {
                    this.nextStep();
                })
                .catch(() => Message.error("网络连接错误,请重试"))
                .finally(() => {
                    this.fullscreenLoading = false;
                });
        }

        affirm4() {
            this.nextStep();
        }
    }
</script>

<style scoped>
    .finish {
        text-align: center;
        font-size: 60px;
        color: green
    }
</style>