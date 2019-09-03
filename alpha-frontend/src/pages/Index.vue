<template>
  <q-page>
  </q-page>
</template>
<style>
</style>
<script>
import { Sortable } from '@shopify/draggable'
export default {
  name: 'PageIndex',
  mounted () {
    const sortable = new Sortable(document.getElementById('test'), {
      draggable: 'div'
    })
    sortable.on('sortable:start', () => console.log('sortable:start'))
    sortable.on('sortable:sort', () => console.log('sortable:sort'))
    sortable.on('sortable:sorted', () => console.log('sortable:sorted'))
    sortable.on('sortable:stop', () => console.log('sortable:stop'))
  }
}
</script>
<style lang="stylus">
  .item
    height: 100%
  .dropzone
    outline: solid 1px
    height: 50px
  .draggable-dropzone--occupied
    background: lightgreen
</style>
