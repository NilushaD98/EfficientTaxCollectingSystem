const express = require('express');
const app = express();
const port = 3000; // Port on which your Express.js application is running

// Import the Eureka client library
const Eureka = require('eureka-js-client').Eureka;

// Eureka client configuration
const client = new Eureka({
  instance: {
    app: 'my-express-app', // Your application name
    hostName: 'localhost',
    ipAddr: '127.0.0.1',
    port: {
      $: port,
      '@enabled': true,
    },
    vipAddress: 'my-express-app',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
  },
  eureka: {
    host: 'localhost', // Eureka server hostname or IP address
    port: 8761, // Default Eureka server port
    servicePath: '/eureka/apps/', // Default registration endpoint
  },
});

// Start the Eureka client
client.start(error => {
  if (error) {
    console.error('Eureka registration FAILED:', error);
  } else {
    console.log('Eureka registration successful');
  }
});

// Your Express.js routes and middleware
app.get('/express/hello', (req, res) => {
    console.log("hellow from api gateway")
  res.send('Hello from Express.js!');

});

// Start the Express.js server
app.listen(port, () => {
  console.log(`Express.js server is running on port ${port}`);
});
