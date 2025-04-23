CREATE TABLE `api_case`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`      bigint(20) DEFAULT NULL COMMENT '项目id',
    `app_id`          bigint(20) NOT NULL COMMENT '应用id',
    `name`            varchar(255) NOT NULL COMMENT '用例名',
    `api_id`          bigint(20) NOT NULL COMMENT '关联的接口id',
    `request_headers` json         DEFAULT NULL COMMENT '请求头',
    `request_params`  json         DEFAULT NULL COMMENT '请求参数',
    `asserts`         text COMMENT '断言',
    `env_variables`   text COMMENT '用例变量，优先级高于环境变量',
    `priority`        int(11) DEFAULT NULL COMMENT '优先级',
    `owner_id`        bigint(20) DEFAULT NULL COMMENT '负责人id',
    `secret_id`       int(11) DEFAULT NULL COMMENT '加密参数id',
    `status`          int(11) DEFAULT '0' COMMENT '0 启用 1停用',
    `remark`          text COMMENT '备注',
    `create_by_id`    bigint(20) NOT NULL COMMENT '创建者id',
    `create_by_name`  varchar(100) DEFAULT NULL COMMENT '创建人',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `update_by_id`    bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `update_by_name`  varchar(100) DEFAULT NULL COMMENT '修改人',
    `update_time`     datetime     NOT NULL COMMENT '修改时间',
    `is_delete`       bigint(20) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3184 DEFAULT CHARSET=utf8 COMMENT='测试用例';

CREATE TABLE `api_case_record`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `project_id`        bigint(20) NOT NULL DEFAULT '0' COMMENT '项目id',
    `schedule_id`       bigint(20) DEFAULT NULL COMMENT '定时任务id',
    `schedule_batch_id` bigint(50) DEFAULT NULL COMMENT '定时任务批次id',
    `api_testcase_id`   bigint(100) DEFAULT NULL COMMENT '用例id',
    `case_owner_id`     bigint(20) DEFAULT NULL COMMENT '用例负责人ID',
    `env_id`            bigint(20) DEFAULT NULL COMMENT '环境名称id',
    `mode`              varchar(20)           DEFAULT NULL COMMENT '执行模式：自动 auto/手动 manua',
    `status`            varchar(20)           DEFAULT NULL COMMENT '执行状态,INIT,RUNNING,SUCCESS,FAIL',
    `exec_times`        tinyint(4) DEFAULT '1' COMMENT '执行次数',
    `debug_result`      longtext COMMENT '执行结果',
    `create_by_id`      bigint(20) DEFAULT NULL COMMENT '创建者id',
    `create_by_name`    varchar(100) NOT NULL DEFAULT '' COMMENT '创建人名称',
    `create_time`       datetime              DEFAULT NULL COMMENT '创建时间',
    `update_by_id`      bigint(20) DEFAULT NULL COMMENT '修改者id',
    `update_by_name`    varchar(100) NOT NULL DEFAULT '' COMMENT '修改者名称',
    `update_time`       datetime              DEFAULT NULL COMMENT '修改时间',
    `is_delete`         int(5) DEFAULT '0' COMMENT '删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                 `idx_status_envnameid_createtime` (`status`,`env_id`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=8440322 DEFAULT CHARSET=utf8 COMMENT='接口执行结果'

CREATE TABLE `api_info`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`      bigint(20) DEFAULT NULL COMMENT '项目id',
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
    `owner_id`        bigint(20) DEFAULT NULL COMMENT '负责人',
    `case_count`      int(11) NOT NULL DEFAULT '0' COMMENT '用例数',
    `invoke_times`    int(11) DEFAULT NULL COMMENT '最近30天调用次数',
    `status`          int(11) DEFAULT '0' COMMENT '状态',
    `remark`          text COMMENT '备注',
    `create_by_id`    bigint(20) DEFAULT NULL COMMENT '创建者id',
    `create_by_name`  varchar(100) DEFAULT NULL COMMENT '创建者',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `update_by_id`    bigint(20) DEFAULT NULL COMMENT '修改者id',
    `update_by_name`  varchar(100) DEFAULT NULL COMMENT '更新者',
    `update_time`     datetime     NOT NULL COMMENT '修改时间',
    `is_delete`       int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8591 DEFAULT CHARSET=utf8 COMMENT='接口管理表'

