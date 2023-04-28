<script setup>
import { announcement, fetchAnnouncement, changeTime } from '../assets/data-manager.js'
import { onMounted } from 'vue';


onMounted(async () => {
  await fetchAnnouncement()
  // announcement.value.sort((a, b) => b.announcementId - a.announcementId)
})
const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;

</script>
 
<template>
  <div class="w-full h-full">
    <div v-show="announcement.length === 0" class="flex justify-center mt-[25%]  text-4xl font-bold">No Announcement</div>
    <div v-show="announcement.length !==0">
      <div class="p-5 mb-2 mt-6">
        <h1 class="font-bold text-3xl text-center">SIT Announcement System (SAS)</h1>
      </div>
      <div class="flex flex-row space-x-2 ml-[20%] mb-5">
        <div class="font-bold">Date/Time show in Timezone:</div>
        <div class="">{{ timezone }}</div>
      </div>



      <div class="flex overflow-x-auto">
        <table class="table ml-[20%]">
          <thead>
            <tr>
              <th>No.</th>
              <th>Title</th>
              <th>Category</th>
              <th>Publish Date</th>
              <th>Close Date</th>
              <th>Display</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>

            <tr v-for="(item, index) in announcement " :key="index">
              <td>{{ index + 1 }}</td>
              <td>{{ item.announcementTitle }}</td>
              <td>{{ item.announcementCategory }}</td>
              <td>{{ changeTime(item.publishDate) }}</td>
              <td>{{ changeTime(item.closeDate) }}</td>
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