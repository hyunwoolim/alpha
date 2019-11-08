const io = require('socket.io')(3000)
const chat = io.on('connection', (socket) => {
  socket.on('chat', (data) => {
    io.to(data.room).emit('chat', data)
  })
  socket.on('joinRoom', (data) => {
    socket.join(data, () => {
    })
  })
  /* chat.emit('a message', {
    everyone: 'in'
    , '/chat': 'will get'
  }); */
})
