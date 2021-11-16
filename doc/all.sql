-- 家族成员
drop table if exists `member`;
create table `member` (
    `id` VARCHAR(32) not null comment '主键',
    `name` VARCHAR(20) not null comment '尊姓大名',
    `nickname` VARCHAR(40) comment '别号',
    `sex` INT(4) not null comment '阴阳',
    `birthday` date comment '生辰',
    `life` INT(4) not null comment '生死',
    `zodiac` VARCHAR(2) comment '生肖',
    `constellation` VARCHAR(10) comment '星座',
    `occupation` VARCHAR(20) comment '行当',
    `interest` VARCHAR(50) comment '志趣',
    `contact` VARCHAR(20) comment '通讯',
    `intro` VARCHAR(200) comment '简述',
    PRIMARY KEY ( id )
) engine=innodb default charset=utf8 comment '家族成员';

insert into `member` (
    id,
    name,
    nickname,
    sex,
    birthday,
    life
#     zodiac,
#     constellation,
#     occupation,
#     interest,
#     contact,
#     intro
)
values (
   '1',
   '张大兵',
   '狗蛋',
   '1',
   '1992-05-02',
   '1'
);