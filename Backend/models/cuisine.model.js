module.exports = (sequelize, Sequelize) =>{

    const Cuisine = sequelize.define("cuisine", {
        cuisines: {
            type: Sequelize.STRING
        },
        userid: {
            type: Sequelize.INTEGER
        }
    });


    return Cuisine;

};