<template>
  <div id="app">
    <div id="nav" class="app-tile nav">
      <router-link to="/" class="nav__link">Home</router-link>
      <router-link to="/books" class="nav__link">Ksiazki</router-link>
      <router-link to="/authors" class="nav__link">Autorzy</router-link>
      <router-link to="/friends" class="nav__link">Znajomi</router-link>
    </div>
    <router-view></router-view>
    <!-- <div class="app-tile">
      <div class="app-tile__books">
        <books />
      </div>
    </div>
    <div class="app-tile">
      <div class="app-tile__authors">
        <authors />
      </div>
    </div>

    <div class="app-tile">
      <div class="app-tile__persons">
        <persons />
      </div>
    </div> -->
  </div>
</template>

<script>
// import Persons from "@/Persons";
// import Books from "@/Books";
// import Authors from "@/Authors";

export default {
  name: "App",
  // components: {
  //   Persons,
  //   Books,
  //   Authors,
  // },
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
.nav {
  display: flex;
  justify-content: flex-start;
}
.nav__link {
  padding: 0 20px;
  font-weight: bold;
  text-decoration: none;
  color: #2c3e50;
}

.nav__link:hover {
  text-decoration: none;
  color: #3b5269;
}

.nav__link:focus {
  text-decoration: none;
  color: #3b5269;
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
