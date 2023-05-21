<script setup>
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';
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
const checkDis = ref(false)
const checkTi = ref(false)
const checkPub = ref(false)
const checkClose = ref(false)



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
const checktime = (a) => {
    if (!a) {
        return 0
    } else {
        return a
    }
}

const check = () => {
    const c = categories.value.filter((a) => a.announcementCategory === data1.value.announcementCategory)[0]?.categoryId
    const a = display.value ? 'Y' : 'N'
    const timepub = new Date(formatDateTime(publishDate.value, publishtime.value)).getTime()
    const timepub1 = new Date(data1.value.publishDate).getTime()
    const timeclo = new Date(formatDateTime(closeDate.value, closetime.value)).getTime()
    const timeclo1 = new Date(data1.value.closeDate).getTime()
    if (
        data1.value.announcementTitle !== createAnn.value.announcementTitle ||
        (data1.value.announcementDescription !== createAnn.value.announcementDescription && createAnn.value.announcementDescription.replace(/<[^>]+>/g, '') !== "") ||
        c !== createAnn.value.categoryId ||
        data1.value.announcementDisplay !== a ||
        checktime(timepub) !== timepub1 ||
        checktime(timeclo) !== timeclo1
    ) {
        return false
    } else {
        return true
    }

}


const submit = async () => {
    if (createAnn.value.announcementTitle === "") {
        checkTi.value = true
        setTimeout(() => {
            checkTi.value = false
        },2000)
    } else if (createAnn.value.announcementDescription.replace(/<[^>]+>/g, '') === "") {
        checkDis.value = true
        setTimeout(() => {
            checkDis.value = false
        },2000)
    } else if (createAnn.value.announcementTitle !== "" && createAnn.value.announcementDescription !== "" && validateTimepublish() && validateTimeClose()) {
        createAnn.value.publishDate = formatDateTime(publishDate.value, publishtime.value)
        createAnn.value.closeDate = formatDateTime(closeDate.value, closetime.value)
        createAnn.value.announcementDisplay = display.value ? "Y" : "N"
        if (props.id === "edit") {
            await updateAnnouncementbyId(createAnn.value, props.param)
            router.go(-1)
        } else {
            await addAnnouncement(createAnn.value)
            router.push({ name: 'Home' })
        }
 
    }
}




const validateDatepublish = () => {
    if (publishDate.value) {
        const inputDate = new Date(publishDate.value)
        inputDate.setHours(0, 0, 0, 0)
        const currentDate = new Date()
        currentDate.setHours(0, 0, 0, 0)
        const clPub = new Date(closeDate.value)
        clPub.setHours(0, 0, 0, 0)


        if (inputDate < currentDate) {
            publishDate.value = null
        } else {
            !publishtime.value ? publishtime.value = "06:00" : ''
        }
        if (inputDate < clPub) {
            publishDate.value = null
            publishtime.value = null
        }
    } 
};


const validateDateclose = () => {
    if (closeDate.value) {
        const clDate = new Date(closeDate.value)
        clDate.setHours(0, 0, 0, 0)
        const pubDate = new Date(publishDate.value)
        pubDate.setHours(0, 0, 0, 0)

        if (clDate < pubDate) {
            closeDate.value = null
            closetime.value = null
            checkClose.value = true
            setTimeout(() => {
                checkClose.value = false
            },3000)
        } else {
            !closetime.value ? closetime.value = "18:00" : ''
        }
        if (closetime.value < publishtime.value) {
            closetime.value = "18:00"
        }

    }
}

const validateTimepublish = () => {
    const p = new Date(publishDate.value + " " + publishtime.value)
    const currentDate = new Date()
    if (p <= currentDate) {
        checkPub.value = true
        setTimeout(() => {
            checkPub.value = false
        },3000)
        return false
    }
    return true 
}

const validateTimeClose = () => {
    const c = new Date(closeDate.value + " " + closetime.value)
    const p = new Date(publishDate.value + " " + publishtime.value)
    const currentDate = new Date()
    console.log(c);
    console.log(p);
    console.log(c<=p);
    if (c <= currentDate || c <= p) {
        checkClose.value = true
        setTimeout(() => {
            checkClose.value = false
        },3000)
        return false
    }
    return true 
}

