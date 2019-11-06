import { Collection, Model } from 'vue-mc'

export class Friend extends Model {
  defaults () {
    return {
      id: '',
      friendId: '',
      relationDate: null,
      friendMember: null
    }
  }

  routes () {
    return {}
  }
}

export class Friends extends Collection {
  model () {
    return Friend
  }

  defaults () {
    return {
      id: ''
    }
  }

  routes () {
    return {
      fetch: '/api/private/friends'
    }
  }
}
