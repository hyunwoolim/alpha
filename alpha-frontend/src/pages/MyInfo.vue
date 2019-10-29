<template>
  <q-page class="q-pa-md q-gutter-md">
    <q-avatar>
      <img src="https://cdn.quasar.dev/img/avatar.png">
    </q-avatar>
    <!--<q-card class="my-card">
      <q-parallax
        src="https://cdn.quasar.dev/img/parallax1.jpg"
        :height="150"
      />

      <q-card-section>
        <div class="text-h6">Our Changing Planet</div>
        <div class="text-subtitle2">by John Doe</div>
      </q-card-section>
    </q-card>-->
  </q-page>
</template>
<style>
</style>
<script>
export default {
  name: 'PageMyInfo',
  created () {
    console.log(this.$store.state.sMember)
  }
}
</script>
<style lang="stylus">
</style>
