<template>
    <div id="signUpInfo">
        <student-table class="student-table" :height="tableHeight"/>
    </div>
</template>

<script lang="ts">
    import Vue from "vue";
    import Component from "vue-class-component";
    import StudentTable from "@/components/signUpComponents/StudentTable.vue";

    @Component({
            components: {
                StudentTable
            }
        }
    )
    export default class StudentSignUp extends Vue {
        tableHeight = 500;
    }
</script>

<style scoped>
    .student-table {
        margin: 50px 50px 0 50px;
    }
</style>