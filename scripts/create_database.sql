DROP SCHEMA IF EXISTS `internet_shop`;

CREATE SCHEMA IF NOT EXISTS `internet_shop` DEFAULT CHARACTER SET utf8;
USE `internet_shop`;

CREATE TABLE IF NOT EXISTS `internet_shop`.`users` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  `surname` VARCHAR(64) NULL,
  `login` VARCHAR(32) NOT NULL,
  `email` VARCHAR(64) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `country` VARCHAR(64) NULL,
  `photo` VARCHAR(128) NULL,
  `role` VARCHAR(64),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `internet_shop`.`categories` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `parent` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `internet_shop`.`manufacturers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `internet_shop`.`input_devices` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(128) NOT NULL,
  `price` DECIMAL(13,2) UNSIGNED NOT NULL,
  `count` BIGINT UNSIGNED NOT NULL,
  `category` INT UNSIGNED NOT NULL,
  `parameters` VARCHAR(1024) NOT NULL,
  `photo` VARCHAR(256) NULL,
  `manufacturer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `kf_categories_id_idx` (`category` ASC) VISIBLE,
  INDEX `fk_manufacturers_id_idx` (`manufacturer` ASC) VISIBLE,
  CONSTRAINT `fk_categories_id`
    FOREIGN KEY (`category`)
    REFERENCES `internet_shop`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_manufacturers_id`
    FOREIGN KEY (`manufacturer`)
    REFERENCES `internet_shop`.`manufacturers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `internet_shop`.`orders` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` ENUM('accepted', 'confirmed', 'in_process', 'sended', 'finished', 'canseled') NOT NULL,
  `details` VARCHAR(512) NULL,
  `date` DATETIME NOT NULL,
  `user_id` BIGINT NOT NULL,
  `pay_type` VARCHAR(64) NOT NULL,
  `pay_requisites` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `internet_shop`.`order_items` (
  `order_id` BIGINT UNSIGNED NOT NULL,
  `input_device_id` BIGINT UNSIGNED NOT NULL,
  `price` DECIMAL NOT NULL,
  `count` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`, `input_device_id`))
ENGINE = InnoDB;

USE `internet_shop` ;

DELIMITER $$
USE `internet_shop`$$
CREATE PROCEDURE `recursiveCategoriesSelection`( catname BIGINT )
BEGIN  
	WITH RECURSIVE `cte` (`id`, `name`, `parent`) 
	AS ( 
		SELECT `categories`.`id`, `categories`.`name`, `categories`.`parent` 
		FROM `categories` 
		WHERE `categories`.`id` = catname
		UNION ALL 
		SELECT `p`.`id`, `p`.`name`, `p`.`parent` 
		FROM `categories` AS `p` 
		INNER JOIN `cte` ON `p`.`parent` = `cte`.`id` 
	) 
	SELECT `cte`.`id`, `cte`.`name`, `cte`.`parent`
	FROM `cte`;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `internet_shop`.`users` (`id`, `name`, `surname`, `login`, `email`, `password`, `country`, `photo`) VALUES (1, 'Illia', 'Veremiev', 'illia', 'illia_veremiev@epam.com', '12345678', 'Ukraine', NULL);


START TRANSACTION;
USE `internet_shop`;
INSERT INTO `internet_shop`.`categories` (`id`, `name`, `parent`) VALUES (3, 'keyboard', 1);
INSERT INTO `internet_shop`.`categories` (`id`, `name`, `parent`) VALUES (2, 'mouse', 1);
INSERT INTO `internet_shop`.`categories` (`id`, `name`, `parent`) VALUES (1, 'root', NULL);
INSERT INTO `internet_shop`.`categories` (`id`, `name`, `parent`) VALUES (4, 'mechanical keyboard', 3);

COMMIT;

