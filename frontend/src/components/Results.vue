<template>
  <div class="container-fluid px-3 pt-3">
    <div class="results-wrapper px-5">
      <div class="list-wrapper border border-bottom-0 mx-5 my-3 bg-white" v-if="viewAs === 1">
        <template v-for="item in items">
          <div class="row my-3">
            <div class="col-md-2">
              <img :src="item.image" width="140" height="100">
            </div>
            <div class="col-md-9 ml-2 text-left w-100">
              <div class="row mb-2">
                <span v-if="item.title">{{item.title}}</span>
                <span class="title-unavailable" v-else>Title unavailable</span>
              </div>
              <div class="row mb-2" v-if="item.description">
                <span class="card-text"v-html="item.description"></span>
              </div>
              <div class="row mb-2" v-if="item.datePosted">
                <span class="card-text">Posted date: {{item.datePosted}}</span>
              </div>
            </div>
          </div>
          <hr class="w-100">
        </template>
      </div>
      <div class="row m-5 tile-wrapper" v-else>
        <template v-for="item in items">
          <div class="col-md-4 col-lg-2 mb-4 px-2">
            <div class="card">
              <img class="card-img-top" :src="item.image" height="120">
              <div class="card-body text-left">
                <span class="card-title" v-if="item.title">{{item.title}}</span>
                <span class="card-title title-unavailable" v-else>Title unavailable</span>
                <span class="card-text">Posted date: {{item.datePosted}}</span>
              </div>
            </div>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Results',
    props: ['items', 'total', 'viewAs']
  }
</script>

<style scoped>
  .tile-wrapper .card-body{
    max-height: 160px;
    min-height: 160px;
  }

  .card-title {
    display: block;
    font-size: 14px;
    color: #000;
  }

  .title-unavailable {
    font-style: italic;
  }

  .card-text {
    font-size: 14px;
    color: #A9A9A9;
    display: block;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    font-size: 14px;
  }
  
</style>