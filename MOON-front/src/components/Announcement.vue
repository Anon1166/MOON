<script setup>
import { announcement, fetchAnnouncement, changeTime, deleteAnnoumcementById } from '../assets/data-manager.js'
import { onBeforeMount , ref, computed} from 'vue';



onBeforeMount(async () => {
  await fetchAnnouncement()
})
const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
const getId = ref("")

const searchKeyword = ref('')
const filterAnn = computed(() => {
    return announcement.value.filter((ann) =>
    ann.announcementTitle?.toLowerCase().includes(searchKeyword.value.toLowerCase())
    ).sort((a, b) => b.level - a.level)
})



</script>
 
<template>
  <div class="w-full h-full bg-cover flex flex-col  items-center">
    <div class="w-full max-w-screen-xl">
      <div class="p-5 mb-2 mt-6">
        <h1 class="font-bold text-3xl text-center">SIT Announcement System (SAS)</h1>
      </div>
      <div class="flex flex-col sm:flex-row items-center justify-center mb-5">
        <div class="font-bold mb-2 sm:mb-0 sm:mr-2">Date/Time show in Timezone:</div>
        <div class="text-center">{{ timezone }}</div>
      </div>
      <div class="ann-button flex justify-between mb-2">
        <input type="text" placeholder="search title"  class="input input-bordered w-full max-w-xs" v-model="searchKeyword" />
        <router-link :to="{ name: 'AddAnnouncement' }">
          <button class="ann-button btn-success btn-sm rounded-md bg-green-300 font-bold mb-2">Add Announcement</button>
        </router-link>
      </div>
      <div class="flex justify-center ">
        <table class="table w-full h-full  ">
          <thead>
            <tr>
              <th class="hidden sm:table-cell">#</th>
              <th class="w-1/3">Title</th>
              <th class="hidden sm:table-cell">Category</th>
              <th class="hidden sm:table-cell">Publish Date</th>
              <th class="hidden sm:table-cell">Close Date</th>
              <th class="text-center">Display</th>
              <th class="text-center">Action</th>
            </tr>
          </thead>
          
          <tbody class="overflow-scroll">
            <tr v-for="(item, index) in filterAnn " :key="index" class="ann-item  ">
              <td class="hidden sm:table-cell">{{ index + 1 }}</td>
              <td class="truncate ann-title text">{{ item.announcementTitle}}</td>
              <td class="hidden sm:table-cell ann-category">{{ item.announcementCategory }}</td>
              <td v-if="item.publishDate !== null" class="hidden sm:table-cell ann-publish-date">{{
                changeTime(item.publishDate) }}</td>
              <td v-else class="hidden sm:table-cell text-center ann-publish-date">-</td>
              <td v-if="item.closeDate !== null" class="hidden sm:table-cell ann-close-date">{{ changeTime(item.closeDate)
              }}</td>
              <td v-else class="hidden sm:table-cell text-center ann-close-date">-</td>
              <td class="ann-display text-center">{{ item.announcementDisplay }}</td>
              <td class="ann-button space-x-2 flex justify-center">
                <router-link :to="{ name: 'AnnouncementView', params: { id: item.id } }">
                  <button class="btn-success w-20 btn-sm rounded-md bg-green-200 font-bold ">view</button>
                </router-link>
                <button @click="getId = item.id" class="ann-button btn-success w-20 btn-sm rounded-md bg-red-400 font-bold"><label
                    for="modal">delete</label></button>
              </td>
            </tr>
          </tbody>
          <tbody v-show="announcement.length === 0">
            <th colspan="7" class=" text-center item-center text-2xl font-bold m-5">No Announcements</th>
          </tbody>
        </table>
      </div>
    </div>

    <input type="checkbox" id="modal" class="modal-toggle" />
    <div class="modal modal-bottom sm:modal-middle">
      <div class="modal-box w-2/3">
        <h3 class="text-xl text-center font-medium text-gray-900 dark:text-gray-300">Are you sure you want to delete this item?</h3>
        <div class="modal-action flex justify-center">
          <label for="modal" @click="deleteAnnoumcementById(getId)" class="btn">confirm</label>
          <label for="modal" class="btn">cancel</label>
        </div>
      </div>
    </div>
  </div>
</template>
 
<style scoped>

.text {
  display: block;
  width: 500px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

</style>