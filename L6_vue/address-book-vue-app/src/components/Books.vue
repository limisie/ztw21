<template>
  <div id="books">
    <h1>Ksiązki</h1>

    <books-form :authorsSource="authorsSource" @add:book="addBook" />
    <books-table :booksSource="booksSource" @delete:book="deleteBook"/>
  </div>
</template>

<script>
import BooksTable from "./BooksTable.vue";
import BooksForm from "./BooksForm.vue";
import axios from "axios";

export default {
  name: "Books",
  components: {
    BooksTable,
    BooksForm,
  },
  props: {
      booksSource: Array,
      authorsSource: Array,
  },
  methods: {
    addBook(book) {
      axios
        .post(`http://localhost:8080/book`, book)
        .catch((e) => {
          this.errors.push(e);
        });
    },
    updateBook(id, book) {
      axios
        .put(`http://localhost:8080/book/${id}`, book)
        .catch((e) => {
          this.errors.push(e);
        });
    },
    deleteBook(id) {
      axios
        .delete(`http://localhost:8080/book/${id}`)
        .catch((e) => {
          this.errors.push(e);
        });
    },
  },
};
</script>

<style scoped>
</style>
