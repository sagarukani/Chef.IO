const db = require("../models");
const User = db.user;
const Address = db.address;
const Config = require("../config/auth.config");
const jwt = require('jsonwebtoken');
const uploadFile = require("../middleware/upload");
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
        // console.log(decode);
        userID = decode.id;
    }catch (err){
        console.error('JWT')
    }
    try {
        uploadFile(req, res);

        if (req.file === undefined) {
            return res.status(400).send({ message: "Please upload a file!" });
        }

        res.status(200).send({
            message: "Uploaded the file successfully: " + req.file.originalname,
        });
    } catch (err) {
        res.status(500).send({
            message: `Could not upload the file: ${req.file.originalname}. ${err}`,
        });
    }
   Address.create({
        street1: req.body.street1,
        street2: req.body.street2,
        city: req.body.city,
        province: req.body.province,
        postalcode: req.body.postalcode,
        country: req.body.country,
        userid: userID
    }).then( () =>{
    let address = Address.findOne({
        where: {userid: userID}
    })
    User.update({
            firstname: req.body.firstname,
            lastname: req.body.lastname,
            gender: req.body.gender,
            mobilenumber: req.body.mobilenumber,
            profilepicture: req.file.originalname,
            addressid: address.id},
        {
            where: {id: userID}
        }).then(() =>{
            
        res.send({message: "User data updated"});
    });
   });
};

