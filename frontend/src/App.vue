<template>
  <div id="app" class="mb-5">
    <MainHeader></MainHeader>
    <Search></Search>
  </div>
</template>

<script>

import MainHeader from '@/components/MainHeader'
import Search     from '@/components/Search'

export default {
  name: 'app',
  components: {
    MainHeader,
    Search
  }
}
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
