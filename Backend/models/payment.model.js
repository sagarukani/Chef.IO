module.exports = (sequelize, Sequelize) =>{
    const Payment = sequelize.define("payment", {
        paymenttype: {
            type: Sequelize.STRING
        },
        paymenttime: {
            type: Sequelize.DATE
        },
        transectionid: {
            type: Sequelize.STRING
        },
        bookingid: {
            type: Sequelize.INTEGER
        },
        amount: {
            type: Sequelize.INTEGER
        },
        issuccess: {
            type: Sequelize.BOOLEAN
        }

    });
    return Payment;
};