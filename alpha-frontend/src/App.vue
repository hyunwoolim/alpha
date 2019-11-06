<template>
  <div id="q-app">
    <router-view />
  </div>
</template>
<script>
export default {
  name: 'App',
  created () {
    this.$store.dispatch('sMember/checkSession')
    console.log(this.$router)
  }
}
</script>
<style>
</style>
