import { createApp } from 'vue'
import router from './router/router'
import App from './App.vue'
import FontAwesomeIcon from '@/plugins/fontawesome.js'

createApp(App)
    .use(router)
    .component("font-awesome-icon", FontAwesomeIcon)
    .mount('#app')
