import _ from 'lodash'
export function set (state, data) {
  state.info = data
  if (_.isEmpty(data)) {
    state.authority = ''
    state.isAuthenticated = false
  } else {
    state.authority = data.authorities[0].authority
    state.isAuthenticated = true
  }
}
