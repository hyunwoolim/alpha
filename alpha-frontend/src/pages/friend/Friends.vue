<template>
  <q-page class="q-pa-md q-gutter-md">
    <div>
      <span class="text-h6">{{ $t('friend') }}</span>
      <q-btn flat round size="xs" :loading="syncing" color="primary" @click="search" icon="ion-sync"></q-btn>
    </div>
    <div class="cursor-pointer" @click="fromRequestsVisible = !fromRequestsVisible">{{ '요청받은 목록 (' + ((requests.from) ? requests.from.length : 0) + ')'}} <q-icon :name="fromRequestsVisible ? 'keyboard_arrow_up' : 'keyboard_arrow_down'"></q-icon></div>
    <q-slide-transition>
      <div v-show="fromRequestsVisible">
        <div class="row" v-for="(item, idx) in requests.from" :key="idx">
          <div class="row">
            {{ item.fromMember.name }}
            <q-btn :label="$t('approve')" @click="approve(item)" size="xs" no-caps></q-btn>
            <q-btn :label="$t('reject')" @click="reject(item)" size="xs" no-caps></q-btn>
          </div>
        </div>
        <div v-show="!requests.from"> No Data </div>
      </div>
    </q-slide-transition>
    <div class="cursor-pointer" @click="toRequestsVisible = !toRequestsVisible">{{ '요청보낸 목록 (' + ((requests.to) ? requests.to.length : 0) + ')'}} <q-icon :name="toRequestsVisible ? 'keyboard_arrow_up' : 'keyboard_arrow_down'"></q-icon></div>
    <q-slide-transition>
      <div v-show="toRequestsVisible">
        <div class="row" v-for="(item, idx) in requests.to" :key="idx">
          <div class="row">
            <span class="text-subtitle2 q-mr-sm">{{ item.toMember.name }}</span> {{ $moment(item.requestDate).fromNow() }}
          </div>
        </div>
        <div v-show="!requests.to"> No Data </div>
      </div>
    </q-slide-transition>
    <div class="cursor-pointer" @click="friendsVisible = !friendsVisible">{{ '친구 목록 (' + ((friends.model) ? friends.model.length : 0) + ')'}} <q-icon :name="friendsVisible ? 'keyboard_arrow_up' : 'keyboard_arrow_down'"></q-icon></div>
    <q-slide-transition>
      <q-table v-show="friendsVisible"
          :data="friends.models"
          :columns="columns"
          hide-header
          hide-bottom
          flat
          :dense="$q.screen.lt.md"
      >
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="name" :props="props">
              {{ props.row.friendMember.name }}
              <span class="text-caption">{{ '(@' + props.row.friendMember.mid + ')' }}</span>
            </q-td>
            <q-td key="chat" :props="props">
              <q-btn
                  flat
                  round
                  label="chat"
                  @click="chat(props.row)"
              ></q-btn>
            </q-td>
          </q-tr>
        </template>
      </q-table>
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
      toRequestsVisible: false,
      friendsVisible: true,
      syncing: false,
      columns: [
        {
          name: 'name',
          required: true,
          align: 'left',
          field: 'friendMember'
        },
        {
          name: 'chat',
          required: true,
          align: 'right',
          field: 'friendMember'
        }
      ]
    }
  },
  created () {
    this.search()
  },
  methods: {
    async search () {
      const me = this
      me.syncing = true
      await me.friends.fetch().catch((e) => {
        console.log('aaa1')
      })
      let requestsResponse = await me.$axios.get('/api/private/friends/requests').catch((e) => {
        console.log('aaa2')
      })
      this.requests = requestsResponse.data
      me.syncing = false
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
    findRequests () {
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
