import { getAnnounment, getAnnounmentById, cerateAnnouncement, updateAnnouncement, deleteAnnoumcement } from "./data-handler.js"
import { ref } from 'vue'

const announcement = ref([])
const announcementDetail = ref("")

const fetchAnnouncement = async () => {
  announcement.value = await getAnnounment()
  
}



const announcementById = async (id) => {
  announcementDetail.value = await getAnnounmentById(id)
}

const addAnnouncement = async (ann) => { 
  await cerateAnnouncement(ann)
  await fetchAnnouncement()
}

const changeTime = (date) => {
  const time = new Date(date)
  if (time.getFullYear() > 1970) {
    return time.toLocaleString('en-GB', { dateStyle: 'medium', timeStyle: 'short' })
  }
}

const deleteAnnoumcementById = async (annId) => {
  await deleteAnnoumcement(annId)
  await fetchAnnouncement()  

}



export { announcement, fetchAnnouncement, announcementDetail, announcementById, addAnnouncement, changeTime ,deleteAnnoumcementById}