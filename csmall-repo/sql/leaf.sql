
CREATE DATABASE `leafdb` DEFAULT CHARACTER SET utf8mb4;

/*Table structure for table `leaf_alloc` */
USE `leafdb`;
DROP TABLE IF EXISTS `leaf_alloc`;

CREATE TABLE `leaf_alloc` (
  `biz_tag` varchar(128) NOT NULL DEFAULT '' COMMENT '业务key',
  `max_id` bigint NOT NULL DEFAULT '1' COMMENT '当前已经分配了的最大id',
  `step` int NOT NULL COMMENT '初始步长，也是动态调整的最小步长',
  `description` varchar(256) DEFAULT NULL COMMENT '业务key的描述',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据库维护的更新时间',
  PRIMARY KEY (`biz_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `leaf_alloc` */

insert  into `leaf_alloc`(`biz_tag`,`max_id`,`step`,`description`,`update_time`) values
('admin',1000,1000,'测试','2021-12-29 09:43:09'),
('cart',1000,1000,'测试','2022-02-22 14:32:21'),
('order',5000,1000,'测试','2022-02-28 16:28:42'),
('order_item',5000,1000,'测试','2022-02-28 16:28:42'),
('sku',8000,1000,'测试','2022-03-07 17:29:01'),
('spu',7000,1000,'测试','2021-12-13 14:59:56'),
('user',1000,1000,'测试','2022-02-22 14:31:56');