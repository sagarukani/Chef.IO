const { authJwt } = require("../middleware");
const controller = require("../controllers/user.controller");

module.exports = function(app) {
    app.use(function(req, res, next) {
        res.header(
            "Access-Control-Allow-Headers",
            "x-access-token, Origin, Content-Type, Accept"
        );
        next();
    });

    app.get("/api/test/all", controller.allAccess);

    app.get(
        "/api/test/user",
        [authJwt.verifyToken],
        controller.userBoard
    );

    app.get(
        "/api/test/mod",
        [authJwt.verifyToken, authJwt.isModerator],
        controller.chefBoard
    );

    app.post("/api/user/address", controller.address);
    app.post("/api/user/addcard", controller.addcard);
    app.post("/api/user/editcard", controller.editcard);
    app.post("/api/user/deletecard", controller.deletecard);
    app.get(
        "/api/test/admin",
        [authJwt.verifyToken, authJwt.isAdmin],
        controller.adminBoard
    );
    app.get("/api/user/getuserprofile", controller.getUserProfile)
    app.get("/api/user/getcards", controller.getcards)
};