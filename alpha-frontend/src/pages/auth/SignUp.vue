<template>
  <q-page>
    <div class="row justify-center q-pa-lg">
      <form>
        {{$t('welcome')}}
        <q-input square outlined v-model="member.email" :label="$t('email')" class="q-mb-lg" type="email" :error="!!member.errors.email" :error-message="(!!member.errors.email) ? member.errors.email[0] : ''"></q-input>
        <q-input square outlined v-model="member.name" :label="$t('name')" class="q-mb-lg" :error="!!member.errors.name" :error-message="(!!member.errors.name) ? member.errors.name[0] : ''"></q-input>
        <q-input square outlined v-model="member.password" :label="$t('password')" :type="isPwd ? 'password' : 'text'" class="q-mb-lg" :error="!!member.errors.password" :error-message="(!!member.errors.password) ? member.errors.password[0] : ''">
          <template v-slot:append>
            <q-icon
              :name="isPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isPwd = !isPwd"
            />
          </template>
        </q-input>
        <div>
          <q-btn  @click="signUp" label="가입하기"></q-btn>
          <span class="text-right cursor-pointer" @click="goPageLogin">
            {{$t('account.already')}}
          </span>
        </div>
      </form>
    </div>
  </q-page>
</template>
<script>
import { Member } from '../../model/Member'

export default {
  name: 'PageSignUp',
  data () {
    return {
      member: new Member(),
      isPwd: true
    }
  },
  created () {
  },
  mounted () {
  },
  methods: {
    signUp () {
      const me = this
      me.member.save()
    },
    goPageLogin () {
      const me = this
      me.$router.push('/login')
    }
  }
}
</script>
<style lang="stylus">
</style>