CREATE TABLE `application_info`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`     bigint(20) DEFAULT NULL COMMENT '项目id',
    `name`           varchar(255) NOT NULL COMMENT '应用名',
    `remark`         varchar(255) DEFAULT NULL COMMENT '应用描述',
    `owner_id`       bigint(20) DEFAULT NULL COMMENT '负责人',
    `create_by_id`   bigint(20) NOT NULL COMMENT '创建者id',
    `update_by_id`   bigint(20) DEFAULT NULL COMMENT '修改者id',
    `create_time`    datetime     NOT NULL COMMENT '创建时间',
    `create_by_name` varchar(100) DEFAULT NULL COMMENT '创建者',
    `update_time`    datetime     NOT NULL COMMENT '更新时间',
    `update_by_name` varchar(100) DEFAULT NULL COMMENT '修改者',
    `is_delete`      bigint(20) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8 COMMENT='应用管理'

CREATE TABLE `daily_data`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `project_id`        bigint(20) NOT NULL COMMENT '项目id',
    `date`              date         NOT NULL COMMENT '日期',
    `api_case_num`      bigint(20) NOT NULL DEFAULT '0' COMMENT '单接口用例数',
    `process_case_num`  bigint(20) DEFAULT '0' COMMENT '场景用例数',
    `case_success_rate` double       DEFAULT NULL COMMENT '用例成功率',
    `create_by_id`      bigint(20) NOT NULL COMMENT '创建者ID',
    `create_by_name`    varchar(100) NOT NULL COMMENT '创建者姓名',
    `create_time`       datetime     NOT NULL COMMENT '创建时间',
    `update_by_id`      bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `update_by_name`    varchar(100) DEFAULT NULL COMMENT '修改者姓名',
    `update_time`       datetime     DEFAULT NULL COMMENT '更新时间',
    `is_delete`         int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY                 `daily_data_id_IDX` (`id`,`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=694 DEFAULT CHARSET=utf8 COMMENT='每日汇总数据'

CREATE TABLE `data_source_config`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`       bigint(20) NOT NULL COMMENT '项目id',
    `app_id`           varchar(100) NOT NULL COMMENT '应用id',
    `name`             varchar(100) DEFAULT NULL COMMENT '名称',
    `data_source_json` json         DEFAULT NULL COMMENT '数据库信息',
    `remark`           text COMMENT '描述',
    `create_by_id`     bigint(20) NOT NULL COMMENT '创建者id',
    `create_by_name`   varchar(100) DEFAULT NULL COMMENT '创建人',
    `create_time`      datetime     NOT NULL COMMENT '创建时间',
    `update_by_id`     bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `update_by_name`   varchar(100) DEFAULT NULL COMMENT '修改人',
    `update_time`      datetime     NOT NULL COMMENT '修改时间',
    `is_delete`        bigint(20) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='数据库配置'

CREATE TABLE `domain_info`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`     bigint(20) DEFAULT NULL COMMENT '项目id',
    `name`           varchar(100) NOT NULL COMMENT '域名名称',
    `url_path`       varchar(100) DEFAULT NULL COMMENT '域名地址，或ip:端口号',
    `create_by_id`   bigint(20) NOT NULL COMMENT '创建人id',
    `create_by_name` varchar(100) NOT NULL COMMENT '创建人',
    `create_time`    datetime     NOT NULL COMMENT '创建时间',
    `update_by_id`   bigint(20) DEFAULT '0' COMMENT '修改人id',
    `update_by_name` varchar(100) NOT NULL COMMENT '修改人',
    `update_time`    datetime     NOT NULL COMMENT '修改时间',
    `is_delete`      bigint(20) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COMMENT='域名信息表'

