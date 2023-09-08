

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElmentUI from 'element-plus'
import 'element-plus/theme-chalk/index.css'

// 导入icon
// 导入样式

import App from './App.vue'

// import Mock from 'mockjs'

// Mock.mock('http://localhost:8080/getData', 'get', {
//   data: [1, 2, 3, 4, 5]
// });

const app = createApp(App)

app.use(createPinia())
app.use(ElmentUI)

app.mount('#app')
