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
    <div class="w-full max-w-screen-xl">
      <div class="p-5 mb-2 mt-6">
        <h1 class="font-bold text-3xl text-center">SIT Announcement System (SAS)</h1>
      </div>
      <div class="flex flex-col sm:flex-row items-center justify-center mb-5">
        <div class="font-bold mb-2 sm:mb-0 sm:mr-2">Date/Time show in Timezone:</div>
        <div class="text-center">{{ timezone }}</div>
      </div>
      <div class="ann-button flex justify-end mb-2">
        <router-link :to="{ name: 'AddAnnouncement' }">
          <button class="btn-success btn-sm rounded-md bg-green-300 font-bold">Add Announcement</button>
        </router-link>
      </div>
      <div class="flex justify-center">
        <table class="table w-full ">
          <thead>
            <tr>
              <th class="hidden sm:table-cell">#</th>
              <th class="w-1/3">Title</th>
              <th class="hidden sm:table-cell">Category</th>
              <th class="hidden sm:table-cell">Publish Date</th>
              <th class="hidden sm:table-cell">Close Date</th>
              <th>Display</th>
              <th class="text-center">Action</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in announcement " :key="index" class="ann-item">
              <td class="hidden sm:table-cell">{{ index + 1 }}</td>
              <td class="truncate ann-title">{{ item.announcementTitle }}</td>
              <td class="hidden sm:table-cell ann-category">{{ item.announcementCategory }}</td>
              <td v-if="item.publishDate !== null" class="hidden sm:table-cell ann-publish-date">{{
                changeTime(item.publishDate) }}</td>
              <td v-else class="hidden sm:table-cell text-center ann-publish-date">-</td>
              <td v-if="item.closeDate !== null" class="hidden sm:table-cell ann-close-date">{{ changeTime(item.closeDate)
              }}</td>
              <td v-else class="hidden sm:table-cell text-center ann-close-date">-</td>
              <td class="ann-display">{{ item.announcementDisplay }}</td>
              <td class="ann-button space-x-2 ">
                <router-link :to="{ name: 'AnnouncementView', params: { id: item.id } }">
                  <button class="btn-success btn-sm rounded-md bg-green-200 font-bold ">view</button>
                </router-link>
                <button class="btn-success btn-sm rounded-md bg-red-400 font-bold ">delete</button>
              </td>
            </tr>
          </tbody>
          <tbody v-show="announcement.length === 0">
            <th colspan="7" class=" text-center item-center text-2xl font-bold m-5">No Announcements</th>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
 
<style scoped></style>