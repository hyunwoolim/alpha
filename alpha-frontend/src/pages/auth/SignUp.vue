<template>
  <q-page>
    <q-input square outlined v-model="member.email" label="이메일"></q-input>
    <q-input square outlined v-model="member.name" label="이름"></q-input>
    <q-input square outlined v-model="member.password" label="비밀번호" :type="isPwd ? 'password' : 'text'">
      <template v-slot:append>
        <q-icon
          :name="isPwd ? 'visibility_off' : 'visibility'"
          class="cursor-pointer"
          @click="isPwd = !isPwd"
        />
      </template>
    </q-input>
    <q-btn @click="signUp"></q-btn>
  </q-page>
</template>
<script lang="ts">
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
