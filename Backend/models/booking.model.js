module.exports = (sequelize, Sequelize) =>{

    const Booking = sequelize.define("booking", {
        userid: {
            type: Sequelize.INTEGER
        },
        chefid: {
            type: Sequelize.INTEGER
        },
        bookingtime: {
            type: Sequelize.DATE
        },
        bookingday: {
            type: Sequelize.STRING
        },
        bookingstatus: {
            type: Sequelize.STRING
        },
        iscompaleted: {
            type: Sequelize.BOOLEAN
        },
        bookingprice: {
            type: Sequelize.INTEGER
        },
        feedbackid: {
            type: Sequelize.INTEGER
        },
        paymentid:{
            type: Sequelize.INTEGER
        }

    });
    
    
    return Booking;

};