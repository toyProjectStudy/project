module.exports = {
  devServer: {
    overlay: false,
  },
  css: {
    loaderOptions: {
      sass: {
        additionalData: `
          @import "@/style/_variables.scss";
        `,
      },
    },
  },
};
