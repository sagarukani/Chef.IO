const { authJwt, verifySignUp} = require("../middleware");
const controller = require("../controllers/chef.controller");

module.exports = function(app) {
    app.post("/api/chef/profile",[authJwt.verifyToken, authJwt.isModerator], controller.chefprofile);
    app.post("/api/chef/editchefprofile",[authJwt.verifyToken, authJwt.isModerator], controller.editchefprofile);
    app.get("/api/chef/profile/:id", controller.getchefprofile);
    app.get("/api/chef/profile/", controller.getownprofile);
    app.get("/api/chef/getownschedule",[authJwt.verifyToken, authJwt.isModerator], controller.getschedule);
    app.post("/api/chef/scheduleUpdate",[authJwt.verifyToken, authJwt.isModerator], controller.scheduleUpdate);
    app.post("/api/chef/scheduleCreate", [authJwt.verifyToken, authJwt.isModerator], controller.createschedule);
    app.get("/api/chef/getschedule/:id", controller.getschedulebyid);
};