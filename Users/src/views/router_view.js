import Home from "./Home.vue";
import router_user from "./users/router_user";

const router_view = [
    ...router_user,
    { name: "home-link", path: "/", component: Home }
]

export default router_view;