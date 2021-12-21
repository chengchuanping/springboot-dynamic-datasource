
# Springboot-Dynamic-Datasource

SpringBoot动态数据源，启动后。先通过`/api/datasource` 添加数据源（数据库需要先创建，并且初始数据表），也可以删除添加的数据库。

然后可以通过 `/api/user` 接口进行测试。

## 数据表SQL

```sql
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `balance` decimal(10,2) DEFAULT NULL COMMENT '账户余额',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `enabled` tinyint unsigned NOT NULL COMMENT '是否启用。0：禁用，1：启用',
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名字',
  `update_at` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户';
```

## 讨论

如果有问题，可以在此帖参与讨论

[https://springboot.io/t/topic/4660](https://springboot.io/t/topic/4660)
