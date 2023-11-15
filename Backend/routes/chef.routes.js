const { authJwt } = require("../middleware");
const controller = require("../controllers/chef.controller");

app.post("/api/chef/profile",[authJwt.verifyToken, authJwt.isModerator], controller.chefprofile);
app.post("/api/chef/editchefprofile",[authJwt.verifyToken, authJwt.isModerator], controller.editchefprofile);
app.get("/api/chef/profile/:id", controller.getchefprofile);
app.get("/api/chef/profile/", controller.getownprofile);
app.get("/api/chef/getschedule",[authJwt.verifyToken, authJwt.isModerator], controller.getschedule);
app.post("/api/chef/scheduleUpdate",[authJwt.verifyToken, authJwt.isModerator], controller.scheduleUpdate);