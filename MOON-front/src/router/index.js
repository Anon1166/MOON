import { createRouter, createWebHistory } from 'vue-router'

import Announcement from '../components/Announcement.vue'
import AnnouncementView from '../components/AnnouncementView.vue'
import PageNotfound from '../components/PageNotfound.vue'


const history = createWebHistory()
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
    }
]
const router = createRouter({ history, routes })
export default router
