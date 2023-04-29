<script setup>
import { announcement, fetchAnnouncement, changeTime } from '../assets/data-manager.js'
import { onMounted } from 'vue';


onMounted(async () => {
  await fetchAnnouncement()
  // announcement.value.sort((a, b) => b.announcementId - a.announcementId)
})
const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;

</script>
 
<!-- <template>
  <div class="screen h-screen w-screen bg-cover bg-">
    <div v-show="announcement.length === 0" class="flex justify-center mt-[25%]  text-4xl font-bold">No Announcement</div>
    <div v-show="announcement.length !== 0">
      <div class="p-5 mb-2 mt-6">
        <h1 class="font-bold text-3xl text-center">SIT Announcement System (SAS)</h1>
      </div>
      <div class="flex space-x-2 mb-5">
        <div class="font-bold">Date/Time show in Timezone:</div>
        <div class="">{{ timezone }}</div>
      </div>



      <div class="flex justify-center">
        <table class="table w-3/4  ">
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
</template> -->

<!-- <template>
  <div class="screen h-screen w-screen bg-cover">
    <div v-show="announcement.length === 0" class="flex justify-center mt-10 sm:mt-32 text-4xl font-bold">No Announcement</div>
    <div v-show="announcement.length !== 0">
      <div class="p-5 mb-2 mt-6">
        <h1 class="font-bold text-3xl text-center">SIT Announcement System (SAS)</h1>
      </div>
      <div class="flex flex-col sm:flex-row items-center justify-center mb-5">
        <div class="font-bold mb-2 sm:mb-0 sm:mr-2">Date/Time show in Timezone:</div>
        <div class="text-center">{{ timezone }}</div>
      </div>

      <div class="flex justify-center">
        <table class="table w-11/12 sm:w-3/4">
          <thead>
            <tr>
              <th class="hidden sm:table-cell">No.</th>
              <th>Title</th>
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
              <td>{{ item.announcementTitle }}</td>
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
</template> -->

<!-- <template>
  <div class="screen h-screen w-screen bg-cover">
    <div v-show="announcement.length === 0" class="flex justify-center mt-10 sm:mt-32 text-4xl font-bold">No Announcement</div>
    <div v-show="announcement.length !== 0">
      <div class="p-5 mb-2 mt-6">
        <h1 class="font-bold text-3xl text-center">SIT Announcement System (SAS)</h1>
      </div>
      <div class="flex flex-col sm:flex-row items-center justify-center mb-5">
        <div class="font-bold mb-2 sm:mb-0 sm:mr-2">Date/Time show in Timezone:</div>
        <div class="text-center">{{ timezone }}</div>
      </div>

      <div class="flex justify-center">
        <table class="table w-11/12 sm:w-3/4">
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
</template> -->

<template>
  <div class="w-screen h-screen bg-cover flex flex-col  items-center">
    <div v-show="announcement.length === 0" class="text-4xl font-bold mb-8">No Announcement</div>
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