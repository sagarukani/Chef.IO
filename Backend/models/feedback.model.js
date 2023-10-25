module.exports = (sequelize, Sequelize) =>{

    const Feedback = sequelize.define("feedback", {
        message: {
            type: Sequelize.STRING
        },
        rating: {
            type: Sequelize.INTEGER
        }

    });
    
    
    return Feedback;

};