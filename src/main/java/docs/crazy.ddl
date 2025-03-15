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

CREATE TABLE `api_management`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`       varchar(100) NOT NULL COMMENT '租户',
    `project_id`      bigint(20) NOT NULL COMMENT '项目Id',
    `application_id`  bigint(20) NOT NULL COMMENT '应用id',
    `name`            varchar(255) NOT NULL COMMENT '接口名',
    `method`          varchar(100) NOT NULL COMMENT '请求方法',
    `protocal`        varchar(100) DEFAULT 'HTTP' COMMENT '接口协议',
    `path`            text         NOT NULL COMMENT '接口路径',
    `content_type`    varchar(100) DEFAULT NULL COMMENT '参数类型',
    `request_headers` text COMMENT '请求头',
    `request_params`  text COMMENT '请求参数',
    `response`        text COMMENT '响应结果',
    `time_out`        int(11) DEFAULT NULL COMMENT '超时时间（秒）',
    `priority`        int(11) DEFAULT NULL COMMENT '优先级',
    `can_prod_exec`   int(11) DEFAULT '0' COMMENT '是否可生产执行：0否 1是',
    `api_type`        varchar(100) DEFAULT NULL COMMENT '接口类型',
    `owner`           bigint(20) DEFAULT NULL COMMENT '负责人',
    `case_count`      int(11) NOT NULL DEFAULT '0' COMMENT '用例数',
    `creator_id`      bigint(20) DEFAULT NULL COMMENT '创建者id',
    `mender_id`       bigint(20) DEFAULT NULL COMMENT '修改者id',
    `invoke_times`    int(11) DEFAULT NULL COMMENT '最近30天调用次数',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `modify_time`     datetime     NOT NULL COMMENT '修改时间',
    `is_delete`       int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    `remark`          text COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY               `api_manage_id_IDX` (`id`,`tenant_id`,`project_id`,`application_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8588 DEFAULT CHARSET=utf8 COMMENT='接口管理表'


CREATE TABLE `api_case`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`          varchar(100) NOT NULL COMMENT '租户',
    `project_id`         bigint(20) NOT NULL COMMENT '项目id',
    `tree_node_key`      varchar(100) NOT NULL DEFAULT '0' COMMENT '树节点key',
    `name`               varchar(255) NOT NULL COMMENT '用例名',
    `priority`           int(11) DEFAULT NULL COMMENT '优先级',
    `is_sub_process`     int(11) NOT NULL DEFAULT '0' COMMENT '是否是子流程',
    `input_params`       text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用例入参',
    `output_params`      text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用例出参',
    `nodes`              text COMMENT '节点',
    `edges`              text COMMENT '边',
    `owner_id`           bigint(20) DEFAULT NULL COMMENT '负责人id',
    `creator_id`         bigint(20) NOT NULL COMMENT '创建者ID',
    `mender_id`          bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `create_time`        datetime     NOT NULL COMMENT '创建时间',
    `modify_time`        datetime     NOT NULL COMMENT '修改时间',
    `is_delete`          int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    `remark`             text COMMENT '备注',
    `recent_exec_result` varchar(100)          DEFAULT NULL COMMENT '最近一次执行结果',
    `recent_exec_time`   datetime              DEFAULT NULL COMMENT '最近一次执行时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2006 DEFAULT CHARSET=utf8 COMMENT='测试用例'
