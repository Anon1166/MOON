<script setup>
import AnnouncementForm from './AnnouncementForm.vue';
import { useRoute } from 'vue-router';
const { params } = useRoute()
const id = "edit"
</script>
 
<template>
    <div>
        <div>   
            <AnnouncementForm :param="params.id" :id = id>
            </AnnouncementForm>
        </div>
    </div>
</template>
 
<style scoped></style>