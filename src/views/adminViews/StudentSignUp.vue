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
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    }
</style>