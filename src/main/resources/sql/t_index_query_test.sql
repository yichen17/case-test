CREATE TABLE `t_index_query_test` (
                                      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
                                      `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `app_status` int(11) DEFAULT NULL COMMENT '工单状态码',
                                      `repay_status` int(11) DEFAULT NULL COMMENT '结清状态码',
                                      `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                      `name` varchar(255) DEFAULT NULL COMMENT '名字',
                                      `age` int(11) DEFAULT NULL COMMENT '年龄',
                                      `certId` varchar(30) DEFAULT NULL COMMENT '身份证号',
                                      PRIMARY KEY (`id`),
                                      KEY `idx_query` (`update_time`,`app_status`,`repay_status`) USING BTREE COMMENT '查询索引'
) ENGINE=InnoDB AUTO_INCREMENT=36000001 DEFAULT CHARSET=utf8mb4 COMMENT='工单测试查询';