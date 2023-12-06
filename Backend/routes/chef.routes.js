const {authJwt, verifySignUp} = require("../middleware");
const controller = require("../controllers/chef.controller");

module.exports = function (app) {
    app.post("/api/chef/profile", controller.chefprofile);
    app.post("/api/chef/editchefprofile", controller.editchefprofile);
    app.get("/api/chef/profile/:id", controller.getchefprofile);
    app.get("/api/chef/profile", controller.getownprofile);
    app.post("/api/chef/getprofile", controller.getprofile);
    app.get("/api/chef/getownschedule", controller.getschedule);
    app.post("/api/chef/scheduleUpdate", controller.scheduleUpdate);
    app.post("/api/chef/scheduleCreate", controller.createschedule);
    app.get("/api/chef/getschedule/:id", controller.getschedulebyid);
};