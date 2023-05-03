<script setup>
import { announcement } from '../assets/data-manager.js'
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
        <p>Title : {{ message }}</p>
        <input v-model="message" placeholder="" />
        
    </div>
</div>
</template>
 
<style scoped>

</style>