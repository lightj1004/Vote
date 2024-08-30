import { createRouter, createWebHistory } from "vue-router";
import router_view from '@/views/router_view.js'

const routes = [
    ...router_view
]

const router = createRouter({
    routes,
    history: createWebHistory()
})

export default router;