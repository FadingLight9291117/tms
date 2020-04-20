<template>
    <div id="myChart" style="height: 100%"></div>
</template>

<script lang="ts">
    import echarts from "echarts"
    import Vue from "vue";
    import Component from "vue-class-component";
    import {Route} from "vue-router";

    @Component
    export default class SexChart extends Vue {
        options = {
            title: {
                text: "男女比例",
                left: "center",
                top: 20,
                textStyle: {
                    color: "#ccc"
                }
            },
            tooltip: {},
            xAxis: {
                data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };



        mounted() {
            // 基于准备好的dom，初始化echarts实例
            const myChart = echarts.init(document.getElementById('myChart') as HTMLCanvasElement);
            // 绘制图表
            myChart.setOption(this.options);
        }
    }
</script>

<style scoped>

</style>