<script setup>
import { announcement, AddAnnouncement} from '../assets/data-manager.js'
const createAnn = {
  id: announcement.value.length+1,
  announcementTitle: "",
  announcementCategory: "",
  publishDate: "",
  closeDate: "",
  announcementDisplay: "",
  categoriesCategoryId: ""
}

</script>
 
<template>
<div class="w-screen h-screen">
    <div>
        <div>Announcement Detail:</div>
        <div>category</div>
        
    </div>
</div>
</template>
 
<style scoped>

</style>