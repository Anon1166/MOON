<script setup>
import { announcement, fetchAnnouncement, announcementById } from '../assets/fetch-data.js'
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter()

onMounted(async () => {
  await fetchAnnouncement()
  announcement.value.sort((a, b) => b.announcementId - a.announcementId)
})



const getDetail = async (annid) => {
  await announcementById(annid)
  console.log(annid)
  router.push({ name: 'AnnouncementView', params: { id: annid } })
}


</script>
 
<template>
  <div>
    <div class="w-full h-full p-5 mb-10">
      <h1 class="text-4xl text-center">SIT Announcement System (SAS)</h1>
    </div>

    <div class="flex justify-center ">
      <table class="w-3/4 h-80 text-center ">
        <thead>
          <tr class="bg-gray-200">
            <th class="border border-slate-300 ">No.</th>
            <th class="border border-slate-300 ">Title</th>
            <th class="border border-slate-300 ">Category</th>
            <th class="border border-slate-300 ">Publish Date</th>
            <th class="border border-slate-300 ">Close Date</th>
            <th class="border border-slate-300 ">Display</th>
            <th class="border border-slate-300 ">Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in announcement " :key="index">
            <td class="border border-slate-300 ">{{ index + 1 }}</td>
            <td class="border border-slate-300 ">{{ item.announcementTitle }}</td>
            <td class="border border-slate-300 ">{{ item.categoriesCategoryName }}</td>
            <td class="border border-slate-300 ">{{ item.publishDate }}</td>
            <td class="border border-slate-300 ">{{ item.closeDate }}</td>
            <td class="border border-slate-300 ">{{ item.announcementDisplay }}</td>
            <!-- <router-link :to="{ name: 'AnnouncementView', params: { id: item.id}}"> -->
            <td class="border border-slate-300 "><button class="btn-success btn-sm rounded-md bg-slate-400"
                @click="getDetail(item.announcementId)">View</button></td>
            <!-- </router-link> -->
          </tr>

        </tbody>

      </table>
    </div>
  </div>
</template>
 
<style scoped></style>