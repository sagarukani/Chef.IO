const config = require("../config/db.config");

const Sequelize = require("sequelize");
const sequelize = new Sequelize(
    config.DB,
    config.USER,
    config.PASSWORD,
    {
      host: config.HOST,
      dialect: config.dialect,
      pool: {
        max: config.pool.max,
        min: config.pool.min,
        acquire: config.pool.acquire,
        idle: config.pool.idle
      }
    }
  );

  const db = {};

  db.Sequelize = Sequelize;
  db.sequelize = sequelize;

  db.chef = require("./chef.model")(sequelize, Sequelize);
  db.user = require("./user.model")(sequelize, Sequelize);
  db.role = require("./role.model")(sequelize, Sequelize);

  db.post = require("./post.model")(sequelize, Sequelize);
  db.schedule = require("./schedule.model")(sequelize, Sequelize);
  db.payment = require("./payment.model")(sequelize, Sequelize);
  db.feedback = require("./feedback.model")(sequelize, Sequelize);
  db.booking = require("./booking.model")(sequelize, Sequelize);
  db.address = require("./address.model")(sequelize, Sequelize);
  db.cuisine = require("./cuisine.model")(sequelize, Sequelize);

  db.user.hasOne(db.address);
  db.address.belongsTo(db.user);

  db.user.belongsToMany(db.role,{
    through: "user_type"
  });
  db.role.belongsToMany(db.user,{
    through: "user_type"
  });
  db.user.hasMany(db.cuisine, {as:"cuisines"});
  db.cuisine.belongsTo(db.user);
  // db.chef.belongsTo(db.user);

  db.ROLES = ["user", "chef", "admin"];
  module.exports = db;