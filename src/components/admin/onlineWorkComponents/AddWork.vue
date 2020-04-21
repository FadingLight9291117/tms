<template>
    <div>
        <form action="http://localhost:80/upload/img" method="POST" enctype="multipart/form-data">
            <input type="file" name="img">
            <input type="submit" value="上传">
        </form>
    </div>
</template>

<script lang="ts">
    import {Vue, Component} from "vue-property-decorator";

    @Component
    export default class AddWork extends Vue {
        formData = [];
    }
</script>

