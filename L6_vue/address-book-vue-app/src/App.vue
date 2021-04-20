<template>
  <div id="app">
    <div class="app-tile">
      <div class="app-tile__books">
        <books :booksSource="books" :authorsSource="authors" />
      </div>
    </div>
    <div class="app-tile">
      <div class="app-tile__authors">
        <authors :authorsSource="authors" />
      </div>
    </div>

    <div class="app-tile">
      <div class="app-tile__persons">
        <persons />
      </div>
    </div>
  </div>
</template>

<script>
import Persons from "@/components/Persons";
import Books from "@/components/Books";
import Authors from "@/components/Authors";
import axios from "axios";

export default {
  name: "App",
  components: {
    Persons,
    Books,
    Authors,
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
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
}
.app-tile {
  display: block;
  margin: auto;
  margin-bottom: 30px;
  padding: 30px;
  width: 60%;
  border-radius: 10px;
  background-color: white;
  box-shadow: #eeeeee 0 0 10px;
}
h1 {
  margin: 0 0 20px 0;
}
button {
  padding: 5px;
  margin: 0;
  background-color: #2c3e50;
  border-radius: 3px;
  border: none;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-weight: 400;
  color: white;
  font-size: medium;
}
button:hover {
  background-color: #3b5269;
  border: none;
}

button:focus {
  background-color: #3b5269;
  border: none;
}
</style>
