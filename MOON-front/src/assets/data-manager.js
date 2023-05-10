import { getAnnounment, getAnnounmentById, cerateAnnouncement, updateAnnouncement, deleteAnnoumcement, getCategories, getCategoriesById, getMode } from "./data-handler.js"
import { ref } from 'vue'

const announcement = ref([])
const announcementDetail = ref("")
const categories = ref([])
const categoriesDetail = ref("")
const mode = ref([])

//Announcement
const fetchAnnouncement = async () => {
  announcement.value = await getAnnounment()
}

const fetchMode = async () => {
  mode.value = await getMode()
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

const updateAnnouncementbyId = async (ann ,annId) => {
  await updateAnnouncement(ann, annId)
  await fetchAnnouncement()
}


// Category
const fetchCategory = async () => {
  categories.value = await getCategories()
}

const categoryById =  async (id) => {
  categoriesDetail.value = await getCategoriesById(id)
}

export { announcement, fetchAnnouncement, announcementDetail, announcementById, addAnnouncement, changeTime ,deleteAnnoumcementById,
   fetchCategory, categories, categoryById, categoriesDetail, updateAnnouncementbyId , fetchMode}