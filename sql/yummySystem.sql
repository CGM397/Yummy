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
  `deliveryAddress` TEXT,
  `vipLevel` INT,
  PRIMARY KEY (`customerId`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;