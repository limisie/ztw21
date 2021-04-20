<template>
  <div id="authors">
    <div class="app-tile">
      <h1>Autorzy</h1>
      <authors-form @add:author="addAuthor" />
      <authors-table :authorsSource="authors" @delete:author="deleteAuthor" />
    </div>
  </div>
</template>

<script>
import AuthorsTable from "./components/AuthorsTable.vue";
import AuthorsForm from "./components/AuthorsForm.vue";
import axios from "axios";

export default {
  name: "Authors",
  components: {
    AuthorsTable,
    AuthorsForm,
  },
  data() {
    return {
      authors: [],
    };
  },
  created() {
    axios
      .get(`http://localhost:8080/author`)
      .then((response) => {
        this.authors = response.data;
      })
      .catch((e) => {
        this.errors.push(e);
      });
  },
  methods: {
    addAuthor(author) {
      axios.post(`http://localhost:8080/author`, author).catch((e) => {
        this.errors.push(e);
      });
    },
    updateAuthor(id, author) {
      axios.put(`http://localhost:8080/author/${id}`, author).catch((e) => {
        this.errors.push(e);
      });
    },
    deleteAuthor(id) {
      axios.delete(`http://localhost:8080/author/${id}`).catch((e) => {
        this.errors.push(e);
      });
    },
  },
};
</script>

<style scoped>
</style>
