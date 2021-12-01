drop table if exists `user`;
create table `user` (
    `id` VARCHAR(32) not null comment '主键',
    `username` VARCHAR(50) not null comment '用户名',
    `nickname` VARCHAR(50) comment '昵称',
    `password` char(32) not null comment '密码',
    PRIMARY KEY ( id ),
    unique key `username_unique` (`username`)
) engine=innodb default charset=utf8 comment '用户表';

# unique key `username_unique` (`username`)
# username_unique 是这个唯一键的名字，对 username 进行唯一处理