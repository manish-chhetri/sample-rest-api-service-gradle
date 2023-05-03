
## Create database schema`hinge_heath` 
CREATE DATABASE `hinge_heath`;

## Create table`tree_data` in above DB schema

DROP TABLE IF EXISTS `tree_data`;

CREATE TABLE `tree_data` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`label` varchar(45) DEFAULT NULL,
`parent` bigint(20) NOT NULL,
`status` int(11) NOT NULL DEFAULT 1,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

## Insert Data in Table`tree_data`

USE `hinge_health`

INSERT INTO `tree_data` VALUES 
(1,'root',0,1),
(2,'ant',1,1),
(3,'bear',1,1),
(4,'cat',3,1),
(5,'dog',3,1),
(6,'elephant',5,1),
(7,'frog',1,1);

## Swagger UI URL
http://`<host>:<port>`/`<origin if any>`/swagger-ui.html

e.g. http://localhost:8080/swagger-ui.html

## Swagger API URL
http://`<host>:<port>`/`<origin if any>`/v2/api-docs

e.g. http://localhost:8080/v2/api-docs
