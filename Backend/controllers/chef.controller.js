const db = require("../models");
const User = db.user;
const Schedule = db.schedule;
const Chef = db.chef;
const Config = require("../config/auth.config");
const jwt = require('jsonwebtoken');
const {where} = require("sequelize");

exports.createschedule = async (req, res) => {
    let token = req.headers["x-access-token"];

    let s = Config.secret;
    let userID;
    try {
        const decode = jwt.verify(token, s);
        // console.log(decode);
        userID = decode.id;
    } catch (err) {
        console.error('JWT verification error:', err.message);
        console.error('JWT')
    }
    let chef = await Chef.findOne({
        where: {userid: userID}
    });
    console.log("chef data is" + chef);
    await Schedule.create({
        chef: chef.id,
        sundayatime: req.body.sundayatime,
        mondaytime: req.body.mondaytime,
        tuesdaytime: req.body.tuesdaytime,
        wednesdaytime: req.body.wednesdaytime,
        thursdaytime: req.body.thursdaytime,
        fridaytime: req.body.fridaytime,
        saturdaytime: req.body.saturdaytime
    }).then(async () => {
        let schedule = await Schedule.findOne({
            where: {chefid: chef.id}
        });
        console.log("schedule data" + schedule);
        res.send(schedule);
    });
};
exports.getschedulebyid = async (req, res) => {
    let id = req.params.id;
    let user = User.findOne({
        where: {id: id}
    });
    let profile = Chef.findOne({
        where: {userid: user.id}
    });
    let schedule = await Schedule.findOne({
        where: {chefid: profile.id}
    });
    res.send(schedule);
}

exports.scheduleUpdate = async (req, res) => {
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
    let chef = await Chef.findOne({
        where: {userid: userID}
    });
    console.log(chef);
    await Schedule.update({
        sundayatime: req.body.sundayatime,
        mondaytime: req.body.mondaytime,
        tuesdaytime: req.body.tuesdaytime,
        wednesdaytime: req.body.wednesdaytime,
        thursdaytime: req.body.thursdaytime,
        fridaytime: req.body.fridaytime,
        saturdaytime: req.body.saturdaytime
    }, {
        where: {chefid: chef.id}
    }).then(async () => {
        let schedule = await Schedule.findOne({
            where: {chefid: chef.id}
        });
        console.log(schedule);
        res.send(schedule);
    })
};
exports.getschedule = async (req, res) => {
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
    let chef = Chef.findOne({
        where: {userid: userID}
    });
    let schedule = await Schedule.findOne({
        where: {chefid: chef.id}
    });
    res.send(schedule);
};
exports.getchefprofile = async (req, res) => {
    let id = req.params.id;

    let profile = await Chef.findOne({
        where: {id: id}
    });
    res.send(profile);
};
exports.getownprofile = async (req, res) => {
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
    let chef = await Chef.findOne({
        where: {userid: userID}
    });
    res.send(chef);
}
exports.getprofile = async (req, res) => {
    let uname = req.body.username;
    let chef = await User.findOne({
        where: {username: uname}
    });
    res.send(chef);
}
exports.editchefprofile = (req, res) => {
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
    Chef.update({
        cuisines: req.body.cuisines,
        preferedcities: req.body.preferedcities,
        intro: req.body.intro,
        facebooklink: req.body.facebooklink,
        instagramlink: req.body.instagramlink,
        youtubelink: req.body.youtubelink,
        Xlink: req.body.Xlink
    }, {
        where: {userid: userID}
    }).then(async () => {
        let createdchef = await Chef.findOne({
            where: {userid: userID}
        });
        console.log(createdchef)
        res.send(createdchef);
    });
}

exports.chefprofile = (req, res) => {
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
    Chef.create({
        cuisines: req.body.cuisines,
        preferedcities: req.body.preferedcities,
        intro: req.body.intro,
        facebooklink: req.body.facebooklink,
        instagramlink: req.body.instagramlink,
        youtubelink: req.body.youtubelink,
        Xlink: req.body.Xlink,
        userid: userID
    }).then(async () => {
        let chef = await Chef.findOne({
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
        }).then(async () => {
            let scheule = await Schedule.findOne({
                where: {chefid: chef.id}
            })
            Chef.update({
                scheduleid: scheule.id
            }, {
                where: {id: chef.id}
            }).then(async () => {
                let createdchef = await Chef.findOne({
                    where: {userid: userID}
                });
                res.send(createdchef);
            });
        });
    });
};