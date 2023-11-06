const express = require('express');
const axios = require('axios');

const app = express();
const port = 3000;

app.get('/hello', (req, res) => {
  console.log('Received a request for "hello world"');
  res.send('Hello World');
});

app.listen(port, () => {
  console.log(`Express server is running on port ${port}`);
});
