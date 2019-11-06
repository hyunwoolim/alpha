<template>
  <q-page class="q-pa-md q-gutter-md">
    <q-input v-model="model.name"></q-input>
    <q-btn label="save" @click="save"></q-btn>
  </q-page>
</template>
<style>
</style>
<script>
import { SMember } from '../model/SMember'

export default {
  name: 'PageMyInfo',
  data () {
    return {
      model: new SMember()
    }
  },
  created () {
    console.log(this.$store.state.sMember)
    this.model.fetch()
  },
  methods: {
    save () {
      this.model.save().then(() => {
        this.$store.dispatch('sMember/checkSession')
      })
    }
  }
}
</script>
<style lang="stylus">
</style>
