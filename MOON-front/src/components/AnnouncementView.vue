<script setup>
import { announcementDetail, announcementById, changeTime } from '../assets/data-manager.js'
import { onBeforeMount, ref } from 'vue';
import { useRoute } from 'vue-router';
import router from '../../src/router/index';
import '@vueup/vue-quill/dist/vue-quill.snow.css';
const load = ref('')
const { params } = useRoute()

onBeforeMount(async () => {
  await announcementById(params.id)
  if (!announcementDetail.value) {
    alert("The request page is not available")
    router.push('/admin/announcement')
  }
  else {
    load.value = true

  }
})


</script>

<template>
  <div v-if="load" class="mx-auto px-6 sm:px-8 md:px-10 max-w-7xl " >
    <div class="py-6 sm:py-8 md:py-10">
      <h1 class="text-3xl font-bold text-center text-emerald-500 ">AnnouncementDetail</h1>
    </div>
    <div class="flex justify-end w-full items-center font-bold space-x-2 ">
      
      <div class="">
        <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-eye text-emerald-500"
          viewBox="0 0 16 16">
          <path
            d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z" />
          <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" />
        </svg>
      </div>
      <div>
        view {{ announcementDetail.count }}
      </div>
    </div>
    <div class="overflow-x-auto border  rounded-lg text-left border-emerald-500 shadow-lg">

      <table class="ann-item  w-full ">
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
          <div v-html="announcementDetail.announcementDescription" class=" ql-editor ann-description p-2"></div>
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
      <button class="btn btn-outline btn-success btn-sm rounded-md  font-bold" @click="$router.go(-1)">Back</button>
      <router-link :to="{ name: 'AddAnnouncementEdit', params: { id: params.id } }">
        <button class="btn btn-outline btn-accent btn-sm rounded-md font-bold">Edit</button>
      </router-link>
    </div>
  </div>
</template>

<style scoped></style>