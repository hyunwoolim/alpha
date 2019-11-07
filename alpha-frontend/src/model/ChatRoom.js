import { Collection, Model } from 'vue-mc'

export class ChatRoom extends Model {
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

export class ChatRooms extends Collection {
  model () {
    return ChatRoom
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
