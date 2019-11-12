<template>
  <q-page class="q-pa-md q-gutter-md">
    <q-input
        v-model="model.mid"
        prefix="@"
        readonly
        :label="$t('mid')"
    ></q-input>
    <q-input
        v-model="model.name"
        :label="$t('name')"
    ></q-input>
    <q-btn
        label="save"
        @click="save"
    ></q-btn>
  </q-page>
</template>
<style>
</style>
<script>
import { SMember } from '../model/SMember'
import { $t } from '../boot/i18n'

export default {
  name: 'PageMyInfo',
  data () {
    return {
      model: new SMember()
    }
  },
  async created () {
    await this.$store.dispatch('sMember/checkSession')
    if (this.$store.state.sMember.isAuthenticated) {
      this.model.fetch()
    }
  },
  methods: {
    save () {
      const me = this
      me.model.save().then(() => {
        this.$store.dispatch('sMember/checkSession')
      }).then(() => {
        me.$q.notify({
          timeout: 500,
          color: 'positive',
          message: $t('success')
        })
      }).catch((e) => {
        this.$t('failed')
      })
    }
  }
}
</script>
<style lang="stylus">
</style>
