const db = require("../models");
const User = db.user;
const Schedule = db.schedule;
const Chef = db.chef;
const Config = require("../config/auth.config");
const jwt = require('jsonwebtoken');
const {where} = require("sequelize");



exports.scheduleUpdate = (req, res) => {
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
        where: {userid: userID}
    });
    Schedule.update({
        sundayatime: req.body.sundayatime,
        mondaytime: req.body.mondaytime,
        tuesdaytime: req.body.tuesdaytime,
        wednesdaytime: req.body.wednesdaytime,
        thursdaytime: req.body.thursdaytime,
        fridaytime: req.body.fridaytime,
        saturdaytime: req.body.saturdaytime
    }, {
        where: {chefid: chef.id}
    }).then(()=>{
        res.send({message: "Chef data updated"});
    })
};
exports.getschedule = (req, res) =>{
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
        where: {userid: userID}
    });
    let schedule = Schedule.findOne({
        where: {chefid : chef.id}
    });
    res.send(schedule);
};
exports.getchefprofile = (req, res) => {
    let id = req.params.id;

    let profile = Chef.findOne({
        where: {id: id}
    });
    res.send(profile);
};
exports.getownprofile = (req, res) => {
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
        where: {userid: userID}
    });
    res.send(chef);
}
exports.editchefprofile = (req, res) => {
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
    Chef.update({
        cuisines: req.body.cuisines,
        preferedcities: req.body.preferedcities,
        intro: req.body.intro,
        facebooklink: req.body.facebooklink,
        instagramlink: req.body.instagramlink,
        youtubelink: req.body.youtubelink,
        Xlink: req.body.Xlink
    },{
        where: {userid: userID}
    }).then(()=>{
        res.send({message: "Chef profile updated"});
    });
}

exports.chefprofile = (req, res) => {
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
    Chef.create({
        cuisines: req.body.cuisines,
        preferedcities: req.body.preferedcities,
        intro: req.body.intro,
        facebooklink: req.body.facebooklink,
        instagramlink: req.body.instagramlink,
        youtubelink: req.body.youtubelink,
        Xlink: req.body.Xlink,
        userid: userID
    }).then(()=>{
        let chef = Chef.findOne({
            where: {userid: userID}
        });
        Schedule.create({
            chefid: chef.id,
            sundayatime: req.body.sundayatime,
            mondaytime: req.body.mondaytime,
            tuesdaytime: req.body.tuesdaytime,
            wednesdaytime: req.body.wednesdaytime,
            thursdaytime: req.body.thursdaytime,
            fridaytime: req.body.fridaytime,
            saturdaytime: req.body.saturdaytime,
        }).then(()=>{
            let scheule = Schedule.findOne({
                where: {chefid: chef.id}
            })
            Chef.update({
                scheduleid: scheule.id
            }, {
                where: {id: chef.id}
            }).then(()=>{
                res.send({message: "Chef data updated"});
            });
        });
    });
};