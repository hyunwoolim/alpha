<template>
  <q-page>
    <div class="row justify-center q-pa-lg">
      <form>
        {{$t('cancel')}}
        <q-input square outlined v-model="member.email" label="이메일" class="q-mb-lg" type="email" :error="!!member.errors.email" :error-message="(!!member.errors.email) ? member.errors.email[0] : ''"></q-input>
        <q-input square outlined v-model="member.name" label="이름" class="q-mb-lg"></q-input>
        <q-input square outlined v-model="member.password" label="비밀번호" :type="isPwd ? 'password' : 'text'" class="q-mb-lg">
          <template v-slot:append>
            <q-icon
              :name="isPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isPwd = !isPwd"
            />
          </template>
        </q-input>
        <q-btn  @click="signUp" label="가입하기"></q-btn>
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
      isPwd: false
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
    }
  }
}
</script>
<style lang="stylus">
</style>
