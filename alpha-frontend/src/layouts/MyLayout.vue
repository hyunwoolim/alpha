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
        <q-toolbar-title class="absolute-center cursor-pointer" @click="goPageIndex">
          {{ $t('app.name') }}
        </q-toolbar-title>
        <q-btn-dropdown size="sm" label="Lang" class="absolute-right q-ma-sm" style="height: 20px">
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
              <q-icon name="people" />
            </q-item-section>
            <q-item-section>
              {{ $t('friends') }}
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
            <q-item clickable v-close-popup @click="goPageMyInfo">
              <q-item-section>
                <q-item-label>{{$t('page.myinfo')}}</q-item-label>
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

    <!--<q-drawer
      v-model="leftDrawerOpen"
      bordered
      content-class="bg-grey-2"
    >
      <q-list>
        <q-item-label header>
          <div class="row">
            <div v-show="!$store.state.sMember.isAuthenticated" class="cursor-pointer" @click="goPageLogin">
              {{$t('please.login')}}
            </div>
            <div v-show="$store.state.sMember.isAuthenticated" style="margin: -10px 0px 0px -10px;">
              <q-btn-dropdown flat no-caps :label="$store.state.sMember.info.name" class="member-name">
                <q-list>
                  <q-item clickable v-close-popup @click="goPageMyInfo">
                    <q-item-section>
                      <q-item-label>{{$t('page.myinfo')}}</q-item-label>
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
            <q-icon class="absolute-right cursor-pointer" size="lg" style="margin: 6px;" name="chevron_left" @click="leftDrawerOpen = !leftDrawerOpen"></q-icon>
          </div>
        </q-item-label>
        <q-item clickable tag="a" target="_blank" @click="goPageFriends">
          <q-item-section avatar>
            <q-icon name="person"/>
          </q-item-section>
          <q-item-section>
            <q-item-label>{{$t('friends')}}</q-item-label>
          </q-item-section>
        </q-item>
        <q-item clickable tag="a" target="_blank" @click="goPageGame1">
          <q-item-section avatar>
            <q-icon name="game"/>
          </q-item-section>
          <q-item-section>
            <q-item-label>{{$t('game1')}}</q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>-->
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
    goPageMyInfo () {
      this.$router.push('/my-info').catch((e) => {
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
    goPageGame1 () {
      this.$router.push('/games/game1/1').catch((e) => {
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
