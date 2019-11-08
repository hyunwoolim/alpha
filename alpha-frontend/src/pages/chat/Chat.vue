<template>
  <q-page>
    <div ref="chatbox" class="justify-center q-pa-xs" style="margin-bottom: 56px;">
      <div v-for="(item, idx) in chatData" :key="idx">
        <q-chat-message
          class="q-ma-xs"
          :name="item.member.name"
          :text="[item.msg]"
          :sent="$store.state.sMember.info.id === item.member.id"
        ></q-chat-message>
      </div>
    </div>
    <q-input
      ref="chatbar"
      v-model="input"
      class="full-width"
      style="position: fixed; bottom: 0px;"
      outlined
      autogrow
      @keydown.enter="send">
      <template v-slot:after>
        <q-btn round dense flat icon="send" @click="send"></q-btn>
      </template>
    </q-input>
  </q-page>
</template>
<script>
export default {
  name: 'PageChat',
  data () {
    return {
      roomId: '',
      input: '',
      socket: this.$socket,
      chatData: []
    }
  },
  created () {
    const me = this
    const param = me.$route.params
    if (param && param.roomId) {
      me.roomId = param.roomId
      me.$join(param.roomId)
      me.socket.on('chat', (data) => {
        me.chatData.push(data)
      })
    } else {
      history.back()
    }
  },
  mounted () {
  },
  watch: {
    chatData () {
    }
  },
  methods: {
    send (e) {
      const me = this
      if (!e.shiftKey && !me.input) {
        e.preventDefault()
      }
      if (!e.shiftKey && me.$store.state.sMember.isAuthenticated && me.input && me.roomId) {
        this.$sendMessage({
          msg: me.input,
          room: me.roomId,
          member: me.$store.state.sMember.info
        })
        this.input = ''
        e.preventDefault()
      }
    }
  }
}
</script>
<style lang="stylus">
</style>
