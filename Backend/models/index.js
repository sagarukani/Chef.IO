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

  db.user = require("./user.model")(sequelize, Sequelize);
  db.chef = require("./chef.model")(sequelize, Sequelize);
  db.post = require("./post.model")(sequelize, Sequelize);
  db.schedule = require("./schedule.model")(sequelize, Sequelize);
  db.payment = require("./payment.model")(sequelize, Sequelize);
  db.feedback = require("./feedback.model")(sequelize, Sequelize);
  db.booking = require("./booking.model")(sequelize, Sequelize);
  db.address = require("./address.model")(sequelize, Sequelize);
  
  db.user.HasOne(db.address);
  db.address.BelongsTo(db.user);
  db.chef.HasOne(db.user);
  db.user.BelongsTo(db.chef);

  module.exports = db;