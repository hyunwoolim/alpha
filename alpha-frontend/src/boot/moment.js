import moment from 'moment'

export default async ({ Vue }) => {
  moment.locale('ko')
  Vue.prototype.$moment = moment
}
