# 数据库 
#创建数据库
DROP DATABASE IF EXISTS apiadvertise_db;
CREATE DATABASE apiadvertise_db;

#使用数据库 
use apiadvertise_db;

#创建管理员表 
CREATE TABLE admin_tb(
admin_id int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
name varchar(255) COMMENT '管理员姓名',
cell_phone varchar(255) COMMENT '手机号',
email varchar(255) COMMENT '邮箱',
password varchar(255) COMMENT '密码',
create_date datetime COMMENT '账号创建时间',
last_login_date datetime COMMENT '最新登录时间',
PRIMARY KEY (admin_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='管理员表';

#创建渠道表 
CREATE TABLE channel_tb(
channel_id int(11) NOT NULL AUTO_INCREMENT COMMENT '渠道id',
name varchar(255) COMMENT '渠道姓名',
cell_phone varchar(255) COMMENT '手机号',
email varchar(255) COMMENT '邮箱',
password varchar(255) COMMENT '密码',
api_token varchar(255) COMMENT 'apitoken',
create_date datetime COMMENT '账号创建时间',
last_login_date datetime COMMENT '最新登录时间',
PRIMARY KEY (channel_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='渠道表';

#创建广告表 
CREATE TABLE advertise_tb(
advertise_id int(11) NOT NULL AUTO_INCREMENT COMMENT '广告索引ID',
app_id int(11) COMMENT '广告ID',
ad_name varchar(255) COMMENT '广告名称',
bundle_id varchar(255) COMMENT '包名bundleId',
task_demands varchar(255) COMMENT '任务要求',
currency varchar(255) COMMENT '货币',
country varchar(255) COMMENT '国家',
icon_url varchar(255) COMMENT '图标',
price decimal(11,2) COMMENT '价格',
app_url varchar(255) COMMENT '投放链接',
preview_url varchar(255) COMMENT '预览链接',
task_num int(11) COMMENT '任务数量',
update_date datetime COMMENT '获取日期',
PRIMARY KEY (advertise_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='广告表 ';

#创建渠道广告表 
CREATE TABLE ad_tb(
ad_id int(11) NOT NULL AUTO_INCREMENT COMMENT '渠道广告索引ID',
app_id int(11) COMMENT '广告ID',
ad_name varchar(255) COMMENT '广告名称',
bundle_id varchar(255) COMMENT '包名bundleId',
task_demands varchar(255) COMMENT '任务要求',
currency varchar(255) COMMENT '货币',
country varchar(255) COMMENT '国家',
icon_url varchar(255) COMMENT '图标',
price decimal(11,2) COMMENT '价格',
app_url varchar(255) COMMENT '投放链接',
preview_url varchar(255) COMMENT '预览链接',
task_num int(11) COMMENT '任务数量',
update_date datetime COMMENT '获取日期',
channel_id int(11) COMMENT '渠道id外键',
PRIMARY KEY (ad_id),
CONSTRAINT FK_CHANNEL_AD FOREIGN KEY (channel_id) REFERENCES channel_tb (channel_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='渠道广告表 ';

#创建渠道点击表 
CREATE TABLE click_tb(
click_id int(11) NOT NULL AUTO_INCREMENT COMMENT '渠道点击索引ID',
appid int(11) COMMENT '广告ID',
idfa varchar(255) COMMENT 'idfa',
ip varchar(255) COMMENT 'ip',
mac varchar(255) COMMENT 'mac',
callback varchar(255) COMMENT '回调地址',
devid varchar(255) COMMENT 'ios 设备的 deviceId',
osver varchar(255) COMMENT '设备版本',
devtype varchar(255) COMMENT '设备型号',
status varchar(255) COMMENT '状态',
update_date datetime COMMENT '点击日期',
channel_id int(11) COMMENT '渠道id外键',
PRIMARY KEY (click_id),
CONSTRAINT FK_CHANNEL_CLICK FOREIGN KEY (channel_id) REFERENCES channel_tb (channel_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='渠道点击表 ';

#设置初始管理员密码MD5加密123456
INSERT IGNORE INTO admin_tb (name,cell_phone,email,password,create_date,last_login_date) 
VALUES ("聂跃","15111336587","278076304@qq.com","11874bb6149dd45428da628c9766b252",now(),now());  

