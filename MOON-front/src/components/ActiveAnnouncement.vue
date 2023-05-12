<script setup>
import { fetchMode, annMode, changeTime, categories, fetchCategory } from '../assets/data-manager.js'
import { onBeforeMount, ref } from 'vue'
import { modeStore } from '../assets/modeAnnouncement.js'
import router from '../router/index.js';
const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
const pageId = ref()

onBeforeMount(async () => {
    await fetchMode()
    await fetchCategory()
    pageId.value = annMode.value.page
})
const modeAnn = modeStore()
const catId = ref(0)



const changePage = async (page) => {
    pageId.value = page
    console.log(pageId.value);
    await fetchMode(modeAnn.modes, page, catId.value)
}

const changeMode = async () => {
    modeAnn.changeModes()
    await fetchMode(modeAnn.modes, 0, catId.value)
    pageId.value = 0
}

const filterCategorys = async () => {
    await fetchMode(modeAnn.modes, 0, catId.value)
    pageId.value = 0
}

const userView = (id) => {
    router.push({ name: 'UserView', params: { id: id } })

}


</script>
 
<template>
    <div class="w-screen h-screen flex flex-col ">

        <div class="p-5 mb-2 mt-6">
            <h1 class="font-bold text-3xl text-center text-emerald-500 ">SIT Announcement System (SAS)</h1>
        </div>

        <div class=" flex justify-center items-center flex-col hover  ">

            <div class="flex  justify-start w-3/4 mb-5 ">
                <div class="font-bold mb-2 sm:mb-0 sm:mr-2 text-emerald-500">Date/Time show in Timezone:</div>
                <div class="text-center ">{{ timezone }}</div>
            </div>

            <div class="flex justify-end w-3/4 ">
                <div class="flex flex-wrap  w-full   justify-between  ">
                    <div class="mb-6 flex relative space-x-4 w-3/4">
                        <label class="text-sm font-medium text-gray-900 dark:text-white" for="category">Category</label>
                        <select class="ann-category select select-bordered rounded-lg border p-2" name="category" v-model="catId"
                            @change="filterCategorys()">
                            <option value="0">ทั้งหมด</option>
                            <option v-for="category in categories" :value="category.categoryId">
                                {{ category.announcementCategory }}</option>
                        </select>
                    </div>
                    <button class="ann-button btn btn-outline  btn-sm rounded-md  font-bold mb-2"
                        :class="modeAnn.modes === 'active' ? 'btn-error' : 'btn-success'" @click="changeMode()">{{
                            modeAnn.modes === "active" ? "Close" : "Active" }} Announcement</button>
                </div>

            </div>
            <table class=" table  w-3/4  shadow-lg ">

                <thead>
                    <tr>
                        <th class="bg-emerald-400 text-black">No</th>
                        <th class="bg-emerald-400 text-black">Title</th>
                        <th class="bg-emerald-400 text-black" v-if="modeAnn.modes === 'active' ? false : true">Closed Date</th>
                        <th class="bg-emerald-400 text-black">Category</th>
                    </tr>
                </thead>
                <tbody v-for="(item, index) in annMode.content" :key="index">

                    <tr class="ann-item hover" @click="userView(item.id)">
                        <td>{{ index + 1 + (annMode.page * annMode.size) }}</td>
                        <td class="text">{{ item.announcementTitle }}</td>
                        <td v-if="item.closeDate !== null && modeAnn.modes === 'close'">{{ changeTime(item.closeDate) }}</td>
                        <td v-else-if="item.closeDate === null && modeAnn.modes === 'close'" class="hidden sm:table-cell  ann-close-date">-</td>
                        <td>{{ item.announcementCategory }}</td>
                    </tr>

                </tbody>
                <tbody v-show="annMode.totalElements === 0">
                    <th colspan="7" class=" text-center item-center text-2xl font-bold m-5">No Announcements</th>
                </tbody>
            </table>
        </div>
        <div v-if="annMode.totalElements > 5" class="flex  flex-row justify-center p-5 ">
            <button :disabled="annMode.page === 0" @click="changePage(--pageId)" class="btn btn-outline dark:btn-success dark:btn-outline ">«</button>
            <div v-for="(item) in annMode.totalPages" class="flex btn-group ">
                <button @click="changePage(item - 1)" class="btn btn-outline dark:btn-success dark:btn-outline "
                    :class="pageId === item - 1 ? ' bg-emerald-400 dark:bg-black ' : ''">{{ item }}</button>
            </div>
            <button :disabled="annMode.page === annMode.totalPages - 1" class="btn btn-outline dark:btn-success dark:btn-outline"
                @click="changePage(++pageId)">»</button>
        </div>
    </div>
</template>
 
<style scoped>

</style>