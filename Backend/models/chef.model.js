module.exports = (sequelize, Sequelize) =>{

    const Chef = sequelize.define("chef", {
        userid: {
            type: Sequelize.INTEGER
        },
        cuisines: {
            type: Sequelize.STRING
        },
        preferedcities: {
            type: Sequelize.STRING
        },
        scheduleid: {
            type: Sequelize.INTEGER
        }

    });
    
    
    return Chef;

};