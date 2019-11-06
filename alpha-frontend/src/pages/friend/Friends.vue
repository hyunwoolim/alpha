<template>
  <q-page class="q-pa-md q-gutter-md">
    <div>친구</div>
    <div class="row" v-for="(item, idx) in friends.models" :key="idx">
      <span>{{(idx + 1) + ' ' + item.friendMember.name}}</span>
      <q-btn label="chat" @click="chat(item)"></q-btn>
    </div>
    <q-input v-model="toId"></q-input>
    <q-btn label="request" @click="request"></q-btn>
    <div>요청받은 목록</div>
    <div class="row" v-for="(item, idx) in requests" :key="idx">
      <span>{{item.fromMember.name}}</span>
      <q-btn label="approve" @click="approve(item)"></q-btn>
      <q-btn label="approve" @click="reject(item)"></q-btn>
    </div>
  </q-page>
</template>
<style>
</style>
<script>

import { Friends } from '../../model/Friend'

export default {
  name: 'PageFriends',
  data () {
    return {
      toId: '',
      friends: new Friends(),
      requests: []
    }
  },
  created () {
    this.findFriends()
    this.requestList()
  },
  methods: {
    findFriends () {
      this.friends.fetch().then(() => {
        console.log(this.friends)
      })
    },
    request () {
      this.$axios({
        url: '/api/private/friends/request',
        method: 'post',
        params: {
          toId: this.toId
        }
      })
    },
    requestList () {
      this.$axios({
        url: '/api/private/friends/requests',
        method: 'get'
      }).then((res) => {
        console.log(res)
        this.requests = res.data
      })
    },
    approve (data) {
      this.$axios({
        url: '/api/private/friends/request/approve',
        method: 'post',
        data: data
      }).then((res) => {
        console.log(res)
      })
    },
    reject (data) {
      this.$axios({
        url: '/api/private/friends/request/reject',
        method: 'post',
        data: data
      }).then((res) => {
        console.log(res)
      })
    },
    chat (data) {
      console.log(data)
      this.$axios({
        url: '/api/private/chat/start',
        method: 'post',
        data: data
      }).then((res) => {
        this.$router.push('/chat/' + res.data.id)
      })
    }
  }
}
</script>
<style lang="stylus">
</style>
