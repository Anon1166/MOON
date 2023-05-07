const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
   specPattern: "cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}",
   baseUrl:"http://intproj22.sit.kmutt.ac.th/at2/",
   experimentalSessionAndOrigin: true
  },
});
