module.exports = (sequelize, Sequelize) =>{

    const Chef = sequelize.define("chef", {
        cuisines: {
            type: Sequelize.STRING
        },
        preferedcities: {
            type: Sequelize.STRING
        },
        scheduleid: {
            type: Sequelize.INTEGER
        },
        userid: {
            type: Sequelize.INTEGER
        }

    });
    
    
    return Chef;

};