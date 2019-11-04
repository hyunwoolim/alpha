const io = require('socket.io')(3000)
console.log('1234')
const chat = io.on('connection', (socket) => {
  socket.on('chat', (data) => {
    console.log(data)
    console.log('여기')
    io.to(data.room).emit('chat', data)
  })
  socket.on('joinRoom', (data) => {
    console.log(data)
    socket.join(data, () => {
      console.log('server join ' + data)
    })
  })
  /* chat.emit('a message', {
    everyone: 'in'
    , '/chat': 'will get'
  }); */
})