CREATE TABLE `encrypt_info`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`     bigint(20) DEFAULT NULL COMMENT '项目id',
    `app_id`         int(11) DEFAULT NULL COMMENT '应用',
    `name`           varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
    `encrypt_json`   json                               DEFAULT NULL COMMENT '加密',
    `create_by_id`   bigint(100) DEFAULT NULL COMMENT '创建人id',
    `create_time`    datetime NOT NULL COMMENT '创建时间',
    `create_by_name` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人名称',
    `update_by_id`   bigint(100) DEFAULT NULL COMMENT '修改人id',
    `update_time`    datetime NOT NULL COMMENT '修改时间',
    `update_by_name` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '修改人名称',
    `is_delete`      int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=403 DEFAULT CHARSET=utf8 COMMENT='加密参数表'

CREATE TABLE `env_config`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`      bigint(20) DEFAULT NULL COMMENT '项目id',
    `app_id`          bigint(20) NOT NULL COMMENT '应用id',
    `env_id`          int(11) NOT NULL DEFAULT '1' COMMENT '环境id',
    `env_name`        varchar(100) NOT NULL COMMENT '环境名',
    `env_sort`        int(11) NOT NULL DEFAULT '1' COMMENT '环境顺序',
    `domain_id`       bigint(20) DEFAULT NULL COMMENT '域名id',
    `request_headers` text COMMENT '请求头',
    `env_variables`   text COMMENT '环境变量',
    `create_by_id`    bigint(100) DEFAULT NULL COMMENT '创建人id',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `create_by_name`  varchar(100) DEFAULT NULL COMMENT '创建人名称',
    `update_by_id`    bigint(100) DEFAULT NULL COMMENT '修改人id',
    `update_time`     datetime     NOT NULL COMMENT '修改时间',
    `update_by_name`  varchar(100) DEFAULT NULL COMMENT '修改人名称',
    `is_delete`       int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=399 DEFAULT CHARSET=utf8 COMMENT='环境信息表'

