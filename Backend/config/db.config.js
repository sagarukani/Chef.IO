module.exports = {
    HOST: "localhost",
    USER: "admin",
    PASSWORD: "Callitchef",
    DB: "chefio",
    dialect: "mysql",
    pool: {
      max: 5,
      min: 0,
      acquire: 30000,
      idle: 10000
    }
  };