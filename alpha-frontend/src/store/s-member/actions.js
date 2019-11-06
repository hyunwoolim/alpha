import axios from 'axios'

export async function checkSession (context) {
  await axios({
    url: '/api/public/session',
    method: 'get',
    async: false
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
