const { authJwt, verifySignUp} = require("../middleware");
const controller = require("../controllers/booking.controller");

module.exports = function(app) {
    app.get("/api/booking/getbookings", controller.getBookings);
    app.get("/api/booking/getbookingbyid/:id", controller.getBookingsById);
    app.post("/api/booking/createbooking", controller.createBooking);
    app.get("/api/booking/getfeedbacks", controller.getFeedbacks);
    app.get("/api/chef/getownbookings", controller.getuserbooking);
    app.post("/api/booking/createfeedback", controller.createFeedback);
    app.get("/api/booking/getownbookings", [authJwt.verifyToken, authJwt.isModerator], controller.getchefbooking);
    app.get("/api/booking/getfeedback/:id", controller.getFeedbackById);
};