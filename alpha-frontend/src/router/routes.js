
const routes = [
  {
    path: '/',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { name: 'home', path: '/', component: () => import('pages/Index.vue') },
      { name: 'myInfo', path: '/my-info', component: () => import('pages/MyInfo.vue'), meta: { authorities: ['USER1'] } },
      { name: 'login', path: '/login', component: () => import('pages/auth/Login.vue') },
      { name: 'logout', path: '/logout', component: () => import('pages/auth/Logout.vue') },
      { name: 'signup', path: '/signup', component: () => import('pages/auth/SignUp.vue') }
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
