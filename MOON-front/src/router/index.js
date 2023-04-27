import { createRouter, createWebHistory } from 'vue-router'

import Announcement from '../components/Announcement.vue'
import AnnouncementView from '../components/AnnouncementView.vue'


const history = createWebHistory()
const routes = [
    {
        path: '/',
        name: 'Home',
        component: Announcement
    },
    {
        path: '/view/:id',
        name: 'AnnouncementView',
        component: AnnouncementView
    }
]
const router = createRouter({history , routes})
export default router
