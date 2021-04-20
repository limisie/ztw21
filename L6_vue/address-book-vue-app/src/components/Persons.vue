<template>
  <div id="persons">
    <h1>Znajomi</h1>

    <person-form @add:person="addPerson" />
    <persons-table :personsSource="persons" />
  </div>
</template>

<script>
import PersonsTable from "./PersonsTable.vue";
import PersonForm from "./PersonForm.vue";

export default {
  name: "Persons",
  components: {
    PersonsTable,
    PersonForm,
  },
  data() {
    return {
      persons: [],
    };
  },
  methods: {
    addPerson(person) {
      this.persons = [...this.persons, person];
    },
    async getPersons() {
      try {
        const response = await fetch(
          "https://jsonplaceholder.typicode.com/users"
        );
        const data = await response.json();
        this.persons = data;
      } catch (error) {
        console.error(error);
      }
    },
  },
  mounted() {
    this.getPersons();
  },
};
</script>

<style scoped>
</style>
