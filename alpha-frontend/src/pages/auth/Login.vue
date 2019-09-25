<template>
  <q-page>
    <v-btn @click="naverLogin">로그인</v-btn>
  </q-page>
</template>
<script lang="ts">
export default {
  name: 'PageLogin',
  data () {
    return {
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
      me.naverData.naverLoginUrl += '&client_id=' + me.naverData.clientId
      me.naverData.naverLoginUrl += '&redirect_uri=' + me.naverData.redirectUri
      me.naverData.naverLoginUrl += '&state=' + this.state
      location.href = me.naverData.naverLoginUrl
    }
  }
}
</script>
