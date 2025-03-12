CREATE TABLE `user`
(
    `id`             int(11) NOT NULL,
    `tenant_id`      varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '租户id',
    `account`        varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '账号',
    `name`           varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '姓名',
    `password`       varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `email`          varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
    `phone`          bigint(20) DEFAULT NULL,
    `select_project` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '选择 项目id',
    `role_id`        int(20) DEFAULT NULL COMMENT '角色id',
    `status`         int(2) DEFAULT NULL COMMENT '状态 0启用 1停用',
    `is_delete`      int(2) DEFAULT NULL COMMENT '删除 0未删除 1删除',
    `create_by_id`   int(11) DEFAULT NULL COMMENT '创建人id',
    `create_by_name` varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '创建人姓名',
    `create_time`    timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `update_by_id`   int(11) DEFAULT NULL COMMENT '修改人id',
    `update_by_name` varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '修改人姓名',
    `update_time`    timestamp NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表'