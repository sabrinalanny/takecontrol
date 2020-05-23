# Take Control

## ReactJS + SpringBoot

### GET
	http://localhost:8080/rest/tc/income

### GET By ID
	http://localhost:8080/rest/tc/income/1

# POST
	http://localhost:8080/rest/tc/income

# PUT
	http://localhost:8080/rest/tc/income

# DELETE
	http://localhost:8080/rest/tc/income/1



## BD MySQL
``` bash
CREATE DATABASE `takecontrol`;
USE `takecontrol`;
```

``` bash
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `type` varchar(255)  DEFAULT NULL,
  PRIMARY KEY (`id`)
);
```

``` bash
CREATE TABLE `income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  `day` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `recurrent` bit(1) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKincomecategory` (`category_id`)
);
```

``` bash
CREATE TABLE `spending` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  `day` bigint(20) NOT NULL DEFAULT '1',
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `recurrent` bit(1) NOT NULL DEFAULT b'0',
  `start_date` datetime DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKspendingcategory` (`category_id`)
);
```