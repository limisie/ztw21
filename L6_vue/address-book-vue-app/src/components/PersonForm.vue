<template>
  <div id="person-form">
    <form @submit.prevent="handleSubmit">
      <label>Imię i nazwisko</label>
      <input
        v-model="person.name"
        type="text"
        :class="{ 'has-error': submitting && invalidName }"
        @focus="clearStatus"
        @keypress="clearStatus"
      />
      <div class="inputs__form">
        <div class="inputs__item">
          <label>Email</label>
          <input
            v-model="person.email"
            type="text"
            :class="{ 'has-error': submitting && invalidEmail }"
            @focus="clearStatus"
          />
        </div>
        <div class="inputs__item">
          <label>Telefon</label>
          <input
            v-model="person.phone"
            type="text"
            :class="{ 'has-error': submitting && invalidPhone }"
            @focus="clearStatus"
            @keypress="clearStatus"
          />
        </div>
      </div>

      <button>Dodaj kontakt</button>
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">Dane poprawnie zapisano</p>
    </form>
  </div>
</template>
<script>
export default {
  name: "person-form",
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      person: {
        name: "",
        email: "",
        phone: "",
      },
    };
  },
  methods: {
    handleSubmit() {
      this.submitting = true;
      this.clearStatus();

      if (this.invalidName || this.invalidEmail || this.invalidPhone) {
        this.error = true;
        return;
      }
      this.$emit("add:person", this.person);

      this.person = {
        name: "",
        email: "",
        phone: "",
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
      return this.person.name === "";
    },
    invalidEmail() {
      return this.person.email === "";
    },
    invalidPhone() {
      return this.person.phone === "";
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