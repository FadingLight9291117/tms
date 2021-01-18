<template>
    <div id="news">
        <el-row class="body" :gutter="24">
            <el-col :span="12">
                <el-card header="全部新闻" :body-style="{padding: 0}">
                    <el-table :data="allNews" v-loading="isLoading" height="480px">
                        <el-table-column type="index"></el-table-column>
                        <el-table-column label="发布日期">
                            <template slot-scope="scope">
                                <i class="el-icon-date"></i>
                                <span style="margin-left: 10px">{{scope.row.date}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="标题">
                            <template slot-scope="scope">
                                <el-popover trigger="hover" placement="top">
                                    {{scope.row.content}}
                                    <div slot="reference" class="name-wrapper">
                                        <el-tag size="medium">{{scope.row.title}}
                                        </el-tag>
                                    </div>
                                </el-popover>
                            </template>
                        </el-table-column>
                        <el-table-column label="选项">
                            <template slot-scope="scope">
                                <el-popconfirm
                                        title="确认要删除吗?"
                                        confirm-button-text="确认"
                                        cancel-button-text="取消"
                                        @onConfirm="remove(scope.$index, scope.row)"
                                        icon="el-icon-info"
                                        icon-color="red">

                                    <el-button slot="reference" type="danger" size="mini">删除
                                    </el-button>
                                </el-popconfirm>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-button type="primary" icon="el-icon-edit" circle class="big-button" @click="add">添加</el-button>
                <el-button type="danger" icon="el-icon-delete" circle class="big-button" @click="clear">清空
                </el-button>
                <el-button type="info" icon="el-icon-info" circle class="big-button">其他</el-button>
                <el-button icon="el-icon-share" circle class="big-button">分享</el-button>
            </el-col>
        </el-row>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import {Message} from "element-ui";

    @Component
    export default class News extends Vue {
        isLoading = false;
        allNews = [];

        remove(index: number, row: any) {
            this.isLoading = true;
            this.$axios
                .delete(`/news/rm/${row.id}`)
                .then(() => {
                    this.fetchNews();
                    Message.success("删除成功");
                })
                .catch(() => Message.error("网络连接错误"))
                .finally(() => this.isLoading = false);
        }

        clear() {
            this.isLoading = true;
            this.$axios
                .delete("/news/rmAll")
                .then(() => {
                    this.fetchNews();
                    Message.success("清空成功");
                })
                .finally(() => this.isLoading = false);
        }

        add() {
            this.$router.push({name: "editor"})
        }

        fetchNews() {
            this.isLoading = true;
            this.$axios
                .get("/news/all")
                .then(resp => this.allNews = resp.data)
                .catch(() => Message.error("网络连接错误"))
                .finally(() => this.isLoading = false);
        }

        mounted() {
            this.fetchNews();
        }
    }
</script>

<style scoped lang="scss">
    #news {
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
</style>