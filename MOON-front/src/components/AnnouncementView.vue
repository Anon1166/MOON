<script setup>
import { announcementDetail, announcementById, changeTime } from '../assets/data-manager.js'
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';

const { params } = useRoute()

onMounted(async () => {
    await announcementById(params.id)
})
</script>
 
<!-- <template>
    <div class="ml-28  w-2/4">
        
        <div class="p-5">
            <h1 class="text-3xl font-bold">AnnouncementDetail:</h1>
        </div>
        <div>
            <table class="table w-full border border-solid rounded-xl">
                <tr>
                    <th>Title</th>
                    <td>{{ announcementDetail.announcementTitle }}</td>
                </tr>
                <tr>
                    <th>Category</th>
                    <td>{{ announcementDetail.announcementCategory }}</td>
                </tr>
                <tr>
                    <th>Description</th>
                    <td>{{ announcementDetail.announcementDescription }}</td>
                </tr>
                <tr>
                    <th>Publish Date</th>
                    <td v-if="announcementDetail.publishDate !== null">{{ changeTime(announcementDetail.publishDate) }}</td>
                    <td v-else>-</td>
                </tr>
                <tr>
                    <th>Close Date</th>
                    <td v-if="announcementDetail.closeDate !== null">{{ changeTime(announcementDetail.closeDate) }}</td>
                    <td v-else>-</td>
                </tr>
                <tr>
                    <th>Display</th>
                    <td>{{ announcementDetail.announcementDisplay }}</td>
                </tr>
            </table>
        </div>



        <button class="btn-success btn-sm rounded-md mt-5 bg-green-200 font-bold" @click="$router.go(-1)">Back</button>
    </div>
</template> -->



<!-- <template>
    <div class="mx-auto px-4 sm:px-6 lg:px-8 max-w-screen-lg">
      <div class="my-8">
        <h1 class="text-3xl sm:text-4xl md:text-5xl font-bold text-center mb-4">Announcement Detail</h1>
        <div class="flex justify-center border border-gray-300 rounded-lg">
          <table class="table w-full">
            <tr>
              <th class="w-1/4 sm:w-1/5 md:w-1/6 py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8 font-bold">Title</th>
              <td class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8">{{ announcementDetail.announcementTitle }}</td>
            </tr>
            <tr>
              <th class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8 font-bold">Category</th>
              <td class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8">{{ announcementDetail.announcementCategory }}</td>
            </tr>
            <tr>
              <th class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8 font-bold">Description</th>
              <td class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8">{{ announcementDetail.announcementDescription }}</td>
            </tr>
            <tr>
              <th class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8 font-bold">Publish Date</th>
              <td class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8">{{ announcementDetail.publishDate ? changeTime(announcementDetail.publishDate) : '-' }}</td>
            </tr>
            <tr>
              <th class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8 font-bold">Close Date</th>
              <td class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8">{{ announcementDetail.closeDate ? changeTime(announcementDetail.closeDate) : '-' }}</td>
            </tr>
            <tr>
              <th class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8 font-bold">Display</th>
              <td class="py-2 px-4 sm:py-3 sm:px-6 md:py-4 md:px-8">{{ announcementDetail.announcementDisplay }}</td>
            </tr>
          </table>
        </div>
      </div>
      <div class="flex justify-center mt-8">
        <button class="btn-success btn-sm rounded-md bg-green-200 font-bold" @click="$router.go(-1)">Back</button>
      </div>
    </div>
  </template> -->
  
  <template>
  <div class="mx-auto px-6 sm:px-8 md:px-10 max-w-7xl">
    <div class="py-6 sm:py-8 md:py-10">
      <h1 class="text-3xl font-bold text-center">AnnouncementDetail:</h1>
    </div>
    <div class="overflow-x-auto">
      <table class="w-full border border-separate rounded-xl">
        <tr>
          <th class="p-2">Title</th>
          <td class="p-2">{{ announcementDetail.announcementTitle }}</td>
        </tr>
        <tr>
          <th class="p-2">Category</th>
          <td class="p-2">{{ announcementDetail.announcementCategory }}</td>
        </tr>
        <tr>
          <th class="p-2">Description</th>
          <td class="p-2">{{ announcementDetail.announcementDescription }}</td>
        </tr>
        <tr>
          <th class="p-2">Publish Date</th>
          <td class="p-2" v-if="announcementDetail.publishDate !== null">{{ changeTime(announcementDetail.publishDate) }}</td>
          <td class="p-2" v-else>-</td>
        </tr>
        <tr>
          <th class="p-2">Close Date</th>
          <td class="p-2" v-if="announcementDetail.closeDate !== null">{{ changeTime(announcementDetail.closeDate) }}</td>
          <td class="p-2" v-else>-</td>
        </tr>
        <tr>
          <th class="p-2">Display</th>
          <td class="p-2">{{ announcementDetail.announcementDisplay }}</td>
        </tr>
      </table>
    </div>
    <div class="mt-6 sm:mt-8 md:mt-10 flex justify-center">
      <button class="btn-success btn-sm rounded-md bg-green-200 font-bold" @click="$router.go(-1)">Back</button>
    </div>
  </div>
</template>

<style scoped>
td {
    padding-left: 3em;
}
</style>