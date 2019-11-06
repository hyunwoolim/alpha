<template>
  <q-page>
    <div class="row justify-center q-pa-lg">
      <q-input v-model="input" @keydown.enter="send"></q-input>
    </div>
  </q-page>
</template>
<script>

export default {
  name: 'PageChat',
  data () {
    return {
      chatRoomId: '',
      input: '',
      socket: this.$socket
    }
  },
  created () {
    const param = this.$route.params
    console.log(param)
    if (param && param.chatRoomId) {
      this.chatRoomId = param.chatRoomId
      this.$join(param.chatRoomId)
      this.socket.on('chat', (data) => {
        this.$q.notify({
          timeout: 100,
          color: 'positive',
          message: data.msg
        })
      })
    } else {
      history.back()
    }
  },
  mounted () {
  },
  methods: {
    send () {
      console.log('sss')
      this.$sendMessage({ msg: '메세지', name: 'test', room: this.chatRoomId })
    }
  }
}
</script>
<style lang="stylus">
</style>
