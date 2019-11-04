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
      input: '',
      socket: this.$socket
    }
  },
  created () {
    this.socket.on('chat', (data) => {
      console.log('와따')
      console.log(data)
    })
  },
  mounted () {
  },
  methods: {
    send () {
      console.log('sss')
      this.$join(this.input)
      this.$sendMessage({ msg: '메세지', name: 'test', room: ('room' + this.input) })
    }
  }
}
</script>
<style lang="stylus">
</style>
