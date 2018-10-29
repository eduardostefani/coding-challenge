<template>
  <div class="mx-5 d-flex justify-content-center" v-if="totalPages > 1">
    <nav>
      <ul class="pagination">
        <li class="page-item disabled">
          <a class="page-link border-0" href="#" tabindex="-1"><</a>
        </li>
        <template v-for="page in pagesToRender">
            <li class="page-item" :class="{'active-page': page == currentPage}">
              <a class="page-link" href="#" @click="selectPage(page)">{{page}}</a>
            </li>
        </template>
        <li class="page-item">
          <a class="page-link border-0" href="#">></a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
  
  export default {
    name: 'Pagination',
    props: ['currentPage', 'totalPages', 'perPage'],
    data() {
      return {
        searchTag: 'ALOU',
        firstPage: 1
      }
    },
    methods: {
      selectPage (page) {
        this.$root.$emit('changePage', page);
      }
    },
    computed: {
      pagesToRender: function() {
        var pages = [];
        var maxPagesToRender = 10;

        var p = Math.ceil(maxPagesToRender/2)-1;

        if (this.currentPage + p >= this.totalPages) {
          if (maxPagesToRender > this.totalPages) {
            this.firstPage = 1;
          } else {
            this.firstPage = this.totalPages - maxPagesToRender + 1;
          }
        } else if (this.currentPage - p < 1) {
          this.firstPage = 1;
        } else {
          this.firstPage = this.currentPage - p;
        }

        for (var pgx=0; pgx<maxPagesToRender; pgx++) {
          if (pgx > this.totalPages - 1) {
              break;
          }
          pages.push(this.firstPage+pgx);
        }

        return pages;
      }
    }
  }

</script>

<style scoped>

  .page-item a {
    color: #A9A9A9;
  }

  .page-item.active-page a{
    color: #000;
  }
    
</style>
