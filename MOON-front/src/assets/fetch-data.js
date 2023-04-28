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

export {announcement, fetchAnnouncement, announcementDetail, announcementById }