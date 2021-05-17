DROP DATABASE if exists dairyfactoryinventoryservice;
drop user if exists `dairy_factory_inventory_service`@`%`;
create database if not exists dairyfactoryinventoryservice character set utf8mb4 collate
  utf8mb4_unicode_ci;
create user if not exists `dairy_factory_inventory_service`@`%` IDENTIFIED with mysql_native_password
  by 'password';
grant select, insert, update, delete, create, drop, references, index, alter, execute, CREATE,
  create view, show view, create routine, alter routine, event, trigger on
  `dairyfactoryinventoryservice`.* to
  `dairy_factory_inventory_service`@`%`;
flush privileges;