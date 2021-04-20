<template>
  <div id="authors">
    <h1>Autorzy</h1>
    <authors-form @add:author="addAuthor" />
    <authors-table :authorsSource="authorsSource" @delete:author="deleteAuthor"/>
  </div>
</template>

<script>
import AuthorsTable from "./AuthorsTable.vue";
import AuthorsForm from "./AuthorsForm.vue";
import axios from "axios";

export default {
  name: "Authors",
  components: {
    AuthorsTable,
    AuthorsForm,
  },
  props: {
    authorsSource: Array,
  },
  methods: {
    addAuthor(author) {
      axios
        .post(`http://localhost:8080/author`, author)
        .catch((e) => {
          this.errors.push(e);
        });
    },
    updateAuthor(id, author) {
      axios
        .put(`http://localhost:8080/author/${id}`, author)
        .catch((e) => {
          this.errors.push(e);
        });
    },
    deleteAuthor(id) {
      axios
        .delete(`http://localhost:8080/author/${id}`)
        .catch((e) => {
          this.errors.push(e);
        });
    },
  },
};
</script>

<style scoped>
</style>
