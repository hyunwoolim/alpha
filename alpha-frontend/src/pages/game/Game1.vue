<template>
  <q-page class="q-pa-md q-gutter-md">
    <q-btn label="가위" @click="out('scissor')"></q-btn>
    <q-btn label="바위" @click="out('rock')"></q-btn>
    <q-btn label="보" @click="out('paper')"></q-btn>
  </q-page>
</template>
<style>
</style>
<script>
export default {
  name: 'PageGame1',
  created () {
    const me = this
    const param = me.$route.params
    me.roomId = param.roomId
    me.$join(param.roomId)
    me.socket.on('game1', (data) => {
      console.log(data)
    })
  },
  mounted () {
  },
  data () {
    return {
      roomId: '',
      action: '',
      socket: this.$socket
    }
  },
  methods: {
    out (what) {
      const me = this
      me.action = what
      me.$game1({
        action: me.action,
        room: me.roomId,
        member: me.$store.state.sMember.info
      })
    }
  }
}
</script>
<style lang="stylus">
</style>
