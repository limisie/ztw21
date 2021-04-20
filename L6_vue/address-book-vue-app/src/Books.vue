<template>
  <div id="books">
    <div class="app-tile">
      <h1>Ksiązki</h1>

      <books-form :authorsSource="authors" @add:book="addBook" />
      <books-table :booksSource="books" @delete:book="deleteBook" />
    </div>
  </div>
</template>

<script>
import BooksTable from "./components/BooksTable.vue";
import BooksForm from "./components/BooksForm.vue";
import axios from "axios";

export default {
  name: "Books",
  components: {
    BooksTable,
    BooksForm,
  },
  data() {
    return {
      books: [],
      authors: [],
    };
  },
  created() {
    axios
      .get(`http://localhost:8080/book`)
      .then((response) => {
        this.books = response.data;
      })
      .catch((e) => {
        this.errors.push(e);
      });
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
    addBook(book) {
      axios.post(`http://localhost:8080/book`, book).catch((e) => {
        this.errors.push(e);
      });
    },
    updateBook(id, book) {
      axios.put(`http://localhost:8080/book/${id}`, book).catch((e) => {
        this.errors.push(e);
      });
    },
    deleteBook(id) {
      axios.delete(`http://localhost:8080/book/${id}`).catch((e) => {
        this.errors.push(e);
      });
    },
  },
};
</script>

<style scoped>
</style>
