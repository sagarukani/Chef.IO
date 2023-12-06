const db = require("../models");
const User = db.user;
const Address = db.address;
const Card = db.card;
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
exports.getUserProfile = async (req, res) => {
    let token = req.headers["x-access-token"];

    let s = Config.secret;
    let userID;
    try {
        const decode = jwt.verify(token, s);
        // console.log(decode);
        userID = decode.id;
    } catch (err) {
        console.error('JWT')
    }
    let user = await User.findOne({
        where: {id: userID}
    });
    res.send(user);
}
exports.addcard = async (req, res) => {
    console.log("received req")
    let token = req.headers["x-access-token"];

    let s = Config.secret;
    let userID;
    try {
        const decode = jwt.verify(token, s);
        userID = decode.id;
    } catch (err) {
        console.error('JWT')
    }
    try {
        Card.create({
            userid: userID,
            cardnumber: req.body.cardnumber,
            cardname: req.body.cardname,
            cardcvv: req.body.cardcvv,
            cardexpiry: req.body.cardexpiry,
            isprimary: "0"
        }).then(async async => {
            let card = await Card.findAll({
                where: {userid: userID}
            })
            res.send(card);
        });
    } catch (e) {

    }
}

exports.getcards = async (req, res) => {
    console.log("received req");
    let token = req.headers["x-access-token"];

    let s = Config.secret;
    let userID;
    try {
        const decode = jwt.verify(token, s);
        userID = decode.id;
    } catch (err) {
        console.error('JWT verification error');
        return res.status(401).json({ error: 'Unauthorized' });
    }

    try {
        let userCards = await Card.findAll({
            where: { userid: userID }
        });

        res.send(userCards);
    } catch (error) {
        console.error('Error fetching user cards:', error);
        res.status(500).json({ error: 'Internal server error' });
    }
};

exports.editcard = async (req, res) => {
    console.log("received req")
    let token = req.headers["x-access-token"];

    let s = Config.secret;
    let userID;
    try {
        const decode = jwt.verify(token, s);
        userID = decode.id;
    } catch (err) {
        console.error('JWT')
    }
    try {
        Card.update({
                isprimary: "0"
            },
            {
                where: {userid: userID}
            }).then(async () => {
            Card.update({
                    isprimary: "1"
                },
                {
                    where: {id: req.body.id}
                }).then(async () => {
                let updatedUser = await Card.findAll({
                    where: {id: userID}
                })
                res.send(updatedUser);
            });
        });
    } catch (e) {

    }
}

exports.deletecard = async (req, res) => {
    console.log("received req")
    let token = req.headers["x-access-token"];

    let s = Config.secret;
    let userID;
    try {
        const decode = jwt.verify(token, s);
        userID = decode.id;
    } catch (err) {
        console.error('JWT')
    }
    try {
        const cardIDToDelete = req.body.id; // Assuming you pass the card ID in the request parameters
        const deletedCard = await Card.destroy({
            where: {
                id: cardIDToDelete,
                userid: userID
            }
        });

        if (deletedCard > 0) {
            // Card deleted successfully
            res.json({message: 'Card deleted successfully'});
        } else {
            // Card not found or user does not have permission
            res.status(404).json({error: 'Card not found or unauthorized'});
        }
    } catch (error) {
        console.error('Error deleting card:', error);
        res.status(500).json({error: 'Internal server error'});
    }
}
exports.address = async (req, res) => {
    console.log("received req")
    let token = req.headers["x-access-token"];

    let s = Config.secret;
    let userID;
    try {
        const decode = jwt.verify(token, s);
        userID = decode.id;
    } catch (err) {
        console.error('JWT')
    }

    try {
        await uploadFile(req, res);

        if (req.file === undefined) {
            return res.status(400).send({message: "Please upload a file!"});
        }

    } catch (err) {
        if (err.code == "LIMIT_FILE_SIZE") {
            return res.status(500).send({
                message: "File size cannot be larger than 2MB!",
            });
        }
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
    }).then(async () => {
        let address = await Address.findOne({
            where: {userid: userID}
        })
        User.update({
                firstname: req.body.firstname,
                lastname: req.body.lastname,
                gender: req.body.gender,
                mobilenumber: req.body.mobilenumber,
                profilepicture: req.file.originalname,
                addressid: address.id
            },
            {
                where: {id: userID}
            }).then(async () => {
            let updatedUser = await User.findOne({
                where: {id: userID}
            })
            res.send(updatedUser);
        });
    });
};

