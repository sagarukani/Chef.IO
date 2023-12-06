module.exports = (sequelize, Sequelize) =>{
    const Cards = sequelize.define("card", {
        cardnumber: {
            type: Sequelize.STRING
        },
        cardname: {
            type: Sequelize.STRING
        },
        cardcvv: {
            type: Sequelize.STRING
        },
        cardexpiry: {
            type: Sequelize.STRING
        },
        isprimary:{
            type : Sequelize.INTEGER
        },
        userid:{
            type : Sequelize.INTEGER
        }
    });
    return Cards;
};