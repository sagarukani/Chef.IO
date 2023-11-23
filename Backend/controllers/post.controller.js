const db = require("../models");
const User = db.user;
const Schedule = db.schedule;
const Chef = db.chef;
const Post = db.post;
const Config = require("../config/auth.config");
const jwt = require('jsonwebtoken');
const {where} = require("sequelize");
const uploadFile = require("../middleware/upload");


exports.createPost = (req, res) =>{
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
    Post.create({
        chefid: chef.id,
        media: req.file.originalname,
        title: req.body.title,
        caption: req.body.caption,
        likecount: req.body.likecount,
        postdate: new Date().toString()
    }).then( async () => {
            let chef = await Chef.findOne({
                where: {userid: userID}
            });
            let createdpost = await Post.findOne({
                where: {chefid: chef.id}
            })
            res.send(createdpost);
        }
    )};
exports.getOwnPost = async (req, res) => {
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
    let mypost = await Post.findAll({where: {chefid: chef.id}});
    res.send(mypost);
}
exports.editpost = (req, res) => {
  const id = req.params.id;

  Post.update({
      media: req.file.originalname,
      title: req.body.title,
      caption: req.body.caption,
      likecount: req.body.likecount,
      postdate: new Date().toString()
  },{where : {id: id}}).then(async () => {
      let updatedpost = await Post.findOne({
          where: {id: id}
      });
      res.send(updatedpost);
  })
};
exports.getallPost = async (req, res) => {
    let posts = await Post.findAll();
    res.send(posts)
}
exports.deletePost = (req, res) =>{
    const id = req.params.id;
    Post.destroy({
        where: {id: id}
    }).then(num => {
        if (num == 1) {
            res.send({
                message: "Tutorial was deleted successfully!"
            });
        } else {
            res.send({
                message: `Cannot delete Tutorial with id=${id}. Maybe Tutorial was not found!`
            });
        }
    })
        .catch(err => {
            res.status(500).send({
                message: "Could not delete Tutorial with id=" + id
            });
        });
};


