
<template>
  <div id="books-form">
    <form @submit.prevent="handleSubmit">
      <label>Title</label>
      <input
        v-model="book.title"
        type="text"
        :class="{ 'has-error': submitting && invalidTitle }"
      />
      <div class="inputs__form">
        <div class="inputs__item">
          <label for="authorSelector">Author</label>
          <select id="authorSelector" v-model="book.author.id">
            <option
              :value="author.id"
              v-for="author in authorsSource"
              :key="author.id"
            >
              {{ author.name }} {{ author.surname }}
            </option>
          </select>
        </div>
        <div class="inputs__item">
          <label>Pages</label>
          <input
            v-model="book.pages"
            type="text"
            :class="{ 'has-error': submitting && invalidPages }"
          />
        </div>
      </div>

      <button>Dodaj ksiąkę</button>
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">Dane poprawnie zapisano</p>
    </form>
  </div>
</template>


<script>
export default {
  name: "books-form",
  props: {
    authorsSource: Array,
  },
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      book: {
        title: "",
        author: {
          id: 0,
        },
        pages: 0,
      },
    };
  },
  methods: {
    handleSubmit() {
      this.submitting = true;
      this.clearStatus();

      if (this.invalidTitle) {
        this.error = true;
        return;
      }
      this.$emit("add:book", this.book);
      location.reload();

      this.book = {
        title: "",
        author: 0,
        pages: 0,
      };

      this.error = false;
      this.success = true;
      this.submitting = false;
    },

    clearStatus() {
      this.success = false;
      this.error = false;
    },
  },
  computed: {
    invalidTitle() {
      return this.book.title === "";
    },
    invalidPages() {
      return isNaN(parseInt(this.book.pages));
    },
  },
};
</script>

<style scoped>
.inputs__form {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 0 -7px;
}

.inputs__item {
  flex-grow: 2;
  margin: 0 7px;
}

input,
option,
select {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: medium;
  border: dotted #dfe3e8 1px;
  border-radius: 3px;
  padding: 3px;
  height: 27px;
}

label {
  margin: 0;
}

form {
  margin-bottom: 2em;
}

[class*="-message"] {
  font-weight: 500;
}
.error-message {
  color: #d33c40;
}
.success-message {
  color: #32a95d;
}
</style>