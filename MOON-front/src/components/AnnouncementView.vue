<script setup>
import { announcementDetail, announcementById, changeTime } from '../assets/data-manager.js'
import { onBeforeMount, ref } from 'vue';
import { useRoute } from 'vue-router';
import router from '../../src/router/index';
const load = ref('')
const { params } = useRoute()

onBeforeMount(async () => {
  await announcementById(params.id)
  if (!announcementDetail.value) {
    alert("The requested page is not available!")
    router.push('/admin/announcement')
  }
  else {
    load.value = true
  }
})


</script>

<template>
  <div v-if="load" class="mx-auto px-6 sm:px-8 md:px-10 max-w-7xl">
    <div class="py-6 sm:py-8 md:py-10">
      <h1 class="text-3xl font-bold text-center">AnnouncementDetail</h1>
    </div>
    <div class="overflow-x-auto  ">
      <table class="ann-item  w-full border border-separate rounded-lg text-left ">
        <tr>
          <th class="p-2 ">Title</th>
          <td class="ann-title p-2">{{ announcementDetail.announcementTitle }}</td>
        </tr>
        <tr>
          <th class="p-2">Category</th>
          <td class="ann-category p-2">{{ announcementDetail.announcementCategory }}</td>
        </tr>
        <tr>
          <th class="p-2">Description</th>
          <td class="ann-description p-2">{{ announcementDetail.announcementDescription }}</td>
        </tr>
        <tr>
          <th class="p-2">Publish Date</th>
          <td class="ann-publish-date p-2" v-if="announcementDetail.publishDate !== null">{{
            changeTime(announcementDetail.publishDate) }}
          </td>
          <td class="ann-publish-date p-2" v-else>-</td>
        </tr>
        <tr>
          <th class="p-2">Close Date</th>
          <td class="ann-close-date  p-2" v-if="announcementDetail.closeDate !== null">{{
            changeTime(announcementDetail.closeDate) }}</td>
          <td class="ann-close-date p-2" v-else>-</td>
        </tr>
        <tr>
          <th class="p-2">Display</th>
          <td class="ann-display p-2">{{ announcementDetail.announcementDisplay }}</td>
        </tr>
      </table>
    </div>
    <div class="mt-6 sm:mt-8 md:mt-10 flex  ann-button space-x-3">
      <button class="btn-success btn-sm rounded-md bg-green-200 font-bold" @click="$router.go(-1)">Back</button>
      <router-link :to="{ name: 'AddAnnouncementEdit', params: { id: params.id } }">
      <button class="btn-success btn-sm rounded-md hover:bg-blue-400 bg-blue-200 font-bold" >Edit</button>
      </router-link>
    </div>
  </div>
</template>

<style scoped>
/* td {
  padding-left: 3em;
} */
</style>