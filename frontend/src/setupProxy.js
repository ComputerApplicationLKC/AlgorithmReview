const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    createProxyMiddleware({
      target: 'http://api-gateway:8000/',
      changeOrigine: true
    }),
  );
};