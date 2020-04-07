<template>
    <div>
        <router-link :to="{ name: 'signUpInfo' }">to</router-link>
    </div>
</template>
<script lang="ts">
    import Vue from "vue";
    import Component from "vue-class-component";

    @Component
    export default class Test extends Vue {

    }
</script>