<template>
  <div id="books-table">
    <table>
      <thead>
        <tr>
          <th>Tytuł</th>
          <th>Autor</th>
          <th>Liczba stron</th>
          <th></th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in booksSource" :key="book.id">
          <td>{{ book.title }}</td>
          <td>{{ book.author.name }} {{ book.author.surname }}</td>
          <td>{{ book.pages }}</td>
          <td><button>Edit</button></td>
          <td><button @click="handleClick(book.id)">Delete</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>


<script>
export default {
  name: "books-table",
  props: {
    booksSource: Array,
  },
  methods: {
    handleClick(id) {
      this.$emit("delete:book", id);
      location.reload();
    },
  },
};
</script>

<style scoped>

</style>