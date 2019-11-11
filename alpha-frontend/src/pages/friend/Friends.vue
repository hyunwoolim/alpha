<template>
  <q-page class="q-pa-md q-gutter-md">
    <div>친구</div>
    <div class="row" v-for="(item, idx) in friends.models" :key="idx">
      <span>{{(idx + 1) + ' ' + item.friendMember.name}}</span>
      <q-btn label="chat" @click="chat(item)"></q-btn>
    </div>
    <!-- <q-input v-model="toId"></q-input>
    <q-btn label="request" @click="request"></q-btn>
    <div>요청받은 목록</div>
    <div class="row" v-for="(item, idx) in requests" :key="idx">
      <span>{{item.fromMember.name}}</span>
      <q-btn label="approve" @click="approve(item)"></q-btn>
      <q-btn label="approve" @click="reject(item)"></q-btn>
    </div> -->
    <q-dialog v-model="requestDialog" persistent>
      <q-card style="min-width: 350px">
        <q-card-section class="row items-center">
          <q-space></q-space>
          <q-btn icon="close" flat round dense v-close-popup></q-btn>
        </q-card-section>
        <q-card-section>
          <q-input :label="$t('input.friend.id')" ref="toId" v-model="toId" :rules="[val => !!val || 'Field is required']" @keyup.enter="request" />
        </q-card-section>
        <q-card-actions align="right" class="text-primary">
          <q-btn flat :label="$t('input.request.friend')" @click="request"></q-btn>
        </q-card-actions>
      </q-card>
    </q-dialog>
    <q-page-sticky position="bottom-right" :offset="[18, 18]">
      <q-btn color="primary" :label="$t('input.request.friend')" @click="showRequestDialog" no-caps></q-btn>
    </q-page-sticky>
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
      requestDialog: false,
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
    showRequestDialog () {
      const me = this
      me.toId = ''
      me.requestDialog = true
    },
    request () {
      if (!this.$refs.toId.validate()) {
        return
      }
      this.$axios({
        url: '/api/private/friends/request',
        method: 'post',
        params: {
          toId: this.toId
        }
      }).then(() => {
      }).catch((e, t) => {
        console.log(e.response.data.message)
        console.log(t)
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
      this.$axios({
        url: `/api/private/chat/start`,
        method: 'post',
        data: data
      }).then((res) => {
        this.$router.push('/chat/' + res.data.roomId)
      })
    }
  }
}
</script>
<style lang="stylus">
</style>
