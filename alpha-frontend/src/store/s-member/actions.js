import axios from 'axios'

export function checkSession (context) {
  axios({
    url: '/api/public/session',
    method: 'get'
  }).then((res) => {
    if (res && res.data) {
      res.data.password = ''
    }
    context.commit('set', res.data)
  })
}
export function logout (context) {
  axios({
    url: '/api/logout',
    method: 'get'
  }).then((res) => {
    context.commit('set', {})
  })
}
