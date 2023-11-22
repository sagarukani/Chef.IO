const {authJwt} = require("../middleware");
const controller = require("../controllers/post.controller");

module.exports = function (app) {
    app.post("/api/post/createpost", [authJwt.verifyToken, authJwt.isModerator], controller.createPost);
    app.post("/api/post/editpost/:id", [authJwt.verifyToken, authJwt.isModerator], controller.editpost);
    app.delete("/api/post/postbyid/:id", controller.deletePost);
    app.get("/api/post/", controller.getallPost);
    app.get("/api/post/ownpost", [authJwt.verifyToken, authJwt.isModerator], controller.getOwnPost);
};