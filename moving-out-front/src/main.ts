import { createApp } from "vue";
import App from "./App.vue";
import VueAxios from "vue-axios";
import axios from 'axios'

const app = createApp(App)
app.use(VueAxios, axios)

app.mount("#app")