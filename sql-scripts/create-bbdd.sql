--
-- Create the database
--
CREATE DATABASE IF NOT EXISTS `tasklist-manager`;
USE `tasklist-manager`;

--
-- Create the tables
--
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`(
	`id` int(11) auto_increment not null unique,
    `email` varchar(45) not null,
    `password` char(68) not null,
    `first_name` varchar(45) default null,
    `last_name` varchar(45) default null,
    primary key (`email`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task`(
    `id` int(11) auto_increment not null,
    `title` varchar(20) default null,
    `description` varchar(255) default null,
    `status` enum('PENDING', 'ONGOING', 'FINISHED') NOT NULL default 'PENDING',
    `priority` enum('LOW', 'HIGH') NOT NULL default 'LOW',
    `owner_email` varchar(45) NOT NULL,
    `created_at` timestamp default current_timestamp,
	`updated_at` timestamp default current_timestamp on update current_timestamp,
    constraint `user_fk` foreign key (`owner_email`) references `user` (`email`),
    primary key (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Insert data
--
INSERT INTO `user`(email, password, first_name, last_name)  VALUES 
('sabrina@example.com','{bcrypt}$2y$12$7OG19UKFoybDMwbfMwXs9ucLz6seRVR1G5wk2eGHnZ/lljVuQ/AYC', 'Sabrina', 'Fernández Zambramo'),
('laura@example.com','{bcrypt}$2y$12$a.eFwi27zOqYbyvvRB1YU.MguzxnI6QdoPyXiioY5uXUHQx00OyHO', 'Laura', 'Fernández Zambrano');

INSERT INTO `task`(title, description, status, priority, owner_email) VALUES
('Example title', 'Example description', 1, 1, 'sabrina@example.com');