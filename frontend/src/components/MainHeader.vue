<template>
  <nav class="mb-1 navbar navbar-expand-lg py-0 border-bottom" :class="[active ? 'navbar-light bg-white' : 'navbar-dark']">
    <a href="#" class="navbar-brand" v-if="!active">FLICKR CACHE</a>
    <div class="navbar-collapse">
      <div class="h-100 w-100 ml-5" v-if="active">
        <input type="text" class="form-control w-100 h-100 mx-auto border-0 px-0" placeholder="Search for anything ..." v-model="searchTag" @keyup.enter="search()">
      </div>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item " v-if="active">
          <a class="search-btn nav-link m-2 mr-3" :class="{'text-white': !active}" @click="search">
            <font-awesome-icon icon="search" />
          </a>
        </li>
        <li class="nav-item border-left" v-else>
          <a class="search-btn nav-link m-2" :class="{'text-white': !active}" @click="toggleSearch">
            <font-awesome-icon icon="search" /><span class="ml-2">Search</span>
          </a>
        </li>
        <li class="nav-item border-left nofitication">
          <a class="nav-link m-2" :class="{'text-white': !active}">
            <font-awesome-icon icon="bell" /><span class="badge badge-notify text-white rounded-0">3</span>
          </a>
        </li>
        <li class="nav-item avatar">
          <img src="static/img/avatar.png" class="my-2 mx-2 rounded-circle z-depth-0" alt="avatar image">
        </li>
      </ul>
    </div>
  </nav>
 
</template>

<script>
  export default {
    name: 'MainHeader',
    data() {
      return {
        active: false,
        searchTag: ''
      }
    },
    methods: {
      search() {
        if(this.searchTag != '') {
          this.$root.$emit('doSearch', this.searchTag);
          this.searchTag = '';

          this.$nextTick(() => {
            this.toggleSearch();
          });  
        }
      },

      toggleSearch() { 
        this.active = !this.active;
      }
    }
  }

</script>

<style scoped>
  .navbar {
    height: 50px;
    background-color: #000;
    color: #FFF;
  }
  .navbar-nav {
    height: 50px;
  }
  .nofitication {
    min-width: 70px; 
  }
  .badge-notify{
    background: #0f83ff;
    position:relative;
    top: -10px;
    left: -5px;
  }
  .form-control:focus,.form-control:active {
     outline: none !important;
     box-shadow: none;
  }
  .avatar img {
    width: 36px;
    height: 36px;
    border-radius:100%;
  }
  .search-btn {
    cursor: pointer;
  }
</style>