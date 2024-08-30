import Login from "./Login.vue";
import Register from "./Register.vue";

const router_user = [
    { name: "login-link", path: "/login", component: Login },
    { name: "register-link", path: "/register", component: Register }
]

export default router_user;