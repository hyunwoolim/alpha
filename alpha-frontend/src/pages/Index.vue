<template>
  <q-page class="q-pa-md q-gutter-md">
    <div v-show="$store.state.sMember.isAuthenticated">
      {{ $store.state.sMember.info.name }} 환영합니다.
      {{ $store.state.sMember.info }}
    </div>
  </q-page>
</template>
<style>
</style>
<script>
export default {
  name: 'PageIndex',
  mounted () {
  }
}
</script>
<style lang="stylus">
</style>
