DROP DATABASE IF EXISTS `yummySystem`;
CREATE DATABASE `yummySystem` DEFAULT CHARACTER SET UTF8MB4;
USE `yummySystem`;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customerId` VARCHAR(64) NOT NULL,
  `customerMail` VARCHAR(64) NOT NULL,
  `customerPassword` VARCHAR(64) NOT NULL,
  `customerName` VARCHAR(64),
  `phoneNumber` VARCHAR(64),
  `vipPoints` INT,
  `vipLevel` INT,
  `active` BOOLEAN,
  PRIMARY KEY (`customerId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant` (
  `restaurantId` VARCHAR(64) NOT NULL,
  `restaurantPassword` VARCHAR(64) NOT NULL,
  `restaurantName` VARCHAR(64),
  `restaurantType` VARCHAR(64),
  PRIMARY KEY (`restaurantId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `userId` VARCHAR(64) NOT NULL,
  `address` VARCHAR(64) NOT NULL,
  `coordinateX` INT,
  `coordinateY` INT,
  PRIMARY KEY (`userId`,`address`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

DROP TABLE IF EXISTS `modification`;
CREATE TABLE `modification` (
  `modificationId` INT,
  `modificationDate` VARCHAR(64) NOT NULL,
  `restaurantId` VARCHAR(64) NOT NULL,
  `modifiedName` VARCHAR(64) NOT NULL,
  `modifiedType` VARCHAR(64),
  `modifiedAddress` VARCHAR(64),
  `checked` BOOLEAN,
  `approve` BOOLEAN,
  PRIMARY KEY (`modificationId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

DROP TABLE IF EXISTS `commodityInfo`;
CREATE TABLE `commodityInfo` (
  `commodityInfoId` INT,
  `restaurantId` VARCHAR(64) NOT NULL,
  `commodityName` VARCHAR(64) NOT NULL,
  `type` VARCHAR(64),
  `commodityPrice` DOUBLE,
  `amount` INT,
  `beginDate` DATETIME,
  `endDate` DATETIME,
  PRIMARY KEY (`commodityInfoId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

DROP TABLE IF EXISTS `commodityItem`;
CREATE TABLE `commodityItem` (
  `commodityItemId` INT,
  `restaurantId` VARCHAR(64) NOT NULL,
  `itemName` VARCHAR(64) NOT NULL,
  `itemPrice` DOUBLE,
  PRIMARY KEY (`commodityItemId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

DROP TABLE IF EXISTS `discountInfo`;
CREATE TABLE `discountInfo` (
  `discountInfoId` INT,
  `restaurantId` VARCHAR(64) NOT NULL,
  `commodityName` VARCHAR(64) NOT NULL,
  `discount` DOUBLE,
  `discountAmount` INT,
  `beginDate` DATETIME,
  `endDate` DATETIME,
  PRIMARY KEY (`discountInfoId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

DROP TABLE IF EXISTS `orderInfo`;
CREATE TABLE `orderInfo` (
  `orderId` INT,
  `customerId` VARCHAR(64) NOT NULL,
  `restaurantId` VARCHAR(64) NOT NULL,
  `deliveryAddress` VARCHAR(64),
  `orderTime` DATETIME,
  `deliveryTime` DATETIME,
  `totalPrice` DOUBLE,
  `orderCondition` VARCHAR(64),
  PRIMARY KEY (`orderId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

DROP TABLE IF EXISTS `orderItem`;
CREATE TABLE `orderItem` (
  `orderItemId` INT,
  `itemName` VARCHAR(64) NOT NULL,
  `amount` INT,
  `subTotal` DOUBLE,
  PRIMARY KEY (`orderItemId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `accountInfoId` INT,
  `userId` VARCHAR(64) NOT NULL,
  `accountId` VARCHAR(64) NOT NULL,
  `paymentPassword` VARCHAR(64) NOT NULL,
  `balance` DOUBLE,
  `inUse` BOOLEAN,
  PRIMARY KEY (`accountInfoId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

INSERT INTO account(`accountInfoId`,`userId`,`accountId`,`paymentPassword`,`balance`,`inUse`) VALUES (10000000,'admin','001','123456','0',TRUE);