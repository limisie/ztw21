<template>
  <div id="authors-form">
    <form @submit.prevent="handleSubmit">
      <div class="inputs__form">
        <div class="inputs__item">
          <label>Imię</label>
          <input
            v-model="author.name"
            type="text"
            :class="{ 'has-error': submitting && invalidName }"
          />
        </div>
        <div class="inputs__item">
          <label>Nazwisko</label>
          <input
            v-model="author.surname"
            type="text"
            :class="{ 'has-error': submitting && invalidSurname }"
          />
        </div>
      </div>
      <button>Dodaj autora</button>
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">Dane poprawnie zapisano</p>
    </form>
  </div>
</template>


<script>
export default {
  name: "authors-form",
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      author: {
        name: "",
        surname: "",
      },
    };
  },
  methods: {
    handleSubmit() {
      this.submitting = true;
      this.clearStatus();

      if (this.invalidName || this.invalidSurname) {
        this.error = true;
        return;
      }
      this.$emit("add:author", this.author);
      location.reload();

      this.author = {
        name: "",
        surname: "",
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
    invalidName() {
      return this.author.name === "";
    },
    invalidSurname() {
      return this.author.surname === "";
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