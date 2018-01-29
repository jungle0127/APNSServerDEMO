--
-- Initiate database
--

DROP DATABASE IF EXISTS `apns`;
CREATE DATABASE apns DEFAULT CHARACTER SET utf8;
USE apns;

-- token table
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`(
  `id` BIGINT AUTO_INCREMENT,
  `token` VARCHAR(100) NOT NULL,
  `active` TINYINT DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;