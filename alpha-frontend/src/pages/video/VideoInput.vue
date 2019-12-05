<template>
  <q-page>
    <q-input outlined v-model="model.title" :label="$t('title')"></q-input>
    <q-uploader
      field-name="file"
      url="/api/upload"
      style="max-width: 300px"
    />
  </q-page>
</template>
<style>
</style>
<script>
export default {
  name: 'PageVideos',
  data () {
    return {
      model: {
        title: ''
      }
    }
  },
  created () {
  },
  mounted () {
  },
  methods: {
  }
}
</script>
<style lang="stylus">
</style>
