const express = require('express');
const cors = require("cors");
const app = express();
const bodyParser = require('body-parser');
const port = '3000';

var corsOptions = {
    origin: "http://localhost:3000"
};
app.use(bodyParser.json({limit: '200mb'}));
app.use(bodyParser.urlencoded({limit: '200mb', extended: true}));
app.use(cors(corsOptions));
app.use("/uploads", express.static('./middleware/assets/uploads'));
app.use(express.json());
app.use((req, res, next) => {
    console.log(`[${new Date().toISOString()}] ${req.method} ${req.url}`);
    console.log('Request Headers:', req.headers);
    console.log('Request Body:', req.body);
    console.log('Query Parameters:', req.query);
    console.log('Request IP Address:', req.ip);
    console.log('---------------------------------------');

    next();
});

app.use(express.urlencoded({extended: true}));

const db = require("./models");
const Role = db.role;

db.sequelize.sync({force: true, logging: true})
    .then(() => {
        console.log("Synced db.");
        initial();
    })
    .catch((err) => {
        console.log("Failed to sync db: " + err.message);
    });

function initial() {
    Role.create({
        id: 1,
        name: "user"
    });

    Role.create({
        id: 2,
        name: "chef"
    });

    Role.create({
        id: 3,
        name: "admin"
    });
}

app.get("/", (req, res) => {
    res.json({message: "Welcome to chef.io application."});
});

// require('./routes')(app);
require('./routes/auth.routes')(app);
require('./routes/user.routes')(app);
require('./routes/chef.routes')(app);
require('./routes/post.routes')(app);
require('./routes/booking.routes')(app);

// set port, listen for requests
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}.`);
});
