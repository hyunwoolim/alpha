const io = require('socket.io')(3000)
console.log('1234')
const chat = io.on('connection', (socket) => {
  console.log('0000')
  socket.emit('chat', (data) => {
    console.log('999999')
    console.log(data)
    socket.broadcast.emit('chat', data)
  })
  /* chat.emit('a message', {
    everyone: 'in'
    , '/chat': 'will get'
  }); */
})
