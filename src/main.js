import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import http from '@/utils/http'

// vue中导出excel表格模板
import FileSaver from 'file-saver'
import XLSX from 'xlsx'

import mitt from 'mitt'

import  "@/assets/css/global.css"



const app = createApp(App)

//ElementPles的icons
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

//国际化
app.use(ElementPlus, {
  locale: zhCn,
})

//全局事件总线
app.config.globalProperties.$bus = new mitt()

//axios
app.config.globalProperties.$http=http;

// Vue.prototype.$FileSaver = FileSaver; //设置全局
// Vue.prototype.$XLSX = XLSX; //设置全局
// app.config.globalProperties.$XLSX=XLSX;
// app.config.globalProperties.$FileSaver=FileSaver;

app.use(router)
    .use(store)
    .use(ElementPlus)
    .mount('#app')

