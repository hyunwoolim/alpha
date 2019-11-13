<template>
  <q-page>
    <div class="row justify-center q-pa-lg">
      <q-form>
        {{$t('login')}}
        <q-input square outlined name="username" v-model="mid" :label="$t('mid')" class="q-mb-lg" @keyup.enter="login"></q-input>
        <q-input square outlined name="password" v-model="password" :label="$t('password')" type="password" class="q-mb-lg" @keyup.enter="login"></q-input>
        <q-btn outline :label="$t('login')" @click="login"></q-btn>
        <q-space></q-space>
        <q-btn outline :label="$t('signup')" @click="goPageSignUp"></q-btn>
      </q-form>
    </div>
  </q-page>
</template>
<script>
import { Member } from '../../model/Member'
import { $t } from '../../boot/i18n'

export default {
  name: 'PageLogin',
  data () {
    return {
      member: new Member(),
      mid: '',
      password: ''
    }
  },
  async created () {
    const me = this
    await this.$store.dispatch('sMember/checkSession')
    if (me.$store.state.sMember.isAuthenticated) {
      me.$q.notify({
        timeout: 500,
        color: 'positive',
        message: $t('login.already')
      })
      me.$router.push('/')
    }
  },
  mounted () {
  },
  methods: {
    goPageSignUp () {
      const me = this
      me.$router.push('/signup')
    },
    login () {
      const me = this
      if (!me.mid) {
        me.$refs.mid.focus()
        return
      }
      if (!me.password) {
        me.$refs.password.focus()
        return
      }
      let formData = new FormData()
      formData.append('username', me.mid)
      formData.append('password', me.password)
      me.$axios({
        url: '/api/secure-login',
        method: 'post',
        data: formData
      }).then((res) => {
        this.$store.dispatch('sMember/checkSession')
        me.$router.push(res.data).then((res) => {
          if (me.$route.query.error) {
            me.$q.notify({
              timeout: 500,
              color: 'negative',
              message: $t('login.failed')
            })
          }
        }).catch((e) => {
          if (e.name === 'NavigationDuplicated') {
            if (me.$route.query.error) {
              me.$q.notify({
                timeout: 500,
                color: 'negative',
                message: $t('login.failed')
              })
            }
          } else {
            me.$q.notify({
              timeout: 500,
              color: 'negative',
              message: $t('error')
            })
          }
        })
      }).catch((e) => {
        me.$q.notify({
          timeout: 500,
          color: 'negative',
          message: $t('login.failed')
        })
      })
    }
  }
}
</script>
