import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

// reset css
import '@/style/_index.scss';

createApp(App).use(store).use(router).mount('#app');
