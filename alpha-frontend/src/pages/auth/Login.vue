<template>
  <q-page>
    <div class="row justify-center q-pa-lg">
      <q-form>
        {{$t('login')}}
        <q-input square outlined name="username" v-model="email" :label="$t('email')" class="q-mb-lg" type="email" @keyup.enter="login"></q-input>
        <q-input square outlined name="password" v-model="password" :label="$t('password')" type="password" class="q-mb-lg" @keyup.enter="login"></q-input>
        <q-btn outline :label="$t('login')" @click="login"></q-btn>
        <q-space></q-space>
        <q-btn outline :label="$t('signup')" @click="goPageSignUp"></q-btn>
      </q-form>
    </div>
  </q-page>
</template>
<script lang="ts">
import { Member } from '../../model/Member'

export default {
  name: 'PageLogin',
  data () {
    return {
      member: new Member(),
      email: '',
      password: ''
    }
  },
  created () {

  },
  mounted () {
  },
  methods: {
    generateState () {
      return 'test'
    },
    goPageSignUp () {
      const me = this
      me.$router.push('/signup')
    },
    login () {
      const me = this
      if (!me.email) {
        me.$refs.email.focus()
        return
      }
      if (!me.password) {
        me.$refs.password.focus()
        return
      }
      let formData = new FormData()
      formData.append('username', me.email)
      formData.append('password', me.password)
      me.$axios({
        url: '/api/secure-login',
        method: 'post',
        data: formData
      }).then((res) => {
        console.log('a')
        console.log(res)
      }).catch((e) => {
        console.log(e)
        me.$q.notify({
          timeout: 500,
          color: 'negative',
          message: e.toString()
        })
        me.$axios({
          url: '/api/public/session',
          method: 'get'
        }).then(res => {
          console.log(res)
        })
      })
    }
  }
}
</script>
