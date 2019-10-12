import { Model } from 'vue-mc'

export class Member extends Model {
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
      save: '/api/public/member'
    }
  }
}
