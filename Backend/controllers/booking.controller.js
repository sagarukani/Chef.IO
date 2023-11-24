const db = require("../models");
const User = db.user;
const Schedule = db.schedule;
const Chef = db.chef;
const Booking = db.booking;
const Feedback = db.feedback;
const Config = require("../config/auth.config");
const jwt = require('jsonwebtoken');
const {where} = require("sequelize");

exports.getBookings = async (req, res) => {
    let booking = await Booking.findAll();
    res.send(booking);
};

exports.getBookingsById = async (req, res) =>{
    let id = req.params.id;
    let booking = await Booking.findOne({
        where:{id: id}
    });
    if(!booking){
        res.send({message:"Booking Not Found"});
    }
    else{
        res.send(booking);
    }
}
exports.createBooking = async (req,res) =>{
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
    Booking.create({
        userid: userID,
        chefid: req.body.chefid,
        bookingtime: req.body.bookingtime,
        bookingday: req.body.bookingday,
        bookingstatus: req.body.bookingstatus,
        iscompaleted: req.body.iscompaleted,
        bookingprice: req.body.bookingprice
    }).then(async (booking)=>{
        await res.send(booking);
    });
};
exports.getuserbooking = async (req, res) =>{
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
    let booking = await Booking.findAll({
        where: {userid: userID}
    });
    res.send(booking);
};
exports.getchefbooking = async (req, res) =>{
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
    let chef = Chef.findOne({
        where:{userid: userID}
    });
    let booking = await Booking.findAll({
        where: {chefid: chef.id}
    });
    res.send(booking);
};
exports.getFeedbacks = async (req, res) => {
    let feedback = await Feedback.findAll();
    res.send(feedback);
};

exports.getFeedbackById = async (req, res) =>{
    let id = req.params.id;
    let feedback = await Feedback.findOne({
        where:{id:id}
    });
    res.send(feedback);
}

exports.createFeedback = async (req, res) =>{
    let bookingid = req.params.id;
    await Feedback.create({
        bookingid: bookingid,
        message: req.body.message,
        rating: req.body.rating
    }).then(async (feedback) =>{
        await Booking.update({
            feedbackid: feedback.id
        },{where: {id: bookingid}
        });
        res.send(feedback);
    });
};

