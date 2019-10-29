import { SMember } from '../../model/SMember'

export function checkSession (context) {
  let sMember = new SMember()
  sMember.fetch()
  context.commit('set', sMember)
}
