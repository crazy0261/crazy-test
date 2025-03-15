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


CREATE TABLE `api_manage`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`       varchar(100) NOT NULL COMMENT '租户',
    `project_id`      bigint(20) NOT NULL COMMENT '项目Id',
    `application_id`  bigint(20) NOT NULL COMMENT '应用id',
    `name`            varchar(255) NOT NULL COMMENT '接口名',
    `method`          varchar(100) NOT NULL COMMENT '请求方法',
    `protocal`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'HTTP' COMMENT '接口协议',
    `path`            text         NOT NULL COMMENT '接口路径',
    `content_type`    varchar(100)                                            DEFAULT NULL COMMENT '参数类型',
    `request_headers` text COMMENT '请求头',
    `request_params`  text COMMENT '请求参数',
    `response`        text COMMENT '响应结果',
    `time_out`        int(11) DEFAULT NULL COMMENT '超时时间（秒）',
    `priority`        int(11) DEFAULT NULL COMMENT '优先级',
    `can_prod_exec`   int(11) DEFAULT '0' COMMENT '是否可生产执行：0否 1是',
    `api_type`        varchar(100)                                            DEFAULT NULL COMMENT '接口类型',
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


CREATE TABLE `api_testcase`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`          varchar(100) NOT NULL COMMENT '租户',
    `project_id`         bigint(20) NOT NULL COMMENT '项目id',
    `app_id`             bigint(20) NOT NULL COMMENT '应用id',
    `name`               varchar(255) NOT NULL COMMENT '用例名',
    `api_id`             bigint(20) NOT NULL COMMENT '关联的接口id',
    `request_headers`    json         DEFAULT NULL COMMENT '请求头',
    `request_params`     json         DEFAULT NULL COMMENT '请求参数',
    `asserts`            text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '断言',
    `env_variables`      text COMMENT '用例变量，优先级高于环境变量',
    `priority`           int(11) DEFAULT NULL COMMENT '优先级',
    `owner_id`           bigint(20) DEFAULT NULL COMMENT '负责人id',
    `creator`            bigint(20) NOT NULL COMMENT '创建者id',
    `mender_id`          bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `is_delete`          bigint(20) NOT NULL DEFAULT '0' COMMENT '是否删除',
    `secret_id`          int(11) DEFAULT NULL COMMENT '加密参数id',
    `remark`             text COMMENT '备注',
    `recent_exec_result` varchar(100) DEFAULT NULL COMMENT '最近一次执行结果',
    `recent_exec_time`   datetime     DEFAULT NULL COMMENT '最近一次执行时间',
    `create_time`        datetime     NOT NULL COMMENT '创建时间',
    `modify_time`        datetime     NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY                  `id_tenantid_projectid` (`id`,`tenant_id`,`project_id`) USING BTREE,
    KEY                  `id_tenantid` (`id`,`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3175 DEFAULT CHARSET=utf8 COMMENT='测试用例'


CREATE TABLE `api_testcase_result`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `tenant_id`         varchar(50) NOT NULL DEFAULT '0' COMMENT '租户',
    `project_id`        varchar(50) NOT NULL DEFAULT '0' COMMENT '项目id',
    `schedule_id`       bigint(20) DEFAULT NULL COMMENT '定时任务id',
    `schedule_batch_id` bigint(20) DEFAULT NULL COMMENT '定时任务批次id',
    `api_testcase_id`   varchar(20)          DEFAULT NULL COMMENT '用例id',
    `env_name_id`       varchar(20)          DEFAULT NULL COMMENT '环境名称id',
    `mode`              varchar(20)          DEFAULT NULL COMMENT '执行模式,schedule',
    `status`            varchar(20)          DEFAULT NULL COMMENT '执行状态,INIT,RUNNING,SUCCESS,FAIL',
    `exec_times`        tinyint(4) DEFAULT '1' COMMENT '执行次数',
    `debug_result`      longtext,
    `creator_name`      varchar(20) NOT NULL DEFAULT '' COMMENT '创建人名称',
    `case_creator_id`   bigint(20) DEFAULT NULL COMMENT '用例创建者id',
    `case_owner_id`     bigint(20) DEFAULT NULL COMMENT '用例负责人ID',
    `create_time`       datetime             DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                 `api_testcase_result_tenant_id_IDX2` (`tenant_id`,`schedule_batch_id`) USING BTREE,
    KEY                 `api_testcase_result_tenant_id_IDX3` (`tenant_id`,`schedule_id`,`schedule_batch_id`,`api_testcase_id`,`env_name_id`,`exec_times`) USING BTREE,
    KEY                 `idx_status_envnameid_createtime` (`status`,`env_name_id`,`create_time`),
    KEY                 `tenant_id_api_testcase_id_create_time` (`tenant_id`,`api_testcase_id`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=8440292 DEFAULT CHARSET=utf8 COMMENT='单接口执行结果'

CREATE TABLE `application`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`   varchar(100) NOT NULL COMMENT '租户',
    `project_id`  bigint(20) NOT NULL COMMENT '项目id',
    `name`        varchar(255) NOT NULL COMMENT '应用名',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '应用描述',
    `creator`     bigint(20) NOT NULL COMMENT '创建者id',
    `mender_id`   bigint(20) DEFAULT NULL COMMENT '修改者id',
    `owner_id`    bigint(20) DEFAULT NULL COMMENT '负责人',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `modify_time` datetime     NOT NULL COMMENT '更新时间',
    `is_delete`   int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY           `application_id_IDX` (`id`,`tenant_id`,`project_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8 COMMENT='应用管理'

CREATE TABLE `ci_batch_record`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `app_name`      varchar(100) NOT NULL COMMENT '应用名',
    `env_name_id`   bigint(20) NOT NULL COMMENT '环境id',
    `batch_id`      bigint(20) NOT NULL COMMENT '批次号',
    `status`        varchar(100) NOT NULL COMMENT '批次执行状态',
    `result`        text COMMENT '结果详情',
    `pipeline_id`   varchar(100) DEFAULT NULL COMMENT '云效流水线的pipeline_id',
    `build_number`  varchar(100) DEFAULT NULL COMMENT '云效流水线构建的build_number',
    `commit_ids`    text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'commit_id列表',
    `webhook`       varchar(100) DEFAULT NULL COMMENT '机器人webhook',
    `executor_name` varchar(100) DEFAULT NULL COMMENT '执行流水线者',
    `create_time`   datetime     NOT NULL COMMENT '创建时间',
    `modify_time`   datetime     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY             `ci_batch_record_batch_id_IDX` (`batch_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6570 DEFAULT CHARSET=utf8 COMMENT='ci执行批次记录'

CREATE TABLE `daily_data`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `tenant_id`         varchar(100) NOT NULL COMMENT '租户',
    `project_id`        bigint(20) NOT NULL COMMENT '项目id',
    `date`              date         NOT NULL COMMENT '日期',
    `api_case_num`      int(11) NOT NULL DEFAULT '0' COMMENT '单接口用例数',
    `mul_case_num`      int(11) DEFAULT '0' COMMENT '多接口用例数',
    `case_success_rate` double DEFAULT NULL COMMENT '用例成功率',
    `create_time`       datetime     NOT NULL COMMENT '创建时间',
    `modify_time`       datetime     NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY                 `daily_data_id_IDX` (`id`,`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=656 DEFAULT CHARSET=utf8 COMMENT='每日数据'

CREATE TABLE `data_pool_data`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `rule_id`      bigint(20) NOT NULL COMMENT '规则id',
    `env_name_id`  bigint(20) NOT NULL COMMENT '环境名id',
    `data_entity`  text     NOT NULL COMMENT '数据体',
    `is_occupy`    int(11) NOT NULL COMMENT '是否被占用',
    `occupier_id`  bigint(20) DEFAULT NULL COMMENT '占用者',
    `occupy_time`  datetime  DEFAULT NULL COMMENT '占用时间',
    `occupy_times` int(11) NOT NULL DEFAULT '0' COMMENT '被占用次数',
    `remark`       char(255) DEFAULT NULL COMMENT '备注',
    `creator_id`   bigint(20) NOT NULL COMMENT '创建者',
    `create_time`  datetime NOT NULL COMMENT '创建时间',
    `is_delete`    int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY            `data_pool_data_rule_id_IDX` (`rule_id`,`is_occupy`,`env_name_id`,`occupier_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2445 DEFAULT CHARSET=utf8 COMMENT='规则数据表'

CREATE TABLE `data_pool_rule`
(
    `id`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`              varchar(255) NOT NULL COMMENT '规则名称',
    `env_name_id`       bigint(20) NOT NULL COMMENT '环境名id',
    `mul_testcase_id`   bigint(20) DEFAULT NULL COMMENT '场景用例id',
    `data_format`       text COMMENT '数据体格式',
    `total_data_count`  int(11) NOT NULL COMMENT '总数据量',
    `occupy_data_count` int(11) NOT NULL COMMENT '被占用数据量',
    `vacant_data_count` int(11) NOT NULL COMMENT '空闲数据量',
    `apply_times`       int(11) NOT NULL COMMENT '申领次数',
    `limit_count`       int(11) DEFAULT NULL COMMENT '每人限制数量',
    `creator_id`        bigint(20) NOT NULL COMMENT '创建者',
    `mender_id`         bigint(20) DEFAULT NULL COMMENT '修改者',
    `create_time`       datetime     NOT NULL COMMENT '创建时间',
    `modify_time`       datetime DEFAULT NULL COMMENT '修改时间',
    `is_delete`         int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='规则表'

CREATE TABLE `data_source`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `tenant_id`   varchar(100) NOT NULL COMMENT '租户',
    `project_id`  bigint(20) NOT NULL COMMENT '项目ID',
    `name`        varchar(100) NOT NULL COMMENT '名称',
    `config`      text COMMENT '配置',
    `creator_id`  bigint(20) NOT NULL COMMENT '创建者ID',
    `mender_id`   bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `is_delete`   int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY           `data_source_config_id_IDX` (`id`,`tenant_id`,`project_id`,`name`,`creator_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='数据源配置'

CREATE TABLE `domain_info`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`   varchar(100) NOT NULL COMMENT '租户',
    `name`        varchar(100) NOT NULL COMMENT '域名名称',
    `domain`      varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '域名，或ip:端口号',
    `creator_id`  bigint(20) NOT NULL COMMENT '创建人id',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `modifier_id` bigint(20) DEFAULT '0' COMMENT '修改人id',
    `modify_time` datetime                                                DEFAULT NULL COMMENT '修改时间',
    `is_delete`   bigint(20) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='域名信息表'

CREATE TABLE `env_info`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`       varchar(100) NOT NULL COMMENT '租户',
    `app_id`          varchar(100) NOT NULL COMMENT '应用id',
    `app_name`        varchar(100) NOT NULL COMMENT '应用名',
    `env_name_id`     bigint(20) NOT NULL COMMENT 'env_name.id',
    `name`            varchar(100) NOT NULL COMMENT '环境名',
    `domain_id`       bigint(20) DEFAULT NULL COMMENT '域名id',
    `request_headers` text COMMENT '请求头',
    `env_variables`   text COMMENT '环境变量',
    `mender_id`       bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `modify_time`     datetime     NOT NULL COMMENT '修改时间',
    `is_delete`       int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY               `env_info_id_IDX` (`id`,`tenant_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=389 DEFAULT CHARSET=utf8 COMMENT='环境信息表'

CREATE TABLE `env_name`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`   varchar(100)                                            NOT NULL COMMENT '租户',
    `name`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '环境名称',
    `project_id`  bigint(20) DEFAULT NULL COMMENT '项目Id',
    `default_env` bigint(20) DEFAULT NULL COMMENT '项目的默认env_info.id',
    `create_time` datetime                                                NOT NULL COMMENT '创建时间',
    `modify_time` datetime                                                NOT NULL COMMENT '修改时间',
    `is_delete`   int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8

CREATE TABLE `mul_testcase`
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

CREATE TABLE `mul_testcase_node`
(
    `id`              varchar(100) NOT NULL COMMENT 'id',
    `tenant_id`       varchar(100) NOT NULL COMMENT '租户',
    `project_id`      bigint(20) NOT NULL COMMENT '项目id',
    `testcase_id`     bigint(20) NOT NULL COMMENT '用例id',
    `app_id`          bigint(20) DEFAULT NULL COMMENT '应用id',
    `api_id`          bigint(20) DEFAULT NULL COMMENT '接口id',
    `request_headers` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求头',
    `request_params`  text COMMENT '请求参数',
    `assert_list`     text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '断言',
    `input_params`    text COMMENT '入参（目前只有子流程用到）',
    `output_params`   text COMMENT '节点出参',
    `sub_case_id`     bigint(20) DEFAULT NULL COMMENT '子流程用例id',
    `groovy_key`      varchar(255) DEFAULT NULL COMMENT '条件节点和前置步骤输出参数的key',
    `groovy_script`   text COMMENT 'groovy脚本(仅条件节点用到)',
    `secret_id`       int(11) DEFAULT NULL COMMENT '加密参数id',
    `data_source_id`  int(11) DEFAULT NULL COMMENT '数据源ID(仅SQL节点用到)',
    `sql_script`      text COMMENT 'SQL脚本(仅SQL节点用到)',
    `is_delete`       int(11) NOT NULL COMMENT '是否删除',
    `creator_id`      bigint(20) DEFAULT NULL COMMENT '创建者ID',
    `mender_id`       bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `modify_time`     datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY               `mul_testcase_node_id_IDX` (`id`,`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试用例详情'

CREATE TABLE `mul_testcase_result`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`         varchar(100) NOT NULL COMMENT '租户',
    `project_id`        bigint(20) NOT NULL COMMENT '项目id',
    `schedule_id`       bigint(20) DEFAULT NULL COMMENT '定时任务Id',
    `schedule_batch_id` bigint(20) DEFAULT NULL COMMENT '定时任务批次Id',
    `testcase_id`       bigint(20) NOT NULL COMMENT '用例id',
    `nodes`             text         NOT NULL COMMENT '节点',
    `edges`             text         NOT NULL COMMENT '边',
    `env_name_id`       bigint(20) NOT NULL COMMENT '执行环境id',
    `exec_times`        int(11) NOT NULL COMMENT '执行次数',
    `status`            varchar(100) NOT NULL COMMENT '状态',
    `input_params`      text COMMENT '用例入参',
    `output_params`     text COMMENT '用例出参',
    `mode`              varchar(100) NOT NULL COMMENT '模式',
    `creator_id`        bigint(20) NOT NULL COMMENT '创建者id',
    `case_creator_id`   bigint(20) DEFAULT NULL COMMENT '用例创建者ID',
    `case_owner_id`     bigint(20) DEFAULT NULL COMMENT '用例负责人ID',
    `create_time`       datetime     NOT NULL COMMENT '创建时间',
    `modify_time`       datetime     NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY                 `mul_testcase_result_id_IDX` (`id`,`tenant_id`) USING BTREE,
    KEY                 `mul_testcase_result_id_IDX2` (`id`,`tenant_id`,`project_id`,`schedule_id`,`schedule_batch_id`,`testcase_id`) USING BTREE,
    KEY                 `mul_testcase_result_tenant_id_IDX` (`tenant_id`,`testcase_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1741877100029978 DEFAULT CHARSET=utf8 COMMENT='多接口用例执行结果'

CREATE TABLE `mul_testcase_result_node`
(
    `id`                       bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`                varchar(100)                                            NOT NULL COMMENT '租户',
    `project_id`               bigint(20) NOT NULL COMMENT '项目id',
    `testcase_id`              bigint(20) NOT NULL COMMENT '用例id',
    `testcase_result_id`       bigint(20) NOT NULL COMMENT '用例结果id',
    `node_id`                  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当前节点id',
    `next_node_id`             varchar(100) DEFAULT NULL COMMENT '下一节点id',
    `debug_result`             longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用例节点执行结果',
    `sql_exec_result`          longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'SQL节点执行结果',
    `pre_step_exec_result`     longtext COMMENT '前置步骤执行结果',
    `status`                   varchar(100)                                            NOT NULL COMMENT '状态',
    `sub_process_input_params` text COMMENT '子流程入参',
    `output_params`            text COMMENT '节点出参',
    `sub_result_id`            bigint(20) DEFAULT NULL COMMENT '子流程结果id',
    `create_time`              datetime                                                NOT NULL COMMENT '创建时间',
    `modify_time`              datetime                                                NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY                        `mul_testcase_result_node_id_IDX` (`id`,`tenant_id`) USING BTREE,
    KEY                        `mul_testcase_result_node_tenant_id_IDX` (`tenant_id`,`testcase_result_id`,`node_id`),
    KEY                        `tenant_id_testcase_id_node_id_create_time` (`tenant_id`,`testcase_id`,`node_id`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1762369479539965753 DEFAULT CHARSET=utf8 COMMENT='多接口用例执行详情'

CREATE TABLE `project`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`   varchar(100)                                            NOT NULL COMMENT '租户',
    `name`        varchar(100)                                            NOT NULL COMMENT '项目名称',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目描述',
    `creator`     varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者',
    `create_time` datetime                                                NOT NULL COMMENT '创建时间',
    `modify_time` datetime                                                NOT NULL COMMENT '修改时间',
    `is_delete`   int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY           `project_id_IDX` (`id`,`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='项目管理'

CREATE TABLE `schedule_record`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `tenant_id`         varchar(50) NOT NULL                                   DEFAULT '0' COMMENT '租户',
    `project_id`        bigint(20) NOT NULL COMMENT '项目id',
    `schedule_id`       bigint(20) DEFAULT NULL COMMENT '定时任务id',
    `schedule_batch_id` bigint(20) DEFAULT NULL COMMENT '定时任务批次id',
    `env_id`            varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '环境id',
    `status`            varchar(20)                                            DEFAULT NULL COMMENT '执行状态',
    `testcase_type`     varchar(255)                                           DEFAULT NULL COMMENT '用例类型',
    `testcase_list`     text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例列表',
    `creator_id`        bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人id',
    `create_time`       datetime                                               DEFAULT NULL COMMENT '创建时间',
    `modify_time`       datetime                                               DEFAULT NULL COMMENT '修改时间',
    `ip`                varchar(100)                                           DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=765864 DEFAULT CHARSET=utf8 COMMENT='定时任务执行记录表'


CREATE TABLE `secret_manage`
(
    `id`           int(11) unsigned NOT NULL AUTO_INCREMENT,
    `tenant_id`    varchar(100) NOT NULL COMMENT '租户',
    `project_id`   bigint(20) NOT NULL COMMENT '项目id',
    `name`         varchar(256) NOT NULL COMMENT '名称',
    `secret_param` text COMMENT '加密参数',
    `creator_id`   bigint(20) NOT NULL COMMENT '创建者id',
    `modifier_id`  bigint(20) DEFAULT NULL COMMENT '修改者id',
    `is_delete`    int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time`  datetime     NOT NULL COMMENT '创建时间',
    `modify_time`  datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY            `secret_manage_id_IDX` (`id`,`tenant_id`,`project_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='加密参数管理'

CREATE TABLE `sys_schedule`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `tenant_id`     varchar(50)  NOT NULL                                  DEFAULT '0' COMMENT '租户',
    `project_id`    varchar(50)  NOT NULL                                  DEFAULT '0' COMMENT '项目id',
    `env`           varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '环境变量',
    `exec_mode`     char(20)                                               DEFAULT 'serial' COMMENT '执行模式',
    `name`          varchar(50)  NOT NULL                                  DEFAULT '0' COMMENT '定时任务名称',
    `remark`        varchar(100) NOT NULL                                  DEFAULT '' COMMENT '定时任务描述',
    `cron`          varchar(50)  NOT NULL                                  DEFAULT '0' COMMENT 'cron表达式',
    `enable`        tinyint(4) DEFAULT '-1' COMMENT '状态，1启用，-1禁用',
    `status`        varchar(20)                                            DEFAULT NULL COMMENT '执行状态',
    `testcase_type` varchar(255)                                           DEFAULT NULL COMMENT '用例类型：接口用例、场景用例',
    `testcase_list` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例列表',
    `creator_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人id',
    `create_time`   datetime                                               DEFAULT NULL COMMENT '创建时间',
    `modifier_id`   bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改人id',
    `modify_time`   datetime                                               DEFAULT NULL COMMENT '修改时间',
    `owner_id`      bigint(20) DEFAULT '0',
    `deleted`       bigint(20) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8 COMMENT='定时任务'


CREATE TABLE `test_account`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`        varchar(100) NOT NULL COMMENT '租户',
    `project_id`       bigint(20) NOT NULL COMMENT '项目id',
    `name`             varchar(255) NOT NULL COMMENT '名称',
    `account`          varchar(100) NOT NULL COMMENT '测试账号',
    `password`         varchar(100) NOT NULL COMMENT '密码',
    `login_path`       varchar(255) NOT NULL COMMENT '登录地址',
    `token`            text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'token',
    `gen_token_status` varchar(100) DEFAULT NULL COMMENT '生成token状态',
    `fail_reason`      text COMMENT '失败原因',
    `creator_id`       bigint(20) DEFAULT NULL COMMENT '创建者ID',
    `mender_id`        bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `create_time`      datetime     NOT NULL COMMENT '创建时间',
    `modify_time`      datetime     NOT NULL COMMENT '修改时间',
    `is_delete`        int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY                `test_account_id_IDX` (`id`,`tenant_id`,`project_id`,`account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='测试账号表'


CREATE TABLE `tree_manage`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `tenant_id`   varchar(100) NOT NULL COMMENT '租户',
    `project_id`  bigint(20) NOT NULL COMMENT '项目id',
    `biz_type`    varchar(100) NOT NULL COMMENT '业务类型',
    `tree_data`   text COMMENT '树详情',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `modify_time` datetime     NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY           `tree_manage_id_IDX` (`id`,`tenant_id`,`project_id`,`biz_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='树结构管理'
