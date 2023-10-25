module.exports = (sequelize, Sequelize) =>{

    const Schedule = sequelize.define("schedule", {
        chefid: {
            type: Sequelize.INTEGER
        },
        sundayatime: {
            type: Sequelize.DATE
        },
        mondaytime: {
            type: Sequelize.DATE
        },
        tuesdaytime: {
            type: Sequelize.DATE
        },
        wednesdaytime: {
            type: Sequelize.DATE
        },
        thursdaytime: {
            type: Sequelize.DATE
        },
        fridaytime: {
            type: Sequelize.DATE
        },
        saturdaytime: {
            type: Sequelize.DATE
        }

    });
    
    
    return Schedule;

};