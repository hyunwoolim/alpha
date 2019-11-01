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
      input: ''
    }
  },
  created () {
  },
  mounted () {
  },
  methods: {
    send () {
      console.log('sss')
      this.$sendMessage({ msg: this.input, name: 'test' })
    }
  }
}
</script>
<style lang="stylus">
</style>
