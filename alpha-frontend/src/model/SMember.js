import { Model } from 'vue-mc'

export class SMember extends Model {
  defaults () {
    return {
      id: '',
      mid: '',
      password: '',
      name: ''
    }
  }

  routes () {
    return {
      fetch: '/api/public/session',
      save: '/api/private/session'
    }
  }

  get secure () {
    this.password = ''
  }

  options () {
  }
}
