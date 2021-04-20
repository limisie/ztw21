<template>
  <div id="authors-table">
    <table>
      <thead>
        <tr>
          <th>Imię</th>
          <th>Nazwisko</th>
          <th></th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="author in authorsSource" :key="author.id">
          <td>{{ author.name }}</td>
          <td>{{ author.surname }}</td>
          <td><button>Edit</button></td>
          <td><button @click="handleClick(author.id)">Delete</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>


<script>

export default {
  name: "authors-table",
  props: {
      authorsSource: Array,
  },
  methods: {
    handleClick(id) {
      this.$emit("delete:author", id);
      location.reload();
    },
  },
};

</script>

<style scoped></style>