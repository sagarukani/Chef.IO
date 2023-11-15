module.exports = (sequelize, Sequelize) =>{

    const Cuisine = sequelize.define("cuisine", {
        id:{
            type: sequelize.INTEGER,
            primaryKey: true
        },
        cuisines: {
            type: Sequelize.STRING
        }
    });


    return Cuisine;

};