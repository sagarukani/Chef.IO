module.exports = (sequelize, Sequelize) =>{

    const Post = sequelize.define("post", {
        chefid: {
            type: Sequelize.INTEGER
        },
        media: {
            type: Sequelize.STRING
        },
        title: {
            type: Sequelize.STRING
        },
        caption: {
            type: Sequelize.STRING
        },
        likecount: {
            type: Sequelize.INTEGER
        },
        postdate: {
            type: Sequelize.DATE
        }

    });
    
    
    return Post;

};