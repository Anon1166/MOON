import { getAnnounment, getAnnounmentById} from "./data-handler.js"
import { ref } from 'vue'

const announcement = ref([])
const announcementDetail = ref("")

const fetchAnnouncement = async () => {
    announcement.value = await getAnnounment()
}

const announcementById = async (id) => {
    announcementDetail.value = await getAnnounmentById(id)
}

const changeTime = (date) => {
  
    const time = new Date(date)
    console.log(time.getFullYear());  
    console.log(time.toLocaleString('en-GB', {dateStyle: 'medium', timeStyle: 'short'}))
    if (time.getFullYear() > 1970) {
      
      return time.toLocaleString('en-GB', {dateStyle: 'medium', timeStyle: 'short'})
      
    }
  }



export {announcement, fetchAnnouncement, announcementDetail, announcementById, changeTime }