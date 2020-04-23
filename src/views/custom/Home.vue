<template>
    <div id="home">
        <header style="width: 100%">
            <el-menu
                    :default-active="activeIndex"
                    mode="horizontal"
                    @select="handleSelect"
                    class="menu"
                    background-color="#24292e"
                    text-color="#fff"
                    active-text-color="#ffd04b"
            >
                <el-menu-item index="home">首页</el-menu-item>
                <el-menu-item style="float: right" @click="handleSignUp"><span class="sign-up">报名</span>
                </el-menu-item>
                <el-menu-item style="float: right">登录</el-menu-item>
            </el-menu>
        </header>
        <div id="index" class="bg">
            <h1>跆拳道</h1>
            <h1>TAEKWONDO</h1>
            <p>礼义廉耻&nbsp;忍耐克己&nbsp;百折不屈</p>
            <p>ETIQUETTE, RIGHTEOUSNESS, SHAME, PATIENCE, SELF DENIAL, UNYIELDING</p>
            <el-button type="primary" size="medium" @click="handleSignUp">立即报名</el-button>
        </div>

        <div id="news">
            <el-tag type="primary" effect="dark" size="medium"> 新闻</el-tag>
            <span v-for="(item, index) in news" :key="index" style="color: #086dc3;">
                {{item.title}}
                <el-divider v-if="index !== news.length -1" direction="vertical"></el-divider></span>
        </div>
        <el-divider></el-divider>

        <div id="advantage">
            <h1>What can you learn from here ?</h1>
            <el-row :gutter="24">
                <el-col :span="16">
                    <img src="../../assets/tk.gif" alt="加载失败">
                </el-col>
                <el-col :span="8" style="">
                    <h2>Level up your body ability</h2>
                    <p>提高免疫力，增强体质</p>
                    <br>
                    <h2>Enhance your confidence</h2>
                    <p>增强自信，积极生活</p>
                    <br>
                    <h2>Learn etiquette</h2>
                    <p>学习礼仪，以礼始终</p>
                </el-col>
            </el-row>

        </div>

        <div id="coach">
            <h1>We have many great coaches !</h1>
            <br>
            <div class="cards">
                <el-card v-for="i in 7" :key="i" shadow="hover" :body-style="{padding: 0}">
                    <img class="coach-img" src="../../assets/head.jpeg" alt="加载失败">
                    <div style="padding: 10px">
                        <p>车亮召</p>
                        <p style="color: #999">黑带一段，十年教学经验，2008奥运冠军</p>
                    </div>
                </el-card>
            </div>
        </div>

        <footer>

        </footer>

    </div>
</template>

<script lang="ts">
    import Vue from "vue";
    import Component from "vue-class-component";
    import {Message} from "element-ui";

    @Component
    export default class Home extends Vue {
        activeIndex = "home";

        handleSelect(key: string) {
            this.$router.push({name: key})
        }

        handleSignUp() {
            this.$router.push({name: "signUp"})
        }

        news = [
            {
                title: "考试 两个班级",
                content: "明天考试"
            },
            {
                title: "报名 赶快报名 明天截止",
                content: "明天开始报名"
            }
        ];

        coaches = [];

        fetchNews() {
            this.$axios
                .get("/news/all")
                .then(resp => this.news = resp.data)
                .catch(() => Message.error("网络连接错误"));
        }

    }
</script>

<style scoped lang="scss">
    #home {
        text-align: center;
        height: 100%;
        width: 100%;
    }

    .el-menu.el-menu--horizontal {
        border-bottom: none;
    }

    .menu {
        text-align: left;
        padding-left: 30px;
    }

    .sign-up {
        border-radius: 10px;
        border: 1px solid;
        padding: 10px;
        margin: 0;
    }

    .bg {
        height: 500px;
        background: url(../../assets/simple-codelines.svg) #282c34;
        color: #fff;
        padding-top: 60px;
        letter-spacing: 0.01em;
        font-weight: 400;
        font-size: 30px;
        line-height: 1.15;
    }

    .bg p {
        color: hsla(0, 0%, 100%, .6);
    }

    .bg .el-button {
        height: 50px;
        width: 160px;
        font-size: 20px;
    }

    #news {
        padding-top: 20px;
    }

    #news .el-tag {
        width: 80px;
        font-size: 18px;
    }

    #news .el-tag, span {
        margin: 0 20px;
    }

    .el-divider--vertical {
        margin-left: 40px;
    }

    #advantage p {
        color: #333;
    }

    #coach, #advantage {
        text-align: left;
        margin: 80px;
    }

    .cards {
        text-align: center;
    }

    .cards .el-card {
        display: inline-block;
        width: 250px;
        height: 400px;
        margin-right: 40px;
        margin-bottom: 20px;

        &:last-child {
            margin-left: 0;
        }
    }

    .coach-img {
        width: 100%;
        display: block;
    }

    footer {
        height: 400px;
        background: #282c34;
    }
</style>
