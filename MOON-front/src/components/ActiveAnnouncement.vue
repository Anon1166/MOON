<script setup>
import {fetchMode} from '../assets/data-manager.js'
import {onBeforeMount} from 'vue'
onBeforeMount(async () => {
  await fetchMode()
})
</script>
 
<template>
    <div class="w-full h-full">

        <div class="p-5 mb-2 mt-6">
            <h1 class="font-bold text-3xl text-center">SIT Announcement System (SAS)</h1>
        </div>

    </div>

    <div>
        <div class="flex justify-end w-52 ">
            <router-link :to="{ name: 'closeAnnouncement' }">
                <button class="ann-button btn-success btn-sm rounded-md bg-green-300 font-bold mb-2">Close
                    Announcements</button>
            </router-link>
        </div>
        <div class=" flex justify-center hover">
            <table class=" table w-3/4 ">
                <!-- head -->
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- row 1 -->
                    <tr>
                        <th>1</th>
                        <td>Cy Gandertonnnnnnnnnnnnnnnnnnnnnnnnn</td>
                        <td>ทั้วไป</td>
                    </tr>
                    <tr>
                        <th>1</th>
                        <td>Cy Gandertonnnnnnnnnnnnnnnnnnnnnnnnnn</td>
                        <td>ทั้วไป</td>
                    </tr>
                    <tr>
                        <th>1</th>
                        <td>Cy Gandertonnnnnnnnnnnnnnnnnnnnnnnnnn</td>
                        <td>ทั้วไป</td>
                    </tr>

                </tbody>
            </table>
        </div>
    </div>
</template>
 
<style scoped></style>