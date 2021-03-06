import io from 'socket.io-client/dist/socket.io.slim'
const socket = io('http://localhost:3000')

export default async ({ Vue }) => {
  Vue.prototype.$sendMessage = ($data) => {
    socket.emit('chat', $data)
  }
  Vue.prototype.$joinChatRoom = (test) => {
    socket.emit('joinChatRoom', test)
  }
  Vue.prototype.$joinGameRoom = (roomId) => {
    socket.emit('joinGameRoom', roomId)
  }
  Vue.prototype.$game1 = ($data) => {
    socket.emit('game1', $data)
  }
  Vue.prototype.$socket = socket
}
