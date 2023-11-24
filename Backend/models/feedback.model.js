module.exports = (sequelize, Sequelize) =>{

    const Feedback = sequelize.define("feedback", {
        bookingid: {
            type: Sequelize.STRING
        },
        message: {
            type: Sequelize.STRING
        },
        rating: {
            type: Sequelize.INTEGER
        }

    });
    
    
    return Feedback;

};