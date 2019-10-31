<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-toolbar-title class="absolute-center">
          admin
        </q-toolbar-title>
        <div></div>
      </q-toolbar>
    </q-header>
    <q-page-container>
      <router-view/>
    </q-page-container>
  </q-layout>
</template>

<script>
import { openURL } from 'quasar'

export default {
  name: 'MyLayout',
  data () {
    return {
      leftDrawerOpen: false
    }
  },
  beforeRouteUpdate (to, from, next) {
    if ((to.meta) && (to.meta.authorities) && (to.meta.authorities.length > 0)) {
      if (!this.$store.state.sMember.isAuthenticated) {
        this.$router.push('/login')
      } else if ((to.meta.authorities.includes(this.$store.state.sMember.authority)) || (this.$store.state.sMember.authority === 'ADMIN')) {
        next()
      }
    } else {
      next()
    }
  },
  methods: {
    openURL,
    goPageMyInfo () {
      this.$router.push('/my-info')
    },
    goPageIndex () {
      this.$router.push('/')
    },
    goPageLogin () {
      this.$router.push('/login')
    },
    goPageLogout () {
      this.$router.push('/logout')
    }
  }
}
</script>

<style>
</style>
