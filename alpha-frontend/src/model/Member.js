import { Model, Collection } from 'vue-mc'
import { $t } from '../boot/i18n'
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
          return $t('validation.email.required')
        }
      },
      name: (value, prop, model) => {
        if (_.isEmpty(value)) {
          return $t('validation.name.required')
        }
      },
      password: (value, prop, model) => {
        if (_.isEmpty(value)) {
          return $t('validation.password.required')
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
