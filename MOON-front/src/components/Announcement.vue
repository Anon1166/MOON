<script setup>
import { announcement, fetchAnnouncement, changeTime } from '../assets/data-manager.js'
import { onBeforeMount } from 'vue';


onBeforeMount(async () => {
  await fetchAnnouncement()
})
const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;

</script>
 
<template>
  <div class="w-screen h-screen bg-cover flex flex-col  items-center">
    <div v-show="announcement.length === 0" class="flex flex-auto items-center text-4xl font-bold">No Announcement</div>
    <div v-show="announcement.length !== 0" class="w-full max-w-screen-lg">
      <div class="p-5 mb-2 mt-6">
        <h1 class="font-bold text-3xl text-center">SIT Announcement System (SAS)</h1>
      </div>
      <div class="flex flex-col sm:flex-row items-center justify-center mb-5">
        <div class="font-bold mb-2 sm:mb-0 sm:mr-2">Date/Time show in Timezone:</div>
        <div class="text-center">{{ timezone }}</div>
      </div>
      <div class="flex justify-center">
        <table class="table w-full">
          <thead>
            <tr>
              <th class="hidden sm:table-cell">#</th>
              <th class="w-1/3">Title</th>
              <th class="hidden sm:table-cell">Category</th>
              <th class="hidden sm:table-cell">Publish Date</th>
              <th class="hidden sm:table-cell">Close Date</th>
              <th>Display</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in announcement " :key="index">
              <td class="hidden sm:table-cell">{{ index + 1 }}</td>
              <td class="truncate">{{ item.announcementTitle }}</td>
              <td class="hidden sm:table-cell">{{ item.announcementCategory }}</td>
              <td class="hidden sm:table-cell">{{ changeTime(item.publishDate) }}</td>
              <td class="hidden sm:table-cell">{{ changeTime(item.closeDate) }}</td>
              <td>{{ item.announcementDisplay }}</td>
              <td>
                <router-link :to="{ name: 'AnnouncementView', params: { id: item.id } }">
                  <button class="btn-success btn-sm rounded-md bg-green-200 font-bold">View</button>
                </router-link>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
 
<style scoped></style>