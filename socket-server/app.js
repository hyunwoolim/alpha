const io = require('socket.io')(3000)
const chat = io.of('/chat').on('connection', (socket) => {
  socket.emit('a message', {
    that: 'only'
    , '/chat': 'will get'
  });
  chat.emit('a message', {
    everyone: 'in'
    , '/chat': 'will get'
  });
})
const news = io.of('/news').on('connection', function (socket) {
  socket.emit('item', { news: 'item' });
});
