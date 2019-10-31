import Vue from 'vue'
import VueI18n from 'vue-i18n'
import messages from 'src/i18n'
import CountryFlag from 'vue-country-flag'

Vue.use(VueI18n)
Vue.component('v-country-flag', CountryFlag) // https://www.npmjs.com/package/vue-country-flag

const i18n = new VueI18n({
  locale: 'ko-kr',
  fallbackLocale: 'ko-kr',
  messages
})

export default ({ app }) => {
  // Set i18n instance on app
  app.i18n = i18n
}

export function $t (message) {
  return i18n.t(message)
}

export { i18n }
