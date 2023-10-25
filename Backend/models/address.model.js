module.exports = (sequelize, Sequelize) =>{

    const Address = sequelize.define("address", {
        street1: {
            type: Sequelize.STRING
        },
        street2: {
            type: Sequelize.STRING
        },
        city: {
            type: Sequelize.STRING
        },
        province: {
            type: Sequelize.STRING
        },
        postalcode: {
            type: Sequelize.STRING
        },
        country: {
            type: Sequelize.STRING
        },
        userid: {
            type: Sequelize.INTEGER
        }
        
        

    });
    
    
    return Address;

};