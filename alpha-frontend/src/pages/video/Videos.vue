<template>
  <q-page>
    <vue-plyr ref="tt">
      <video poster="/api/video/download/20191206_040810.png" src="/api/video/download/20191206_032714460_276cd123-a7e9-4467-b05e-6eac810b855c.mp4"></video>
    </vue-plyr>
    <q-btn @click="tt" label="tt"></q-btn>
  </q-page>
</template>
<style>
</style>
<script>
export default {
  name: 'PageVideos',
  data () {
    return {
    }
  },
  created () {
  },
  mounted () {
  },
  methods: {
    tt () {
      console.log(this.$refs.tt.player)
    }
  }
}
</script>
<style lang="stylus">
</style>
