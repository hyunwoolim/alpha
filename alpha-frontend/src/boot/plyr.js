import VuePlyr from 'vue-plyr'

export default async ({ Vue }) => {
  Vue.use(VuePlyr, {
    plyr: {
      fullscreen: { enabled: true }
    },
    emit: ['ended']
  })
}
