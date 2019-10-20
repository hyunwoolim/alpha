import { Model, Collection } from 'vue-mc'
import _ from 'lodash'

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
  options () {
    return {
      validateOnChange: true
    }
  }
  validation () {
    return {
      email: (value, prop, model) => {
        if (_.isEmpty(value)) {
        }
      }
    }
  }
}

export class Members extends Collection {
  model () {
    return Member
  }
  defaults () {
    return {
      id: ''
    }
  }
  routes () {
    return {
      fetch: '/api/members'
    }
  }
}
