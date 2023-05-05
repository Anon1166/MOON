import { createRouter, createWebHistory } from 'vue-router'

import Announcement from '../components/Announcement.vue'
import AnnouncementView from '../components/AnnouncementView.vue'
import PageNotfound from '../components/PageNotfound.vue'
import AnnouncementCreate from '../components/AnnouncementCreate.vue'
import AnnouncementEdit from '../components/AnnouncementEdit.vue'

const history = createWebHistory(import.meta.env.BASE_URL)
const routes = [
    {
        path: '/admin/announcement',
        name: 'Home',
        component: Announcement
    },
    {
        path: '/admin/announcement/:id',
        name: 'AnnouncementView',
        component: AnnouncementView
    },
    {
        path: '/:notfoundpath(.*)',
        name: 'PageNotfound',
        component: PageNotfound
    },
    {
        path: '/admin/announcement/add',
        name: 'AddAnnouncement',
        component: AnnouncementCreate
    },
    {
        path: '/admin/announcement/:id/edit',
        name: 'AddAnnouncementEdit',
        component: AnnouncementEdit
    }
]
const router = createRouter({ history, routes })
export default router
