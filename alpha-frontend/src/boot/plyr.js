import VuePlyr from 'vue-plyr'

export default async ({ Vue }) => {
  Vue.use(VuePlyr, {
    plyr: {
    },
    emit: ['ended']
  })
}
