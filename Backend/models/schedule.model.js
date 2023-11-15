module.exports = (sequelize, Sequelize) =>{

    const Schedule = sequelize.define("schedule", {
        chefid: {
            type: Sequelize.INTEGER
        },
        sundayatime: {
            type: Sequelize.STRING
        },
        mondaytime: {
            type: Sequelize.STRING
        },
        tuesdaytime: {
            type: Sequelize.STRING
        },
        wednesdaytime: {
            type: Sequelize.STRING
        },
        thursdaytime: {
            type: Sequelize.STRING
        },
        fridaytime: {
            type: Sequelize.STRING
        },
        saturdaytime: {
            type: Sequelize.STRING
        }

    });
    
    
    return Schedule;

};