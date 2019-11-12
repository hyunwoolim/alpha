import { Model, Collection } from 'vue-mc'
import { $t } from '../boot/i18n'
import _ from 'lodash'
import axios from 'axios'

export class Member extends Model {
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
      mid: async (value, prop, model) => {
        if (_.isEmpty(value)) {
          return $t('validation.mid.required')
        }
        let memberExists = await axios.get(`/api/public/member/exists?mid=${value}`)
        if (memberExists.data) {
          return $t('account.exists.already')
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
