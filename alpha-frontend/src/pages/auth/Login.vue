<template>
  <q-page>
    <q-btn @click="naverLogin" label="로그인"></q-btn>
  </q-page>
</template>
<script lang="ts">
import { Member } from '../../model/Member'

export default {
  name: 'PageLogin',
  data () {
    return {
      member: new Member(),
      naverData: {
        clientId: 'ggsFtWFewo9oiFQP1Tjz',
        redirectUri: 'http://localhost:4041/login/callback',
        state: this.generateState,
        naverLoginUrl: 'https://nid.naver.com/oauth2.0/authorize?response_type=code'
      }
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
    naverLogin () {
      const me = this
      me.member.save()
    }
  }
}
</script>
