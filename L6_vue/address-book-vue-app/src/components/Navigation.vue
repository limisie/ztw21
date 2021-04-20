<template>
  <div id="navigation">

  </div>
</template>

<script>

export default {
  name: "App",
  components: {
  },
  data() {
    return {
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

<style></style>
