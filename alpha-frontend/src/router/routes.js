
const routes = [
  {
    path: '/',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { name: 'home', path: '/', component: () => import('pages/Index.vue') },
      { name: 'myInfo', path: '/my-info', component: () => import('pages/MyInfo.vue'), meta: { authorities: ['USER1'] } },
      { name: 'login', path: '/login', component: () => import('pages/auth/Login.vue') },
      { name: 'logout', path: '/logout', component: () => import('pages/auth/Logout.vue') },
      { name: 'signup', path: '/signup', component: () => import('pages/auth/SignUp.vue') },
      { name: 'friends', path: '/friends', component: () => import('pages/friend/Friends.vue'), meta: { authorities: ['USER1'] } },
      { name: 'game1', path: '/games/game1/:roomId', component: () => import('pages/game/Game1.vue') },
      { name: 'chats', path: '/chats', component: () => import('pages/chat/Chats.vue') },
      { name: 'chat', path: '/chat/:roomId', component: () => import('pages/chat/Chat.vue') },
      { name: 'board', path: '/board', component: () => import('pages/board/Board.vue') }
    ]
  },
  {
    path: '/admin',
    component: () => import('layouts/AdminLayout.vue'),
    meta: { authorities: ['ADMIN'] },
    children: [
      { name: 'main', path: '/admin/main', component: () => import('pages/admin/Main.vue') }
    ]
  }
]

// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: () => import('pages/Error404.vue')
  })
}
export default routes
