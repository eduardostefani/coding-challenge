<template>
  <div class="search-wrapper">
    <div v-if="error" class="m-5 text-danger">
      <span>{{error}}</span>
    </div>
    <div v-else>
      <Loading v-if="isLoading"></Loading>
      <div class="m-5" v-if="!response">
        Start searching by clicking in the Search button on the top right
      </div>
      <div class="m-5" v-else-if="response && response.totalResults === 0">
        The tag you search returned no results, please try another one
      </div>
      <div v-else>
        <div class="results-wrapper px-5 text-left mt-4">
          <div class="results-header px-3">
            <span class="ml-5" v-if="tag">"{{tag}}"</span>
            <div class="row border mx-5 my-2">
              <div class="col-lg-6 text-left pt-3" >
                <span class="pl-4">{{viewing}} out of {{response.totalResults}}</span>  
              </div>
              <div class="col-lg-2">
                <a class="toggle-view float-right nav-link m-2" @click="toggleView" title="toogle list - tile results">
                  <font-awesome-icon icon="list" class="toggle-view" />
                </a>
              </div>
              <div class="col-lg-4 border-left text-left pt-3">
                <span class="pt-3">SORT BY</span>
                <select name="" id="" class="ml-5 border-0" v-model="sortBy">
                  <option value="0">Date posted - Newest to Oldest</option>
                  <option value="1">Date posted - Oldest to Newest</option>
                  <option value="6">Relevance</option>
                </select>
              </div>
            </div>
          </div>
        </div>
        <Results :items="response.items" :total="response.totalResults" :viewAs="viewAs"></Results>
        <Pagination :totalPages="response.totalPages" :currentPage="currentPage" :perPage="12"></Pagination>
      </div>
    </div>
    
  </div>
</template>

<script>
  import {AXIOS} from './http-common'
  
  import Loading    from '@/components/Loading'
  import Results    from '@/components/Results'
  import Pagination from '@/components/Pagination'

  export default {
    name: 'Search',
    components: {
      Loading,
      Results,
      Pagination
    },

    data () {
      return {
        isLoading: false,
        response: null,
        error: null,
        tag: '',
        perPage: 12,
        currentPage: 1,
        sortBy: 0,
        viewAs: 1 // 1 -list, 2 tile
      }
    },

    watch: {
      sortBy: function () {
        this.page = 1;
        this.search(this.tag);
      }
    },

    mounted() {
      this.$root.$on('doSearch', this.search);
      this.$root.$on('changePage', this.changePage);
    },

    methods: {
      changePage (page) {
        this.currentPage = page;
        this.search(this.tag);
      },
      
      // fetch items
      search (tag) {
        this.error = null;
        this.isLoading = true;
        this.tag = tag;

        var params = new URLSearchParams();
        params.append('search', this.tag);
        params.append('page', this.currentPage);
        params.append('sort', this.sortBy);

        AXIOS.get('/search', { params })
          .then(response => {
            this.response = response.data
          })
          .catch(e => {
            this.error = e.response.data;
          })
          .then(() => {
            this.isLoading = false;
          })
      },

      toggleView () {
        this.viewAs = this.viewAs === 1 ? 2 : 1;
      }
    },
    
    computed: {
      viewing: function() {
        return this.response.items.length;
      }
    }
  }

</script>

<style scoped>
  .toggle-view:hover  {
    cursor: pointer;
  }
</style>
