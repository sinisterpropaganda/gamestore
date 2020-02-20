ALTER TABLE user CHANGE COLUMN role user_role tinyint(4) NOT NULL;

RENAME TABLE user TO gamestore_user;
