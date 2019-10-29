import { SMember } from '../../model/SMember'

export function get (context) {
  let sMember = new SMember()
  sMember.fetch()
  context.commit('set', sMember)
}
