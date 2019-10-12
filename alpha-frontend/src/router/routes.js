
const routes = [
  {
    path: '/',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') },
      { path: '/my-info', component: () => import('pages/MyInfo.vue') },
      { path: '/login', component: () => import('pages/auth/Login.vue') },
      { path: '/signup', component: () => import('pages/auth/SignUp.vue') }
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
