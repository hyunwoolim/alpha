<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          aria-label="Menu"
        >
          <q-icon name="menu"/>
        </q-btn>
        <q-toolbar-title class="absolute-center cursor-pointer" @click="goPageIndex">
          {{ $t('app.name') }}
        </q-toolbar-title>
        <q-btn-dropdown label="Lang" class="absolute-right">
          <q-list>
            <q-item clickable v-close-popup @click="changeLang('en-us')">
              <q-item-section>
                <q-item-label><v-country-flag country="usa"></v-country-flag></q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-close-popup @click="changeLang('ko-kr')">
              <q-item-section>
                <q-item-label><v-country-flag country="kor"></v-country-flag></q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
      </q-toolbar>
    </q-header>

    <q-drawer
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
            <!--<div v-show="$store.state.sMember.isAuthenticated" class="cursor-pointer float-right" @click="goPageLogout">
              {{$t('logout')}}
            </div>-->
            <q-icon class="absolute-right cursor-pointer" size="lg" style="margin: 6px;" name="chevron_left" @click="leftDrawerOpen = !leftDrawerOpen"></q-icon>
          </div>
        </q-item-label>
        <!--<q-item clickable tag="a" target="_blank" @click="goPageMyInfo">
          <q-item-section avatar>
            <q-icon name="person"/>
          </q-item-section>
          <q-item-section>
            <q-item-label>내정보</q-item-label>
            <q-item-label caption>My Info</q-item-label>
          </q-item-section>
        </q-item>-->
        <!--<q-item clickable tag="a" target="_blank" href="https://quasar.dev">
          <q-item-section avatar>
            <q-icon name="school"/>
          </q-item-section>
          <q-item-section>
            <q-item-label>Docs</q-item-label>
            <q-item-label caption>quasar.dev</q-item-label>
          </q-item-section>
        </q-item>
        <q-item clickable tag="a" target="_blank" href="https://github.quasar.dev">
          <q-item-section avatar>
            <q-icon name="code"/>
          </q-item-section>
          <q-item-section>
            <q-item-label>Github</q-item-label>
            <q-item-label caption>github.com/quasarframework</q-item-label>
          </q-item-section>
        </q-item>
        <q-item clickable tag="a" target="_blank" href="https://chat.quasar.dev">
          <q-item-section avatar>
            <q-icon name="chat"/>
          </q-item-section>
          <q-item-section>
            <q-item-label>Discord Chat Channel</q-item-label>
            <q-item-label caption>chat.quasar.dev</q-item-label>
          </q-item-section>
        </q-item>
        <q-item clickable tag="a" target="_blank" href="https://forum.quasar.dev">
          <q-item-section avatar>
            <q-icon name="record_voice_over"/>
          </q-item-section>
          <q-item-section>
            <q-item-label>Forum</q-item-label>
            <q-item-label caption>forum.quasar.dev</q-item-label>
          </q-item-section>
        </q-item>
        <q-item clickable tag="a" target="_blank" href="https://twitter.quasar.dev">
          <q-item-section avatar>
            <q-icon name="rss_feed"/>
          </q-item-section>
          <q-item-section>
            <q-item-label>Twitter</q-item-label>
            <q-item-label caption>@quasarframework</q-item-label>
          </q-item-section>
        </q-item>
        <q-item clickable tag="a" target="_blank" href="https://facebook.quasar.dev">
          <q-item-section avatar>
            <q-icon name="public"/>
          </q-item-section>
          <q-item-section>
            <q-item-label>Facebook</q-item-label>
            <q-item-label caption>@QuasarFramework</q-item-label>
          </q-item-section>
        </q-item>-->
      </q-list>
    </q-drawer>
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
  created () {
  },
  methods: {
    openURL,
    goPageMyInfo () {
      this.$router.push('/my-info').catch((e) => {
        if (e.name === 'NavigationDuplicated') {
          this.leftDrawerOpen = false
        }
      })
    },
    goPageIndex () {
      this.$router.push('/')
    },
    goPageLogin () {
      this.$router.push('/login')
    },
    goPageLogout () {
      this.$router.push('/logout')
    },
    changeLang (lang) {
      this.$i18n.locale = lang || this.$q.lang.getLocale()
    }
  }
}
</script>

<style>
  .member-name {
    font-weight: 600;
  }
</style>
