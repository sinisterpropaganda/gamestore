CREATE TABLE `gamestore_catlog`.`document` (
  `document_id` INT NOT NULL AUTO_INCREMENT,
  `status` TINYINT(4) NOT NULL,
  `url` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`document_id`));

CREATE TABLE `gamestore_catlog`.`game` (
  `game_id` INT NOT NULL AUTO_INCREMENT,
  `publisher` VARCHAR(45) NOT NULL,
  `release_date` DATETIME NOT NULL,
  `mrp` DECIMAL(10,2) NOT NULL,
  `discount_percent` INT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `playable_on` TINYINT(4) NULL,
  `genre` TINYINT(4) NULL,
  `file_size` FLOAT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `times_bought` INT NULL,
  `icon_id` INT NULL,
  PRIMARY KEY (`game_id`));

ALTER TABLE `gamestore_catlog`.`game` 
ADD INDEX `fk_game_document_document_id_idx` (`icon_id` ASC);
ALTER TABLE `gamestore_catlog`.`game` 
ADD CONSTRAINT `fk_game_document_document_id`
  FOREIGN KEY (`icon_id`)
  REFERENCES `gamestore_catlog`.`document` (`document_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


CREATE TABLE `gamestore_catlog`.`feature_details` (
  `type` TINYINT(4) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `image_id` INT NOT NULL,
  PRIMARY KEY (`type`),
  INDEX `fk_feature_details_document_document_id_idx` (`image_id` ASC),
  CONSTRAINT `fk_feature_details_document_document_id`
    FOREIGN KEY (`image_id`)
    REFERENCES `gamestore_catlog`.`document` (`document_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `gamestore_catlog`.`featured` (
  `game_id` INT NOT NULL,
  `type` TINYINT(4) NOT NULL,
  PRIMARY KEY (`game_id`, `type`),
  INDEX `fk_featured_feature_details_type_idx` (`type` ASC),
  CONSTRAINT `fk_featured_feature_details_type`
    FOREIGN KEY (`type`)
    REFERENCES `gamestore_catlog`.`feature_details` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `gamestore_catlog`.`screenshot` (
  `game_id` INT NOT NULL,
  `image_id` INT NOT NULL,
  PRIMARY KEY (`game_id`, `image_id`),
  INDEX `fk_screenshot_document_document_id_idx` (`image_id` ASC),
  CONSTRAINT `fk_screenshot_game_game_id`
    FOREIGN KEY (`game_id`)
    REFERENCES `gamestore_catlog`.`game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_screenshot_document_document_id`
    FOREIGN KEY (`image_id`)
    REFERENCES `gamestore_catlog`.`document` (`document_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `gamestore_catlog`.`game_desc` (
  `game_id` INT NOT NULL,
  `description` LONGTEXT NULL,
  PRIMARY KEY (`game_id`),
  CONSTRAINT `fk_game_desc_game_game_id`
    FOREIGN KEY (`game_id`)
    REFERENCES `gamestore_catlog`.`game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

