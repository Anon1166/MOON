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
    <div class="ml-10 mr-10 w-2/4 ">
        <div class="p-5">
            <h1 class="text-4xl">AnnouncementDetail:</h1>
        </div>
        <div class=" border border-solid  w-full text-left">
            <table>
                <tr>
                    <th>Title</th>
                    <td>{{ announcementDetail.announcementTitle }}</td>
                </tr>
                <tr>
                    <th>Category</th>
                    <td>{{ announcementDetail.categoriesCategoryName }}</td>
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



        <button class="btn-success btn-sm rounded-md mt-5 bg-slate-400" @click="$router.go(-1)">back</button>
    </div>
</template>
 
<style scoped>
td {
    padding-left: 3em;
}

</style>