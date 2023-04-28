<script setup>
import { announcementDetail, announcementById, changeTime } from '../assets/data-manager.js'
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';

const { params } = useRoute()

onMounted(async () => {
    await announcementById(params.id)
})
</script>
 
<template>
    <div class="ml-28  w-2/4">
        
        <div class="p-5">
            <h1 class="text-3xl font-bold">AnnouncementDetail:</h1>
        </div>
        <div>
            <table class="table w-full border border-solid rounded-xl">
                <tr>
                    <th>Title</th>
                    <td>{{ announcementDetail.announcementTitle }}</td>
                </tr>
                <tr>
                    <th>Category</th>
                    <td>{{ announcementDetail.announcementCategory }}</td>
                </tr>
                <tr>
                    <th>Description</th>
                    <td>{{ announcementDetail.announcementDescription }}</td>
                </tr>
                <tr>
                    <th>Publish Date</th>
                    <td v-if="announcementDetail.publishDate !== null">{{ changeTime(announcementDetail.publishDate) }}</td>
                    <td v-else>-</td>
                </tr>
                <tr>
                    <th>Close Date</th>
                    <td v-if="announcementDetail.closeDate !== null">{{ changeTime(announcementDetail.closeDate) }}</td>
                    <td v-else>-</td>
                </tr>
                <tr>
                    <th>Display</th>
                    <td>{{ announcementDetail.announcementDisplay }}</td>
                </tr>
            </table>
        </div>



        <button class="btn-success btn-sm rounded-md mt-5 bg-green-200 font-bold" @click="$router.go(-1)">Back</button>
    </div>
</template>
 
<style scoped>
td {
    padding-left: 3em;
}
</style>