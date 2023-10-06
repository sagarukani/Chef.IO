--create database tables

CREATE TABLE cfdb_users (
    UserID int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    FirstName varchar(255) NOT NULL,
    LastName varchar(255) NOT NULL,
    Email varchar(255) NOT NULL,
    ProfilePicture varchar(255),
    MobileNumber int NOT NULL,
    CountryCode varchar(25) NOT NULL,
    UserType int NOT NULL,
    Password varchar(255) NOT NULL,
    DataOfBirth Date NOT NULL,
    Gender varchar(25) NOT NULL,
    AddressID int NOT NULL FOREIGN KEY REFERENCES cfdb_address(AddressID),
    CreatedAt Date NOT NULL,
    UpdatedAt Date NOT NULL,
    OTP int,
    fcmToken varchar(255),
    authToken varchar(255),
    MobielToken varchar(255),
    CONSTRAINT fk_AddressID
      FOREIGN KEY(AddressID) 
	  REFERENCES cfdb_address(AddressID)
);

CREATE TABLE cfdb_address (
    AddressID int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Street1 varchar(300) DEFAULT '',
    Street2 varchar(300) DEFAULT '',
    City varchar(64)  DEFAULT '' ,
    Province varchar(64)   DEFAULT ' ',
    PostalCode varchar(10) DEFAULT ' ',
    Country varchar(8)    DEFAULT '',
    UserID int NOT NULL,
    CONSTRAINT fk_UserID
      FOREIGN KEY(UserID) 
	  REFERENCES cfdb_users(UserID)
);

CREATE TABLE cfdb_chef (
    ChefID int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    UserID int NOT NULL FOREIGN KEY REFERENCES cfdb_users(UserID),
    Cuisines text[],
    PreferedCities text[],
    ScheduleID int NOT NULL FOREIGN KEY REFERENCES cfdb_schedule(ScheduleID),
    CONSTRAINT fk_ScheduleID
      FOREIGN KEY(ScheduleID) 
	  REFERENCES cfdb_schedule(ScheduleID)
);
CREATE TABLE cfdb_schedule (
    ScheduleID int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    ChefID int NOT NULL FOREIGN KEY REFERENCES cfdb_chef(ChefID),
    SundayaTime int NOT NULL default "0",
    MondayTime int NOT NULL default "0",
    TuesdayTime int NOT NULL default "0" ,
    WednesdayTime int NOT NULL default "0",
    ThursdayTime int NOT NULL default "0",
    Fridaytime int NOT NULL Default "0",
    SaturdayTime int NOT NULL Default "0",
    CONSTRAINT ChefID
      FOREIGN KEY(ChefID) 
	  REFERENCES cfdb_chef(ChefID)
);

CREATE TABLE cfdb_post (
    PostID int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    ChefID int NOT NULL,
    Media varchar(255) NOT NULL,
    Title varchar(79) NOT NULL UNIQUE,
    Caption varchar(255),
    LikeCount int NOT NULL DEFAULT 0,
    PostDate date NOT NULL,
    CreatedAt time NOT NULL,
    UpdatedAt time NOT NULL,
    CONSTRAINT ChefID
      FOREIGN KEY(ChefID) 
	  REFERENCES cfdb_chef(ChefID)
);

CREATE TABLE cfdb_chat (
    MessageID varchar(255) NOT NULL PRIMARY KEY,
    SendID varchar(255) NOT NULL,
    RecieveID varchar(255) NOT NULL,
    Media varchar(70) NOT NULL,
    Message varchar (255) NOT NULL,
    MessageType varchar(25) NOT NULL,
    DeliveryStatus varchar(25) NOT NULL,
    SenderAvatar varchar(25) NOT NULL,
    SenderName varchar(255) NOT NULL,
    RecieverAvatar varchar(25) NOT NULL,
    RecieverName varchar(255) NOT NULL,   
);

CREATE TABLE cfdb_onlineUsers (
    UserID int NOT NULL,
    SocketID int NOT NULL,
    CONSTRAINT fk_UserID
      FOREIGN KEY(UserID) 
	  REFERENCES cfdb_users(UserID)
);

CREATE TABLE cfdb_payment (
    PaymentID int NOT NULL PRIMARY KEY,
    PaymentType varchar(25) NOT NULL,
    PaymentTime time NOT NULL,
    TransectionID varchar(25) NOT NULL,
    BookingID varchar(25) NOT NULL FOREIGN KEY REFERENCES cfdb_booking(BookingID),
    Amount int NOT NULL,
    IsSuccess int NOT NULL,
    CONSTRAINT fk_BookingID
        FOREIGN KEY(BookingID)
        REFERENCES cfdb_booking(BookingID)
);

CREATE TABLE cfdb_booking (
    BookingID int NOT NULL PRIMARY KEY,
    UserID int NOT NULL FOREIGN KEY REFERENCES cfdb_users(UserID),
    ChefID int NOT NULL FOREIGN KEY REFERENCES cfdb_chef(ChefID),
    BookingTime time NOT NULL,
    BookingDay varchar(25) NOT NULL,
    BookingStatus varchar(25) NOT NULL,
    IsCompaleted int NOT NULL,
    BookingPrice varchar(25) NOT NULL,
    PaymentID int NOT NULL FOREIGN KEY REFERENCES cfdb_payment(PaymentID),
    FeedbackID int NOT NULL FOREIGN KEY REFERENCES cfdb_feedback(FeedbackID),
    CONSTRAINT fk_UserID
      FOREIGN KEY(UserID) 
	  REFERENCES cfdb_users(UserID),
    CONSTRAINT fk_ChefID
        FOREIGN KEY(ChefID)
        REFERENCES cfdb_chef(ChefID),
    CONSTRAINT fk_PaymentID
        FOREIGN KEY(PaymentID)
        REFERENCES cfdb_payment(PaymentID),
    CONSTRAINT fk_FeedbackID
        FOREIGN KEY(FeedbackID)
        REFERENCES cfdb_feedback(FeedbackID)
);

CREATE TABLE cfdb_feedback (
    FeedbackID int NOT NULL PRIMARY KEY,
    Message varchar(255) NOT NULL,
    CreatedAt time NOT NULL,
    UpdatedAt time NOT NULL,
    Reting int NOT NULL DEFAULT 1,
);