const clearPublishDate = (event) => {
    event.preventDefault()
    publishDate.value = null
    publishtime.value = null
}

const clearCloseDate = (event) => {
    event.preventDefault()
    closeDate.value = null
    closetime.value = null
}





</script>
<template>
    <div class="w-screen h-screen ">

        <h3 class="text-center font-bold text-3xl text-emerald-500 ">Announcement Detail</h3>
        <div class="flex justify-center">
            <div class=" w-full max-w-2xl p-5 m-5 border border-emerald-400 rounded-lg shadow-xl">
                <div class="grid gap-6 mb-6 md:grid-cols-1">
                    <form class="grid gap-6 md:grid-cols-1" @submit.prevent="submit">
                        <div class=" mb-3">
                            <label for="title"
                                class="block mb-2 text-sm font-medium text-gray-900  dark:text-white">Title</label>
                            <input type="text" id="title" v-model="createAnn.announcementTitle" maxlength="200"
                                class="ann-title bg-gray-50 peer  border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                placeholder=" ">
                            <p v-if="checkTi" class="absolute text-xs text-red-500">Please fill out this filed.</p>
                        </div>
                        <div class="mb-6 flex relative space-x-4 ">
                            <label class="text-sm font-medium text-gray-900 dark:text-white" for="category">Category</label>
                            <select v-model=createAnn.categoryId class="ann-category rounded-lg border p-2" name="category">
                                <option v-for="category in categories " :key="category.categoryId"
                                    :value="category.categoryId">{{ category.announcementCategory }}</option>
                            </select>
                        </div>
                        <div class="mb-6 flex space-x-2">
                            <label for="description"
                                class="block  text-sm font-medium text-gray-900 dark:text-white">Description</label>
                            <div class="w-3/4 h-3/4 " >
                               <QuillEditor theme="snow" toolbar="full" 
                                    v-model:content="createAnn.announcementDescription" contentType="html" />
                                <p v-if="checkDis" class="text-xs absolute text-red-500">Please fill out this filed.</p>
                            </div>

                        </div>





                        <div class="mb-6 flex space-x-3 mt-8">
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
                                <input @change="validateDatepublish" type="date" v-model="publishDate"
                                    class="ann-publish-date bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="Select date">
                            </div>
                            <div class="relative max-w-sm flex ">
                                <input type="time" v-model="publishtime" :disabled="publishDate === null" @change="validateTimepublish"
                                    class="ann-publish-time bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"> 
                            </div>
                            <div class="relative max-w-sm flex ">
                                <button @click="clearPublishDate($event)"
                                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                    clear
                                </button>
                            </div>
                            <p v-if="checkPub" class="text-xs absolute text-red-500 mt-11">Date or Time must be greater than the current time.</p>
                        </div>
                        
                        <div class="mb-6 flex space-x-3">
                            <label for="message"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white pr-3">Close
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
                                <input type="date" @change="validateDateclose" v-model="closeDate"
                                    class="ann-close-date bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="Select date">
                            </div>
                            <div class="relative max-w-sm flex ">
                                <input type="time" @change="validateTimeClose" v-model="closetime"
                                    :disabled="closeDate === null"
                                    class="ann-close-time bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                            </div>
                            <div class="relative max-w-sm flex ">
                                <button @click="clearCloseDate($event)"
                                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                    clear
                                </button>
                            </div>
                            <p v-if="checkClose" class="text-xs absolute text-red-500 mt-11">Date or Time must be greater than the current time and publish time.</p>
                        </div>
                        <div class="flex items-start mb-6">
                            <div class="flex items-center h-5">
                                <input id="check" type="checkbox" v-model="display"
                                    class="ann-display w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800">
                            </div>
                            <label for="check" class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Check
                                to
                                show this announcement</label>
                        </div>
                        <button type="submit" :disabled="check()"
                            class="ann-button btn btn-outline btn-success text-white focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center  dark:focus:ring-blue-800  disabled:bg-zinc-600 disabled:text-zinc-400 ">Submit</button>
                    </form>
                    <button @click="$router.go(-1)"
                        class="ann-button btn btn-outline btn-error text-white hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center  dark:hover:bg-red-700 dark:focus:ring-red-800">Cancel</button>
                </div>
            </div>
        </div>

    </div>
</template>
 
<style scoped></style>
