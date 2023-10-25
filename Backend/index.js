const express = require('express');
const cors = require("cors");
const app = express();
const port = '3000';

var corsOptions = {
    origin: "http://localhost:3000"
};

app.use(cors(corsOptions));

app.use(express.json());

app.use(express.urlencoded({extended: true}));

const db = require("./models");

db.sequelize.sync()
  .then(() => {
    console.log("Synced db.");
  })
  .catch((err) => {
    console.log("Failed to sync db: " + err.message);
  });

  app.get("/", (req, res) => {
    res.json({ message: "Welcome to chef.io application." });
  });
  
//   require("./app/routes/")(app);
  
  // set port, listen for requests
  const PORT = process.env.PORT || 3000;
  app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}.`);
  });
