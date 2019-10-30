<template>
  <q-page>
    {{$t('logout.processing')}}
  </q-page>
</template>
<script>
import { $t } from '../../boot/i18n'

export default {
  name: 'PageLogout',
  data () {
    return {
    }
  },
  created () {
    const me = this
    me.$store.dispatch('sMember/logout').then(() => {
      me.$q.notify({
        timeout: 500,
        color: 'positive',
        message: $t('logout.succeeded')
      })
      me.$router.push('/')
    })
  }
}
</script>
