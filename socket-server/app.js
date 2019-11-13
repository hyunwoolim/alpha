const io = require('socket.io')(3000)
const chat = io.on('connection', (socket) => {
  socket.on('chat', (data) => {
    io.to(data.room).emit('chat', data)
  })
  socket.on('joinChatRoom', (data) => {
    socket.join(data, () => {
    })
  })
  socket.on('disconnecting', (reason) => {
    let rooms = Object.keys(socket.rooms);
    console.log(rooms)
    console.log(rooms[0])
    let count = io.sockets.adapter.rooms[rooms[0]].length
    console.log(io.sockets.adapter.rooms)
    console.log(count)
    io.to(rooms[0]).emit('joinAlarm', count - 1)
  })
  socket.on('joinGameRoom', (data) => {
    socket.join(data, () => {
      console.log('입장')
      let count = io.sockets.adapter.rooms[data].length
      console.log(count)
      console.log(data)
      io.to(data).emit('joinAlarm', count)
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
