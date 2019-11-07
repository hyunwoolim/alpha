import io from 'socket.io-client/dist/socket.io.slim'
const socket = io('http://localhost:3000')

export default async ({ Vue }) => {
  Vue.prototype.$sendMessage = ($payload) => {
    console.log('$send')
    socket.emit('chat', $payload)
  }
  Vue.prototype.$join = (test) => {
    socket.emit('joinRoom', test)
  }
  Vue.prototype.$socket = socket
}
