<template>
  <q-page>
    {{$t('logout.processing')}}
  </q-page>
</template>
<script>

export default {
  name: 'PageLogout',
  data () {
    return {
    }
  },
  created () {
    this.$axios({
      url: '/api/logout',
      method: 'get'
    }).then((res) => {
      console.log(res.data)
      this.$router.push(res.data)
    })
  }
}
</script>
