import io from 'socket.io-client'
const socket = io('http://localhost:3000')

export default async ({ Vue }) => {
  Vue.prototype.$sendMessage = ($payload) => {
    console.log('$send')
    socket.emit('chat', {
      msg: $payload.msg,
      name: $payload.name,
      room: $payload.room
    })
  }
  Vue.prototype.$join = (test) => {
    socket.emit('joinRoom', 'room' + test)
  }
  Vue.prototype.$socket = socket
}
