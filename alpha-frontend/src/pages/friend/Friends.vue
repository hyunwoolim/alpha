<template>
  <q-page class="q-pa-md q-gutter-md">
    <q-input v-model="toId"></q-input>
    <q-btn label="request" @click="request"></q-btn>
    <div>요청받은 목록</div>
    <div v-for="(item, idx) in requests" :key="idx">
      {{item.fromMember.name}}
    </div>
  </q-page>
</template>
<style>
</style>
<script>

export default {
  name: 'PageFriends',
  data () {
    return {
      toId: '',
      requests: []
    }
  },
  created () {
    this.requestList()
  },
  methods: {
    request () {
      this.$axios({
        url: '/api/private/friend/request',
        method: 'post',
        params: {
          toId: this.toId
        }
      })
    },
    requestList () {
      this.$axios({
        url: '/api/private/friend/requests',
        method: 'get'
      }).then((res) => {
        console.log(res)
        this.requests = res.data
      })
    }
  }
}
</script>
<style lang="stylus">
</style>
