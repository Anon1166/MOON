<script setup>
import { announcement, fetchAnnouncement, changeTime } from '../assets/data-manager.js'
import { onMounted } from 'vue';


onMounted(async () => {
  await fetchAnnouncement()
  // announcement.value.sort((a, b) => b.announcementId - a.announcementId)
})


</script>
 
<template>
   
  <div v-if="announcement.length ===0"  class="flex justify-center mt-[25%] text-4xl font-bold">No Announcement</div>
  <div v-else>
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
            <td class="border border-slate-300 ">{{ item.announcementCategory }}</td>
            <td class="border border-slate-300 ">{{ changeTime(item.publishDate) }}</td>
            <td class="border border-slate-300 ">{{ changeTime(item.closeDate) }}</td>
            <td class="border border-slate-300 ">{{ item.announcementDisplay }}</td>

            <td class="border border-slate-300 ">
              <router-link :to="{ name: 'AnnouncementView', params: { id: item.id } }">
                <button class="btn-success btn-sm rounded-md bg-slate-400">View</button>
              </router-link>
            </td>

          </tr>

        </tbody>

      </table>
    </div>
  </div>
</template>
 
<style scoped></style>