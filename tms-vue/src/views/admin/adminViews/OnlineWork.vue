<template>
    <div>
        <el-row>
            <el-card :body-style="{padding: 0}" header="全部班级">
                <el-table ref="classTable" :data="allClass" v-loading="classTableLoading" max-height="300"
                          @selection-change="handleSelectionChange">
                    <el-table-column type="selection"></el-table-column>
                    <el-table-column type="index"></el-table-column>
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
                    <el-table-column label="选项">
                        <template slot-scope="scope">
                            <el-button size="mini" type="info" @click="handleOldWorks(scope.$index, scope.row)">往期作业
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="margin: 10px 0 10px 10px">
                    <el-button @click="cancelSelection">取消选择</el-button>
                </div>
            </el-card>
        </el-row>
        <el-row>
            <el-button type="primary" icon="el-icon-edit" circle class="big-button" :disabled="isDisable"
                       @click="publishWork">布置作业
            </el-button>
            <el-button type="info" icon="el-icon-info" circle class="big-button" @click="addVideo">上传视频</el-button>
            <el-button icon="el-icon-share" circle class="big-button" @click="handleAllVideo">所有视频</el-button>
        </el-row>
        <el-dialog :visible.sync="showDialog" title="请选择视频">
            <el-row>
                <video class="video" width="200" height="200" v-for="item in videos" :key="item"
                       @click="selectVideo(item)"
                       :src="item"></video>
            </el-row>
            <el-row>
                <el-button @click="showDialog = false">取消</el-button>
            </el-row>
        </el-dialog>
    </div>
</template>

<script lang="ts">
    import {Vue, Component, Watch} from "vue-property-decorator";
    import {Message} from "element-ui";

    @Component
    export default class OnlineWork extends Vue {
        allClass = []
        classTableLoading = false
        multipleSelection = []
        // 布置作业按钮是否可用
        isDisable = true
        showDialog = false

        selected = {
            video: "",
            courses: [],
            date: ""
        }
        videos = [];

        selectVideo(val: string) {
            this.selected.video = val;
            this.handlePublish();
        }

        publishWork() {
            this.showDialog = true;
            this.$axios
                .get("/file/videos")
                .then(resp => this.videos = resp.data)
                .catch(() => Message.error("网络连接错误"))

        }

        handlePublish() {
            (this.selected.courses as Array<number>) = this.multipleSelection.map((item: any) => item.id);
            const date = new Date();
            this.selected.date = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
            this.$axios
                .post("/onlineWork/publish", this.selected)
                .then(resp => {
                    if (resp.data) {
                        Message.success("发布成功");
                    } else {
                        Message.error("网络连接错误");
                    }
                })
                .catch(() => Message.error("网络连接错误"));
            this.showDialog = false;
            this.cancelSelection();
        }

        handleAllVideo() {
            this.$router.push({name: "allVideo"})
        }


        handleSelectionChange(val: any) {
            this.multipleSelection = val;
            this.isDisable = this.multipleSelection.length == 0
        }

        cancelSelection() {
            (this.$refs.classTable as any).clearSelection();
        }

        // 上传视频
        addVideo() {
            this.$router.push({name: "addVideo"});
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

        mounted() {
            this.fetchData();
        }
    }
</script>

<style scoped lang="scss">
    .el-row {
        margin: 20px 20px 0 20px;
    }

    .big-button {
        width: 100px;
        height: 100px;
        float: left;
        margin-right: 20px;
        margin-bottom: 20px;

        &:first-child {
            margin-left: 10px;
        }
    }

    .video {
        float: left;
        margin-left: 20px;
        margin-bottom: 10px;
    }
</style>