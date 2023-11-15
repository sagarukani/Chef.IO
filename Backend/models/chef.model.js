module.exports = (sequelize, Sequelize) =>{

    const Chef = sequelize.define("chef", {
        cuisines: {
            type: Sequelize.STRING
        },
        preferedcities: {
            type: Sequelize.STRING
        },
        intro:{
            type: Sequelize.STRING
        },
        facebooklink:{
            type:Sequelize.STRING
        },
        instagramlink:{
            type:Sequelize.STRING
        },
        youtubelink:{
            type:Sequelize.STRING
        },
        Xlink:{
            type:Sequelize.STRING
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