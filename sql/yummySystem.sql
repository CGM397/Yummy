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
  `restaurantId` VARCHAR(64) NOT NULL,
  `modifiedType` VARCHAR(64),
  `modifiedAddress` VARCHAR(64),
  `approve` BOOLEAN,
  PRIMARY KEY (`restaurantId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;