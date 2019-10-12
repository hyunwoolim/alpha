<template>
  <q-page>
    <img @click="naverLogin" class="cursor-pointer login-btn" src="~assets/images/auth/naver_login_btn.png">
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
        redirectUri: 'http://localhost:4041/api/auth/login',
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
      me.naverData.naverLoginUrl += '&client_id=' + me.naverData.clientId + '&redirect_uri=' + me.naverData.redirectUri + '&state=' + this.state
      location.href = me.naverData.naverLoginUrl
    }
  }
}
</script>
