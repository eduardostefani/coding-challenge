import Vue from 'vue'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faSearch, faBell, faList } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(faSearch, faBell, faList)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

// Bootstrap
Vue.use(BootstrapVue)

new Vue({
    render: h => h(App)
}).$mount('#app')

