import { Model } from 'vue-mc'

export class SMember extends Model {
  defaults () {
    return {
      id: '',
      email: '',
      password: '',
      name: ''
    }
  }

  routes () {
    return {
      fetch: '/api/public/session'
    }
  }

  options () {
  }
}
