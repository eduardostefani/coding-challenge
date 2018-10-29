<template>
  <div id="overlay">
    <img src="static/img/loader.gif" alt="Loading" /><br/>
  </div>
</template>


<script>
  export default {
    name: 'Loading'
  }

</script>

<style scoped>
  #overlay {
    background-color: rgba(255,255,255,0.5);
    color: #666666;
    position: fixed;
    height: 100%;
    width: 100%;
    z-index: 5000;
    top: 0;
    left: 0;
    float: left;
    text-align: center;
    padding-top: 25%;
  }
</style>
