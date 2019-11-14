<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          @click="drawer = !drawer"
          aria-label="Menu"
        >
          <q-icon name="menu"/>
        </q-btn>
        <q-toolbar-title class="absolute-center cursor-pointer sign-font text-h4" @click="goPageIndex">
          <img class="q-mt-sm q-pt-xs" src="~assets/logo_w.png" height="45px;">
          <!--{{ $t('app.name') }}-->
        </q-toolbar-title>
        <q-btn-dropdown flat size="sm" label="Lang" class="absolute-right q-ma-sm" style="height: 20px">
          <q-list>
            <q-item clickable v-close-popup @click="changeLang('en-us')">
              <q-item-section>
                <q-item-label>English</q-item-label><!-- <v-country-flag country="usa"></v-country-flag> -->
              </q-item-section>
            </q-item>
            <q-item clickable v-close-popup @click="changeLang('ko-kr')">
              <q-item-section>
                <q-item-label>한글</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
      </q-toolbar>
    </q-header>
    <q-drawer
        v-model="drawer"
        :width="200"
        :breakpoint="400"
    >
      <q-scroll-area style="height: calc(100% - 100px); margin-top: 100px; border-right: 1px solid #ddd">
        <q-list padding>
          <q-item active clickable v-ripple @click="goPageFriends">
            <q-item-section avatar>
              <q-icon name="ion-ios-people" />
            </q-item-section>
            <q-item-section>
              {{ $t('friends') }}
            </q-item-section>
          </q-item>
          <q-item active clickable v-ripple @click="goPageGames">
            <q-item-section avatar>
              <q-icon name="ion-logo-game-controller-b"></q-icon>
            </q-item-section>
            <q-item-section>
              {{ $t('game') }}
            </q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>
      <div v-if="!$store.state.sMember.isAuthenticated" class="absolute-top text-center" style="height: 100px; border-right: 1px solid #ddd">
        <q-btn :label="$t('login')" style="margin-top: 30px;" @click="goPageLogin"></q-btn>
      </div>
      <div v-if="$store.state.sMember.isAuthenticated" class="absolute-top" style="height: 100px; border-right: 1px solid #ddd">
        <q-btn-dropdown flat no-caps :label="$store.state.sMember.info.name" class="member-name" style="margin: 20px 5px 5px 5px;">
          <q-list>
            <q-item clickable v-close-popup @click="goPageProfile">
              <q-item-section>
                <q-item-label>{{$t('page.profile')}}</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-close-popup @click="goPageLogout">
              <q-item-section>
                <q-item-label color="negative">{{$t('logout')}}</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
      </div>
    </q-drawer>
    <q-page-container>
      <router-view></router-view>
    </q-page-container>
  </q-layout>
</template>

<script>
import { openURL } from 'quasar'

export default {
  name: 'MyLayout',
  data () {
    return {
      drawer: false
    }
  },
  async created () {
    await this.$store.dispatch('sMember/checkSession')
    if ((this.$route.meta) && (this.$route.meta.authorities) && (this.$route.meta.authorities.length > 0)) {
      if (!this.$store.state.sMember.isAuthenticated) {
        this.$router.push('/login')
      }
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
    this.drawer = false
  },
  methods: {
    openURL,
    goPageProfile () {
      this.$router.push('/profile').catch((e) => {
        if (e.name === 'NavigationDuplicated') {
          this.drawer = false
        }
      })
    },
    goPageFriends () {
      this.$router.push('/friends').catch((e) => {
        if (e.name === 'NavigationDuplicated') {
          this.drawer = false
        }
      })
    },
    goPageGames () {
      this.$router.push('/games/games').catch((e) => {
        if (e.name === 'NavigationDuplicated') {
          this.drawer = false
        }
      })
    },
    goPageIndex () {
      this.$router.push('/').catch((e) => {
        if (e.name === 'NavigationDuplicated') {
          this.drawer = false
        }
      })
    },
    goPageLogin () {
      this.$router.push('/login').catch((e) => {
        if (e.name === 'NavigationDuplicated') {
          this.drawer = false
        }
      })
    },
    goPageLogout () {
      this.$router.push('/logout').catch((e) => {
        if (e.name === 'NavigationDuplicated') {
          this.drawer = false
        }
      })
    },
    changeLang (lang) {
      const me = this
      me.$i18n.locale = lang || me.$q.lang.getLocale()
      if (me.$store.state.sMember.isAuthenticated) {
        me.$axios.post('/api/private/member/lang')
      }
    }
  }
}
</script>

<style>
  .member-name {
    font-weight: 600;
  }
</style>
