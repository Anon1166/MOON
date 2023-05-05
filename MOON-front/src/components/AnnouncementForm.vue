<script setup>
import { addAnnouncement } from '../assets/data-manager.js'
import { announcementDetail, announcementById, fetchCategory, categories, updateAnnouncementbyId } from '../assets/data-manager';
import { ref } from 'vue';
import { onBeforeMount } from 'vue';
import router from '../router/index.js';

const data1 = ref({})
const props = defineProps(["param", "id"])
const publishDate = ref(null)
const publishtime = ref(null)
const closeDate = ref(null)
const closetime = ref(null)
const display = ref(false)


onBeforeMount(async () => {
    await fetchCategory()
    if (props.id === "edit") {
        await announcementById(props.param)
        data1.value = announcementDetail.value
        const c = categories.value.filter((a) => a.announcementCategory === announcementDetail.value.announcementCategory)
        const pubdate = new Date(announcementDetail.value.publishDate)
        const closedate = new Date(announcementDetail.value.closeDate)
        createAnn.value.announcementTitle = announcementDetail.value.announcementTitle
        createAnn.value.announcementDescription = announcementDetail.value.announcementDescription
        publishDate.value = changeDate(pubdate)
        publishtime.value = changeTime(pubdate)
        closeDate.value = changeDate(closedate)
        closetime.value = changeTime(closedate)
        display.value = announcementDetail.value.announcementDisplay === "Y" ? true : false
        createAnn.value.categoryId = c[0].categoryId

    }
})

const changeDate = (date) => {
    if (date.getFullYear() > 1970) {
        const year = date.getFullYear()
        const month = (date.getMonth() + 1).toString().padStart(2, '0')
        const day = date.getDate().toString().padStart(2, '0')
        const formattedDate = `${year}-${month}-${day}`
        return formattedDate
    }
}

const changeTime = (date) => {
    if (date.getFullYear() > 1970) {
        const formattedTime = date.toLocaleTimeString('en-GB', {
            hour: 'numeric',
            minute: 'numeric'
        })
        return formattedTime
    }
}
const createAnn = ref({
    announcementTitle: "",
    publishDate: "",
    closeDate: "",
    announcementDescription: "",
    announcementDisplay: "",
    categoryId: 1
})

const formatDateTime = (date, time) => {
    if (date && time) {
        const format = new Date(date + " " + time)
        return format.toISOString()
    }
}
const check = () => {
    const c = categories.value.filter((a) => a.announcementCategory === data1.value.announcementCategory)
    const a = display.value ? 'Y' : 'N'
    const timepub = new Date(formatDateTime(publishDate.value, publishtime.value)).getTime()
    const timepub1 = new Date(data1.value.publishDate).getTime()
    const timeclo = new Date(formatDateTime(closeDate.value, closetime.value)).getTime()
    const timeclo1 = new Date(data1.value.closeDate).getTime()

    if (
        data1.value.announcementTitle !== createAnn.value.announcementTitle ||
        data1.value.announcementDescription !== createAnn.value.announcementDescription ||
        c[0].categoryId !== createAnn.value.categoryId ||
        data1.value.announcementDisplay !== a ||
        timepub !== timepub1 ||
        timeclo !== timeclo1
    ) {
        return false
    } else {
        return true
    }

}

const submit = async () => {
    if (createAnn.value.announcementTitle !== "" && createAnn.value.announcementDescription !== "") {
        createAnn.value.publishDate = formatDateTime(publishDate.value, publishtime.value)
        createAnn.value.closeDate = formatDateTime(closeDate.value, closetime.value)
        createAnn.value.announcementDisplay = display.value ? "Y" : "N"
        if (props.id === "edit") {
            updateAnnouncementbyId(createAnn.value, props.param)
            router.go(-1)
        } else {
            addAnnouncement(createAnn.value)
            router.push({ name: 'Home' })
        }

    }
}

</script>
<template>
    <div class="w-full h-full ">
        <div>
            <h3 class="text-center font-bold text-3xl m-3 text-blue-700 ">Announcement Detail</h3>
            <div class="flex justify-center">
                <!-- from -->
                <div class=" w-full max-w-2xl p-5 m-5 border rounded-lg shadow-lg">
                    <div class="grid gap-6 mb-6 md:grid-cols-1">
                        <form class="grid gap-6 md:grid-cols-1" @submit.prevent="submit">
                            <div class="mb-3">
                                <label for="title"
                                    class="block mb-2 text-sm font-medium text-gray-900  dark:text-white">Title</label>
                                <input type="text" id="title" v-model="createAnn.announcementTitle"
                                    class="bg-gray-50 peer  border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder=" " required>
                            </div>
                            <div class="mb-6 flex relative space-x-4 ">
                                <label class="text-sm font-medium text-gray-900 dark:text-white"
                                    for="category">Category</label>
                                <select v-model=createAnn.categoryId class="rounded-lg border p-2"
                                    name="category">
                                    <option v-for="category in categories " :key="category.categoryId"
                                        :value="category.categoryId">{{ category.announcementCategory }}</option>
                                </select>
                            </div>
                            <div class="mb-6 flex space-x-2">
                                <label for="description"
                                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Description</label>
                                <textarea v-model=createAnn.announcementDescription id="description" rows="4" required
                                    class="block p-2.5 w-full peer text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="Write your thoughts here..."></textarea>
                            </div>
                            <div class="mb-6 flex space-x-3">
                                <label for="message"
                                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Publish
                                    Date</label>

                                <div class="relative max-w-sm flex  ">
                                    <div class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                                        <svg aria-hidden="true" class="w-5 h-5 text-gray-500 dark:text-gray-400"
                                            fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd"
                                                d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z"
                                                clip-rule="evenodd"></path>
                                        </svg>
                                    </div>
                                    <input type="date" v-model="publishDate"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        placeholder="Select date">
                                </div>
                                <div class="relative max-w-sm flex ">
                                    <input type="time" v-model="publishtime"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                </div>
                            </div>
                            <div class="mb-6 flex space-x-3">
                                <label for="message"
                                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Close
                                    Date</label>
                                <div class="relative max-w-sm flex  ">
                                    <div class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                                        <svg aria-hidden="true" class="w-5 h-5 text-gray-500 dark:text-gray-400"
                                            fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd"
                                                d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z"
                                                clip-rule="evenodd"></path>
                                        </svg>
                                    </div>
                                    <input type="date" v-model="closeDate"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        placeholder="Select date">
                                </div>
                                <div class="relative max-w-sm flex ">
                                    <input type="time" v-model="closetime"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                </div>
                            </div>
                            <div class="flex items-start mb-6">
                                <div class="flex items-center h-5">
                                    <input id="check" type="checkbox" v-model="display"
                                        class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800">
                                </div>
                                <label for="check" class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Check
                                    to
                                    show this announcement</label>
                            </div>
                            <button type="submit" :disabled="check()" :class="check() ? '' : 'hover:bg-blue-800 dark:hover:bg-blue-700'"
                                class="text-white bg-blue-700 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600  dark:focus:ring-blue-800  disabled:bg-zinc-600 disabled:text-zinc-400 ">Submit</button>
                        </form>
                        <button @click="$router.go(-1)"
                            class="text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-800">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<style scoped></style>