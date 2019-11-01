import io from 'socket.io-client'
const socket = io('http://localhost')

export default async ({ Vue }) => {
  Vue.prototype.$sendMessage = ($payload) => {
    socket.emit('chat', {
      msg: $payload.msg,
      name: $payload.name
    })
  }
  Vue.prototype.$socket = socket
}
