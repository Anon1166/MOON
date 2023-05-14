-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema announcements
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema announcements
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `announcements` DEFAULT CHARACTER SET utf8mb3 ;
USE `announcements` ;

-- -----------------------------------------------------
-- Table `announcements`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `announcements`.`category` (
  `categoryId` INT NOT NULL,
  `categoryName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`categoryId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `announcements`.`announcement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `announcements`.`announcement` (
  `announcementId` INT AUTO_INCREMENT NOT NULL,
  `announcementTitle` VARCHAR(200) NOT NULL,
  `announcementDescription` VARCHAR(10000) NOT NULL,
  `publishDate` DATETIME NULL DEFAULT NULL,
  `closeDate` DATETIME NULL DEFAULT NULL,
  `announcementDisplay` ENUM('Y', 'N') NOT NULL DEFAULT 'N',
  `categoryId` INT NOT NULL,
  PRIMARY KEY (`announcementId`),
  INDEX `fk_announcement_category_idx` (`categoryId` ASC) VISIBLE,
  CONSTRAINT `fk_announcement_category`
    FOREIGN KEY (`categoryId`)
    REFERENCES `announcements`.`category` (`categoryId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;