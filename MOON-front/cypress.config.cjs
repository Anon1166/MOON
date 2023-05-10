const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
   specPattern: "cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}",
<<<<<<< HEAD
   baseUrl:"http://intproj22.sit.kmutt.ac.th/at2",
=======
   baseUrl:"http://intproj22.sit.kmutt.ac.th/at2/",
>>>>>>> 97831c16d6158a51d2c1eb39b9268c5b76b4637d
   experimentalSessionAndOrigin: true
  },
});
