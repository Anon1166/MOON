<script setup>
import { fetchMode, annMode } from '../assets/data-manager.js'
import { onBeforeMount, ref } from 'vue'
const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
onBeforeMount(async () => {
    await fetchMode()
})

const changePage = async (page) => {
    await fetchMode("active", 5, page, 0)
}


</script>
 
<template>
    <div class="w-screen h-screen flex flex-col ">

        <div class="p-5 mb-2 mt-6">
            <h1 class="font-bold text-3xl text-center">SIT Announcement System (SAS)</h1>
        </div>

        <div class="flex flex-col sm:flex-row items-center justify-center mb-5 ">
            <div class="font-bold mb-2 sm:mb-0 sm:mr-2">Date/Time show in Timezone:</div>
            <div class="text-center">{{ timezone }}</div>
        </div>
       
            <div class="flex w-full p-2 items-center justify-center ">
                <router-link :to="{ name: '' }">
                    <button
                        class="ann-button btn-success btn-sm rounded-md bg-green-300 font-bold mb-2 ">CloseAnnouncement</button>
                </router-link>
            </div>
        
        <div class=" flex justify-center hover  ">
            <table class=" table w-3/4  shadow-lg">

                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody>

                    <tr v-for="(item, index) in annMode.content" :key="index" class="ann-item hover">
                        <th>{{ index + 1 + (annMode.page * annMode.size) }}</th>
                        <td>{{ item.announcementTitle }}</td>
                        <td>{{ item.announcementCategory }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="flex flex-row justify-center p-5 ">
            <button class="btn">«</button>
            <div v-for="(item) in annMode.totalPages" class="btn-group ">
                <button @click="changePage(item - 1)" class="btn">{{ item }}</button>
            </div>
            <button class="btn">»</button>
        </div>
    </div>
</template>
 
<style scoped></style>