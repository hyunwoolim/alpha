<template>
  <q-page>
    <div class="row justify-center q-pa-lg">
      <q-input v-model="input" @keydown.enter="send"></q-input>
    </div>
  </q-page>
</template>
<script>

export default {
  name: 'PageChat2',
  data () {
    return {
      input: '',
      socket: this.$socket
    }
  },
  created () {
    this.$join('room2')
    this.socket.on('chat', (data) => {
      console.log('와따')
      console.log(data)
      this.$q.notify({
        timeout: 100,
        color: 'positive',
        message: data.msg
      })
    })
  },
  mounted () {
  },
  methods: {
    send () {
      console.log('sss')
      this.$sendMessage({ msg: '메세지', name: 'test', room: 'room2' })
    }
  }
}
</script>
<style lang="stylus">
</style>