START TRANSACTION;
USE `internet_shop`;
INSERT INTO `internet_shop`.`manufacturers` (`id`, `name`) VALUES (1, 'Logitec');
INSERT INTO `internet_shop`.`manufacturers` (`id`, `name`) VALUES (2, 'Razer');
INSERT INTO `internet_shop`.`manufacturers` (`id`, `name`) VALUES (3, 'A4Tech');
INSERT INTO `internet_shop`.`manufacturers` (`id`, `name`) VALUES (4, 'SteelSeries');
INSERT INTO `internet_shop`.`manufacturers` (`id`, `name`) VALUES (5, 'Sven');

COMMIT;


START TRANSACTION;
USE `internet_shop`;
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (1, 'Bloody V5M X\'Glide Multi-Core Gaming Mouse', 99.99, 0, 2, '{\"buttonsCount\":5,\"size\":\"medium\",\"scrollsCount\":1}', 'bloody v5m.png', 1);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (2, 'Bloody R8A Wireless Gaming Mouse', 119.99, 5, 2, '{\"buttonsCount\":5,\"size\":\"medium\",\"scrollsCount\":1}', 'bloody r8a.png', 1);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (3, 'M90 HD', 49.99, 10, 2, '{\"buttonsCount\":3,\"size\":\"medium\",\"scrollsCount\":1}', 'm90 hd.png', 2);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (4, 'G305 LIGHTSPEED Wireless', 149.99, 15, 2, '{\"buttonsCount\":4,\"size\":\"medium\",\"scrollsCount\":2}', 'g305.png', 2);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (5, 'G400', 89.99, 20, 2, '{\"buttonsCount\":7,\"size\":\"medium\",\"scrollsCount\":1}', 'g400.png', 2);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (6, 'Mamba Gaming Mouse', 129.99, 25, 2, '{\"buttonsCount\":6,\"size\":\"medium\",\"scrollsCount\":1}', 'Razer_Mamba.png', 3);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (7, 'Naga Gaming Mouse', 139.99, 30, 2, '{\"buttonsCount\":16,\"size\":\"medium\",\"scrollsCount\":1}', 'naga.png', 3);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (8, 'DeathAdder Gaming Mouse', 99.99, 35, 2, '{\"buttonsCount\":4,\"size\":\"medium\",\"scrollsCount\":1}', 'deathadder.png', 3);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (9, 'RX-165', 34.99, 40, 2, '{\"buttonsCount\":2,\"size\":\"small\",\"scrollsCount\":1}', 'sven rx-165.png', 5);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (10, 'RX-150', 39.99, 45, 2, '{\"buttonsCount\":2,\"size\":\"small\",\"scrollsCount\":1}', 'rx-150.png', 5);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (11, 'Apex M750 TKL Gaming PUBG Edition', 249.99, 50, 4, '{\"keyCount\":89,\"withLegs\":true, \"hasExtraButtons\":false, \"pressingDepth\":2.5, \"keySwitches\":\"red\", \"pressingStrength\":40}', 'apex m750.png', 4);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (12, 'G613 Wireless Mechanical Gaming', 269.99, 55, 4, '{\"keyCount\":133,\"withLegs\":true, \"hasExtraButtons\":true, \"pressingDepth\":1.5, \"keySwitches\":\"brown\", \"pressingStrength\":52}', 'g613.png', 4);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (13, 'Ornata Chroma', 189.99, 60, 4, '{\"keyCount\":114,\"withLegs\":true, \"hasExtraButtons\":true, \"pressingDepth\":2.0, \"keySwitches\":\"red\", \"pressingStrength\":35}', 'ornata chroma.png', 3);
INSERT INTO `internet_shop`.`input_devices` (`id`, `title`, `price`, `count`, `category`, `parameters`, `photo`, `manufacturer`) VALUES (14, 'KB-S300', 89.99, 65, 3, '{\"keyCount\":115,\"withLegs\":false, \"hasExtraButtons\":false}', 'kb-s300.png', 5);

COMMIT;

