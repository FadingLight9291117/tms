<template>
    <div>
        <coach-table class="coach-table" :height="tableHeight"></coach-table>
    </div>
</template>

<script lang="ts">
    import Vue from "vue";
    import Component from "vue-class-component";
    import CoachTable from "@/components/signUpComponents/CoachTable.vue";


    @Component({
        components: {
            CoachTable
        }
    })
    export default class CoachSignUp extends Vue {
        tableHeight = 500;
    }
</script>

<style scoped>
    .coach-table {
        margin: 50px 50px 0 50px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    }

</style>