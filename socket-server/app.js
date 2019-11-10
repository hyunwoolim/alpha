const io = require('socket.io')(3000)
const chat = io.on('connection', (socket) => {
  socket.on('chat', (data) => {
    io.to(data.room).emit('chat', data)
  })
  socket.on('joinRoom', (data) => {
    socket.join(data, () => {
    })
  })
  socket.on('game1', (data) => {
    console.log('게임')
    console.log(data)
    io.to(data.room).emit('game1', data)
  })
  /* chat.emit('a message', {
    everyone: 'in'
    , '/chat': 'will get'
  }); */
})
