<template>
  <q-page>
    <div class="row justify-center q-pa-lg">
      Battery status is: <strong>{{ batteryStatus }}</strong>
    </div>
  </q-page>
</template>
<script>

export default {
  name: 'PageChats',
  data () {
    return {
      batteryStatus: 'determining...'
    }
  },
  created () {
    window.addEventListener('batterystatus', this.updateBatteryStatus, false)
  },
  beforeDestroy () {
    window.removeEventListener('batterystatus', this.updateBatteryStatus, false)
  },
  mounted () {
  },
  methods: {
    updateBatteryStatus (status) {
      this.batteryStatus = `Level: ${status.level}, plugged: ${status.isPlugged}`
    }
  }
}
</script>
<style lang="stylus">
</style>
