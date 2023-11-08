const db = require("../models");
const User = db.user;
const Address = db.address;
const Config = require("../config/auth.config");
const jwt = require('jsonwebtoken');
exports.allAccess = (req, res) => {
    res.status(200).send("Public Content.");
};

exports.userBoard = (req, res) => {
    res.status(200).send("User Content.");
};

exports.adminBoard = (req, res) => {
    res.status(200).send("Admin Content.");
};

exports.chefBoard = (req, res) => {
    res.status(200).send("Chef Content.");
};
exports.address = (req, res) => {
    let token = req.headers["x-access-token"];

    let s = Config.secret;
    let userID;
    try{
        const decode = jwt.verify(token, s);

        userID = decode.userId;
    }catch (err){
        console.error('JWT ')
    }
   const Address.create({
        street1: req.body.street1,
        street2: req.body.street2,
        city: req.body.city,
        province: req.body.province,
        postalcode: req.body.postalcode,
        country: req.body.country,
        userid: userID
    }).then(
        res.status(200)
    )
    User.findByPk(userID).then(user => {
        user.update({
            gender: req.body.gender,
            mobilenumber: req.body.mobilenumber,
            profilepicture: req.body.profilepicture
        })
    })
};

