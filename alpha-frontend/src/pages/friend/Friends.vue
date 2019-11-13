<template>
  <q-page class="q-pa-md q-gutter-md">
    <div>친구 <q-icon name="autorenew" @click="findFriends"></q-icon></div>
    <div
        class="row"
        v-for="(item, idx) in friends.models"
        :key="idx"
    >
      <span>{{(idx + 1) + ' ' + item.friendMember.name}}</span>
      <q-btn
          label="chat"
          @click="chat(item)"
      ></q-btn>
    </div>
    <div @click="fromRequestsVisible = !fromRequestsVisible">{{ '요청받은 목록 (' + requests.requests.length + ')'}} <q-icon :name="fromRequestsVisible ? 'keyboard_arrow_up' : 'keyboard_arrow_down'"></q-icon></div>
    <q-slide-transition>
      <div v-show="fromRequestsVisible">
        <div class="row" v-for="(item, idx) in requests.requests" :key="idx">
          <div class="row">
            {{ item.fromMember.name }}
            <q-btn :label="$t('approve')" @click="approve(item)" size="xs" no-caps></q-btn>
            <q-btn :label="$t('reject')" @click="reject(item)" size="xs" no-caps></q-btn>
          </div>
        </div>
      </div>
    </q-slide-transition>
    <div @click="toRequestsVisible = !toRequestsVisible">{{ '요청보낸 목록 (' + requests.myRequests.length + ')'}} <q-icon :name="toRequestsVisible ? 'keyboard_arrow_up' : 'keyboard_arrow_down'"></q-icon></div>
    <q-slide-transition>
      <div v-show="toRequestsVisible">
        <div class="row" v-for="(item, idx) in requests.myRequests" :key="idx">
          <div class="row">
            <span class="text-subtitle2 q-mr-sm">{{ item.toMember.name }}</span> {{ $moment(item.requestDate).fromNow() }}
          </div>
        </div>
      </div>
    </q-slide-transition>

    <q-dialog
        v-model="requestDialog"
        persistent
    >
      <q-card style="min-width: 350px">
        <q-card-section class="row items-center">
          <q-space></q-space>
          <q-btn
              icon="close"
              flat
              round
              dense
              v-close-popup
          ></q-btn>
        </q-card-section>
        <q-card-section>
          <q-input
              :label="$t('input.friend.id')"
              prefix="@"
              ref="toId"
              v-model="toId"
              :rules="[val => !!val || 'Field is required']"
              @keyup.enter="request"
          />
        </q-card-section>
        <q-card-actions
            align="right"
            class="text-primary"
        >
          <q-btn
              flat
              :label="$t('input.request.friend')"
              @click="request"
          ></q-btn>
        </q-card-actions>
      </q-card>
    </q-dialog>
    <q-page-sticky
        position="bottom-right"
        :offset="[18, 18]"
    >
      <q-btn
          color="primary"
          :label="$t('input.request.friend')"
          @click="showRequestDialog"
          no-caps
      ></q-btn>
    </q-page-sticky>
  </q-page>
</template>
<style>
</style>
<script>

import { Friends } from '../../model/Friend'
import { $t } from '../../boot/i18n'

export default {
  name: 'PageFriends',
  data () {
    return {
      toId: '',
      requestDialog: false,
      friends: new Friends(),
      requests: {},
      fromRequestsVisible: false,
      toRequestsVisible: false
    }
  },
  created () {
    this.findFriends()
  },
  methods: {
    findFriends () {
      this.friends.fetch()
      this.requestList()
    },
    showRequestDialog () {
      const me = this
      me.toId = ''
      me.requestDialog = true
    },
    request () {
      const me = this
      if (!me.$refs.toId.validate()) {
        return
      }
      me.$axios({
        url: '/api/private/friends/request',
        method: 'post',
        params: {
          toId: me.toId
        }
      }).then(() => {
        me.$q.notify({
          timeout: 3000,
          color: 'positive',
          message: $t('requested')
        })
        me.requestDialog = false
      }).catch((e) => {
        me.$q.notify({
          timeout: 3000,
          color: 'negative',
          message: $t(e.response.data.message)
        })
      })
    },
    requestList () {
      this.$axios({
        url: '/api/private/friends/requests',
        method: 'get'
      }).then((res) => {
        console.log(res.data)
        this.requests = res.data
      })
    },
    approve (data) {
      const me = this
      me.$axios({
        url: '/api/private/friends/request/approve',
        method: 'post',
        data: data
      }).then((res) => {
        me.requestList()
      })
    },
    reject (data) {
      const me = this
      me.$axios({
        url: '/api/private/friends/request/reject',
        method: 'post',
        data: data
      }).then((res) => {
        me.requestList()
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
