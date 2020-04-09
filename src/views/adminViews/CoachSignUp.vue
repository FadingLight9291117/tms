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
    }

</style>