CREATE TABLE `process_case`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`     bigint(20) NOT NULL COMMENT '项目id',
    `app_id`         bigint(20) DEFAULT NULL COMMENT '应用id',
    `tree_key`       varchar(100) NOT NULL DEFAULT '0' COMMENT '树节点key',
    `name`           varchar(255) NOT NULL COMMENT '用例名',
    `priority`       int(11) DEFAULT NULL COMMENT '优先级',
    `is_sub_process` int(11) NOT NULL DEFAULT '0' COMMENT '是否是子流程',
    `input_params`   text COMMENT '用例入参',
    `nodes`          json                  DEFAULT NULL COMMENT '节点',
    `edges`          json                  DEFAULT NULL COMMENT '边',
    `owner_id`       bigint(20) DEFAULT NULL COMMENT '负责人id',
    `status`         int(11) DEFAULT '0' COMMENT '状态',
    `remark`         text COMMENT '备注',
    `create_by_id`   bigint(20) NOT NULL COMMENT '创建者ID',
    `create_by_name` varchar(100) NOT NULL COMMENT '创建者姓名',
    `create_time`    datetime     NOT NULL COMMENT '创建时间',
    `update_by_id`   bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `update_by_name` varchar(100)          DEFAULT NULL COMMENT '修改者姓名',
    `update_time`    datetime              DEFAULT NULL COMMENT '更新时间',
    `is_delete`      int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2015 DEFAULT CHARSET=utf8 COMMENT='场景用例'

CREATE TABLE `process_case_node`
(
    `id`              bigint(20) NOT NULL COMMENT 'id',
    `project_id`      bigint(20) NOT NULL COMMENT '项目id',
    `case_id`         bigint(20) NOT NULL COMMENT '用例id',
    `app_id`          bigint(20) DEFAULT NULL COMMENT '应用id',
    `api_id`          bigint(20) DEFAULT NULL COMMENT '接口id',
    `request_headers` text COMMENT '请求头',
    `request_params`  text COMMENT '请求参数',
    `assert_list`     text COMMENT '断言',
    `is_sub_env`      json         DEFAULT NULL COMMENT '子流程环境关联',
    `input_params`    text COMMENT '入参（目前只有子流程用到）',
    `output_params`   text COMMENT '节点出参',
    `sub_case_id`     bigint(20) DEFAULT NULL COMMENT '子流程用例id',
    `groovy_key`      varchar(255) DEFAULT NULL COMMENT '条件节点和前置步骤输出参数的key',
    `groovy_script`   text COMMENT 'groovy脚本(仅条件节点用到)',
    `secret_id`       bigint(20) DEFAULT NULL COMMENT '加密参数id',
    `data_source_id`  bigint(20) DEFAULT NULL COMMENT '数据源ID(仅SQL节点用到)',
    `sql_script`      text COMMENT 'SQL脚本(仅SQL节点用到)',
    `create_by_id`    bigint(20) NOT NULL COMMENT '创建者ID',
    `create_by_name`  varchar(100) NOT NULL COMMENT '创建者姓名',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `update_by_id`    bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `update_by_name`  varchar(100) DEFAULT NULL COMMENT '修改者姓名',
    `update_time`     datetime     DEFAULT NULL COMMENT '修改时间',
    `is_delete`       int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY               `mul_testcase_node_id_IDX` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场景测试用例节点详情'

CREATE TABLE `process_case_record`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`        bigint(20) NOT NULL COMMENT '项目id',
    `app_id`            bigint(20) NOT NULL COMMENT '应用id',
    `schedule_id`       bigint(20) DEFAULT NULL COMMENT '任务Id',
    `schedule_batch_id` bigint(20) DEFAULT NULL COMMENT '定时任务批次Id',
    `case_id`           bigint(20) NOT NULL COMMENT '用例id',
    `nodes`             text         NOT NULL COMMENT '节点',
    `edges`             text         NOT NULL COMMENT '边',
    `account_id`        bigint(20) DEFAULT NULL COMMENT '执行用户id',
    `env_sort_id`       int(10) NOT NULL COMMENT '执行环境id',
    `exec_count`        int(11) DEFAULT '1' COMMENT '执行次数',
    `status`            varchar(100) NOT NULL COMMENT '状态',
    `input_params`      text COMMENT '用例入参',
    `output_params`     text COMMENT '用例出参',
    `mode`              varchar(100) NOT NULL COMMENT '模式',
    `create_by_id`      bigint(20) NOT NULL COMMENT '创建者ID',
    `create_by_name`    varchar(100) NOT NULL COMMENT '创建者姓名',
    `create_time`       datetime     NOT NULL COMMENT '创建时间',
    `update_by_id`      bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `update_by_name`    varchar(100) DEFAULT NULL COMMENT '修改者姓名',
    `update_time`       datetime     DEFAULT NULL COMMENT '修改时间',
    `is_delete`         int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY                 `mul_testcase_result_id_IDX` (`id`) USING BTREE,
    KEY                 `mul_testcase_result_id_IDX2` (`id`,`project_id`,`schedule_id`,`schedule_batch_id`,`case_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1913990500516163586 DEFAULT CHARSET=utf8 COMMENT='场景用例执行结果'

CREATE TABLE `process_case_node_record`
(
    `id`                       bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`               bigint(20) NOT NULL COMMENT '项目id',
    `case_id`                  bigint(20) NOT NULL COMMENT '用例id',
    `case_result_id`           bigint(20) NOT NULL COMMENT '用例结果id',
    `node_id`                  varchar(100) NOT NULL COMMENT '当前节点id',
    `next_node_id`             varchar(100) DEFAULT NULL COMMENT '下一节点id',
    `debug_result`             longtext COMMENT '用例节点执行结果',
    `sql_exec_result`          longtext COMMENT 'SQL节点执行结果',
    `pre_step_exec_result`     longtext COMMENT '前置步骤执行结果',
    `status`                   varchar(100) NOT NULL COMMENT '状态',
    `sub_process_input_params` text COMMENT '子流程入参',
    `output_params`            text COMMENT '节点出参',
    `sub_result_id`            bigint(20) DEFAULT NULL COMMENT '子流程结果id',
    `create_by_id`             bigint(20) DEFAULT NULL COMMENT '创建人id',
    `create_by_name`           varchar(100) DEFAULT NULL COMMENT '创建者姓名',
    `create_time`              datetime     NOT NULL COMMENT '创建时间',
    `update_by_id`             bigint(20) DEFAULT NULL COMMENT '修改人id',
    `update_by_name`           varchar(100) DEFAULT NULL COMMENT '修改者姓名',
    `update_time`              datetime     NOT NULL COMMENT '修改时间',
    `is_delete`                int(11) DEFAULT '0' COMMENT '删除',
    PRIMARY KEY (`id`),
    KEY                        `mul_testcase_result_node_id_IDX` (`id`) USING BTREE,
    KEY                        `mul_testcase_result_node_tenant_id_IDX` (`case_result_id`,`node_id`),
    KEY                        `tenant_id_testcase_id_node_id_create_time` (`case_id`,`node_id`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1913990505050206209 DEFAULT CHARSET=utf8 COMMENT='场景用例节点执行结果'

CREATE TABLE `project_info`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `name`           varchar(100) NOT NULL COMMENT '项目名称',
    `remark`         varchar(255)          DEFAULT NULL COMMENT '项目描述',
    `create_by_id`   int(100) DEFAULT NULL COMMENT '创建者id',
    `create_by_name` varchar(100)          DEFAULT NULL COMMENT '创建者',
    `create_time`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`   int(11) DEFAULT NULL COMMENT '更新人id',
    `update_by_name` varchar(100)          DEFAULT NULL COMMENT '更新者姓名\n',
    `update_time`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete`      int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='项目管理'

CREATE TABLE `project_user_association`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `project_id`     bigint(20) DEFAULT NULL COMMENT '项目id',
    `user_id`        bigint(20) NOT NULL COMMENT '用户id',
    `status`         int(2) DEFAULT '0' COMMENT '状态 0启用 1停用',
    `is_delete`      int(2) DEFAULT '0' COMMENT '删除 0未删除 1删除',
    `create_by_id`   int(11) DEFAULT NULL COMMENT '创建人id',
    `create_by_name` varchar(50) DEFAULT NULL COMMENT '创建人姓名',
    `create_time`    timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `update_by_id`   int(11) DEFAULT NULL COMMENT '修改人id',
    `update_by_name` varchar(50) DEFAULT NULL COMMENT '修改人姓名',
    `update_time`    timestamp NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='项目用户关联表'

CREATE TABLE `task_schedule`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `name`           varchar(50)  NOT NULL DEFAULT '0' COMMENT '定时任务名称',
    `project_id`     bigint(20) NOT NULL DEFAULT '0' COMMENT '项目id',
    `exec_mode`      char(20)              DEFAULT 'serial' COMMENT '执行模式',
    `env_sort`       int(11) DEFAULT NULL COMMENT '环境顺序',
    `remark`         varchar(100) NOT NULL DEFAULT '' COMMENT '定时任务描述',
    `cron`           varchar(50)  NOT NULL DEFAULT '0' COMMENT 'cron表达式',
    `next_exec_time` timestamp NULL DEFAULT NULL COMMENT '下次执行时间',
    `enable`         int(10) DEFAULT '0' COMMENT '状态，1启用，-1禁用',
    `status`         varchar(20)           DEFAULT NULL COMMENT '执行状态',
    `owner_id`       bigint(20) DEFAULT '0' COMMENT '负责人',
    `testcase_type`  varchar(255)          DEFAULT NULL COMMENT '用例类型：接口用例、场景用例',
    `testcase_list`  json                  DEFAULT NULL COMMENT '用例列表',
    `create_by_id`   bigint(20) NOT NULL COMMENT '创建人id',
    `create_by_name` varchar(100) NOT NULL COMMENT '创建人',
    `create_time`    datetime              DEFAULT NULL COMMENT '创建时间',
    `update_by_id`   bigint(20) NOT NULL COMMENT '最后修改人id',
    `update_by_name` varchar(100) NOT NULL COMMENT '修改人',
    `update_time`    datetime              DEFAULT NULL COMMENT '修改时间',
    `is_delete`      bigint(20) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8 COMMENT='定时任务'

CREATE TABLE `task_schedule_record`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `project_id`        bigint(20) NOT NULL COMMENT '项目id',
    `schedule_id`       bigint(20) DEFAULT NULL COMMENT '定时任务id',
    `schedule_batch_id` bigint(20) DEFAULT NULL COMMENT '定时任务批次id',
    `env_sort_id`       bigint(20) DEFAULT NULL COMMENT '环境id',
    `status`            varchar(20)  DEFAULT NULL COMMENT '执行状态',
    `testcase_type`     varchar(255) DEFAULT NULL COMMENT '用例类型',
    `mode`              varchar(100) DEFAULT NULL COMMENT '模式',
    `testcase_list`     text     NOT NULL COMMENT '用例列表',
    `ip`                varchar(100) DEFAULT NULL,
    `create_by_id`      bigint(20) NOT NULL COMMENT '创建者id',
    `create_by_name`    varchar(100) DEFAULT NULL COMMENT '创建人',
    `create_time`       datetime NOT NULL COMMENT '创建时间',
    `update_by_id`      bigint(20) DEFAULT NULL COMMENT '修改者ID',
    `update_by_name`    varchar(100) DEFAULT NULL COMMENT '修改人',
    `update_time`       datetime NOT NULL COMMENT '修改时间',
    `is_delete`         bigint(20) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=818674 DEFAULT CHARSET=utf8 COMMENT='定时任务执行记录表'

CREATE TABLE `test_account`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `project_id`       bigint(20) DEFAULT NULL COMMENT '项目id',
    `name`             varchar(255) NOT NULL COMMENT '名称',
    `api_case_id`      bigint(20) NOT NULL COMMENT '接口用例id',
    `input_params`     varchar(255) NOT NULL COMMENT '账号密码',
    `cron`             varchar(100) NOT NULL COMMENT 'cron',
    `json_path`        text COMMENT 'jsonPath',
    `token`            text COMMENT 'token',
    `gen_token_status` varchar(100) DEFAULT NULL COMMENT '生成token状态',
    `fail_reason`      text COMMENT '失败原因',
    `next_exec_time`   timestamp NULL DEFAULT NULL COMMENT '下次执行时间',
    `create_by_id`     bigint(100) DEFAULT NULL COMMENT '创建者ID',
    `create_by_name`   varchar(100) DEFAULT NULL COMMENT '创建者',
    `create_time`      datetime     NOT NULL COMMENT '创建时间',
    `update_by_id`     bigint(100) DEFAULT NULL COMMENT '修改者ID',
    `update_by_name`   varchar(100) DEFAULT NULL COMMENT '修改者',
    `update_time`      datetime     NOT NULL COMMENT '修改时间',
    `is_delete`        int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8 COMMENT='测试账号表'

CREATE TABLE `tree_info`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `project_id`     bigint(20) NOT NULL COMMENT '项目id',
    `tree_data`      text COMMENT '树详情',
    `is_delete`      int(11) DEFAULT NULL COMMENT '删除',
    `create_by_id`   bigint(20) DEFAULT NULL COMMENT '创建者id',
    `create_by_name` varchar(100) DEFAULT NULL COMMENT '创建者姓名',
    `create_time`    datetime NOT NULL COMMENT '创建时间',
    `update_by_id`   bigint(20) DEFAULT NULL COMMENT '修改者id',
    `update_by_name` varchar(100) DEFAULT NULL COMMENT '修改者',
    `update_time`    datetime NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='树结构管理'

CREATE TABLE `user`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `project_id`     bigint(20) DEFAULT NULL COMMENT '选择项目',
    `account`        varchar(50)  NOT NULL COMMENT '账号',
    `name`           varchar(50)  NOT NULL COMMENT '姓名',
    `password`       varchar(200) NOT NULL COMMENT '密码',
    `email`          varchar(200) DEFAULT NULL COMMENT '邮箱',
    `phone`          bigint(20) DEFAULT NULL,
    `role_id`        int(20) DEFAULT NULL COMMENT '角色id',
    `status`         int(2) DEFAULT '0' COMMENT '状态 0启用 1停用',
    `is_delete`      int(2) DEFAULT '0' COMMENT '删除 0未删除 1删除',
    `create_by_id`   int(11) DEFAULT NULL COMMENT '创建人id',
    `create_by_name` varchar(50)  DEFAULT NULL COMMENT '创建人姓名',
    `create_time`    timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `update_by_id`   int(11) DEFAULT NULL COMMENT '修改人id',
    `update_by_name` varchar(50)  DEFAULT NULL COMMENT '修改人姓名',
    `update_time`    timestamp NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表'

INSERT INTO crazy_test.user (id, project_id, account, name, password, email, phone, role_id, status, is_delete, create_by_id, create_by_name, create_time, update_by_id, update_by_name, update_time) VALUES (1, 14, 'admin', '管理员', '21232f297a57a5a743894a0e4a801fc3', '11@qq.com', 13164165663, 1, 0, 0, 1, 'sys', '2025-03-11 21:22:10', 1, 'sys', '2025-03-11 21:22:16');















