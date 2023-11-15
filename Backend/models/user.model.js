module.exports = (sequelize, Sequelize) =>{

    const User = sequelize.define("users", {
        username: {
            type: Sequelize.STRING
        },
        firstname: {
            type: Sequelize.STRING
        },
        lastname: {
            type: Sequelize.STRING
        },
        email: {
            type: Sequelize.STRING
        },
        password: {
            type: Sequelize.STRING
        },
        profilepicture: {
            type: Sequelize.STRING
        },
        mobilenumber: {
            type: Sequelize.STRING
        },
        gender:{
            type: Sequelize.ENUM("1","2")
        },
        addressid:{
            type: Sequelize.STRING
        }

    });
    
    
    return User;

};