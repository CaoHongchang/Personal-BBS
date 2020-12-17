DROP DATABASE IF EXISTS ssm;
CREATE DATABASE ssm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ssm;
CREATE TABLE Todo
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `content`   VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

#建表语句:topic表
CREATE TABLE `ssm`.`topic` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NOT NULL,
    `content` VARCHAR(1000) NOT NULL,
    `userId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `ssm`.`topic` (`id`, `title`, `content`, `userId`) VALUES ('1', 'title1', 'content1', '-1');

#   建立表语句:user表
CREATE TABLE `ssm`.`user` (
                              `id` INT NOT NULL AUTO_INCREMENT,
                              `username` VARCHAR(45) NOT NULL,
                              `password` VARCHAR(45) NOT NULL,
                              `role` ENUM('admin', 'normal', 'guest') NOT NULL,
                              PRIMARY KEY (`id`));

#   建立表语句:topiccomment
CREATE TABLE `ssm`.`topiccomment` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `content` VARCHAR(300) NOT NULL,
                                      `userId` INT NOT NULL,
                                      `topicId` INT NULL,
                                      PRIMARY KEY (`id`));

#   建表语句:Comment
CREATE TABLE `ssm`.`comment` (
                                 `id` INT NOT NULL AUTO_INCREMENT,
                                 `cotent` VARCHAR(300) NOT NULL,
                                 `weiboId` INT NOT NULL,
                                 PRIMARY KEY (`id`));
