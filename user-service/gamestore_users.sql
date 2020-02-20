CREATE DATABASE IF NOT EXISTS `gamestore_users`;

CREATE TABLE `gamestore_users`.`document` (
    `document_id` INT NOT NULL AUTO_INCREMENT,
    `status` TINYINT NOT NULL,
    `url` VARCHAR(1000) NOT NULL,
    PRIMARY KEY (`document_id`)
);

ALTER TABLE `gamestore_users`.`document` 
ADD COLUMN `create_date` DATETIME NOT NULL AFTER `url`,
ADD COLUMN `update_date` DATETIME NOT NULL AFTER `create_date`;

CREATE TABLE `gamestore_users`.`user` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(250) NOT NULL,
    `profile_image_id` INT NULL,
    `balance` DECIMAL(10,2) NOT NULL,
    `role_id` TINYINT(4) NOT NULL,
    `create_date` DATETIME NOT NULL,
    `update_date` DATETIME NOT NULL,
    PRIMARY KEY (`user_id` , `username`),
    INDEX `fk_user_1_idx` (`profile_image_id` ASC),
    CONSTRAINT `fk_user_1` FOREIGN KEY (`profile_image_id`)
        REFERENCES `gamestore_users`.`document` (`document_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);


ALTER TABLE `gamestore_users`.`user` ADD UNIQUE KEY(`username`);


CREATE TABLE `gamestore_users`.`wishlist` (
    `game_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `add_date` DATETIME NOT NULL,
    PRIMARY KEY (`game_id` , `user_id`),
    CONSTRAINT `fk_wishlist_user_user_id` FOREIGN KEY (`user_id`)
        REFERENCES `gamestore_users`.`user` (`user_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `gamestore_users`.`bucket` (
    `game_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `add_date` DATETIME NOT NULL,
    PRIMARY KEY (`game_id` , `user_id`),
    CONSTRAINT `fk_bucket_user_user_id` FOREIGN KEY (`user_id`)
        REFERENCES `gamestore_users`.`user` (`user_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);




