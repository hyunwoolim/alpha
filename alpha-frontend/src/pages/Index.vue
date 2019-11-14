<template>
  <q-page class="q-pa-lg">
    <div v-show="$store.state.sMember.isAuthenticated">
      {{ $store.state.sMember.info.name }} 환영합니다.
    </div>
  </q-page>
</template>
<style>
</style>
<script>
export default {
  name: 'PageIndex',
  created () {
    window.addEventListener('load', function () {
      Notification.requestPermission(function (status) {
        if (Notification.permission !== status) {
          Notification.permission = status
        }
      })
    })
  }
}
</script>
<style lang="stylus">
</style>
