<template>
  <q-page>
    <div class="row justify-center q-pa-lg">
      <form>
        <div class="q-ma-lg text-center">
          {{$t('welcome')}}
        </div>
        <q-input square outlined v-model="member.name" :label="$t('name')" class="q-mb-lg" :error="!!member.errors.name" :error-message="(!!member.errors.name) ? member.errors.name[0] : ''" @blur="member.validate()"></q-input>
        <q-input square outlined v-model="member.mid" :label="$t('mid')" class="q-mb-lg" :error="!!member.errors.mid" :error-message="(!!member.errors.mid) ? member.errors.mid[0] : ''" @blur="member.validate()"></q-input>
        <q-input square outlined v-model="member.password" :label="$t('password')" :type="isPwd ? 'password' : 'text'" class="q-mb-lg" :error="!!member.errors.password" :error-message="(!!member.errors.password) ? member.errors.password[0] : ''" @blur="member.validate()">
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
      me.member.save().then(() => {
        me.$router.push('/login')
      }).catch((e) => {
      })
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
