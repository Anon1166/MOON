<script setup>
import { announcementDetail, announcementById, changeTime } from '../assets/data-manager.js'
import { onBeforeMount } from 'vue';
import { useRoute } from 'vue-router';
import { modeStore } from '../assets/modeAnnouncement.js'

const { params } = useRoute()
const modeAnn = modeStore()
onBeforeMount(async () => {
    await announcementById(params.id)

})
</script>

<template>
    <div class=" flex justify-center w-full h-full p-20 ">
        <div class=" item-card h-3/4 w-3/4 bg-base-100 shadow-xl border border-emerald-400 rounded-lg">
            <div class="card-body ">
                <div class="flex justify-between ">
                    <div class="ann-title font-bold text-3xl card-title text-emerald-500">{{ announcementDetail.announcementTitle }}</div>
                    <div v-if="announcementDetail.closeDate !== null && modeAnn.modes === 'close'" class="ann-close-date  text-sm "><span
                            class="text-red-600 font-bold">Close on : </span>{{ changeTime(announcementDetail.closeDate) }}
                    </div>
                </div>
                <p class="ann-category opacity-75">{{ announcementDetail.announcementCategory }}</p>
                <div class="ann-description">{{ announcementDetail.announcementDescription }}</div>




                <div class="ann-button card-actions justify-end">
                    <button @click="$router.go(-1)" class="btn btn-outline btn-error">back</button>
                </div>
            </div>

        </div>
    </div>
</template>

<style scoped></style>


