CREATE USER 'gamestore'@'localhost' IDENTIFIED BY 'gamestore';
CREATE DATABASE gamestore_catlog;
CREATE DATABASE gamestore_users;

GRANT ALL PRIVILEGES ON gamestore_users.* TO 'gamestore'@'localhost';
GRANT ALL PRIVILEGES ON gamestore_catlog.* TO 'gamestore'@'localhost';
