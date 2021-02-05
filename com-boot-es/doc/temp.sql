create table nba.nba_player
(
	id int auto_increment comment '主键'
		primary key,
	countryEn varchar(200) null comment '城市的英文表示',
	country varchar(100) null comment '城市的中文表示',
	code varchar(60) null comment '球员号码',
	display_affiliation varchar(200) null comment '球员隶属关系',
	display_name varchar(100) null comment '球员名称',
	draft int null comment '草案',
	school_type varchar(100) null comment '教育类型',
	weight varchar(100) null comment '体重',
	play_year int null comment '职业年龄',
	jersey_no varchar(20) null comment '球衣号码',
	birth_day mediumtext null comment '生日',
	birth_day_str varchar(100) null comment '生日的字符串格式',
	display_name_en varchar(100) null comment '球员名称英文',
	position varchar(100) null comment '位置',
	height_value varchar(100) null comment '身高',
	player_id varchar(50) null comment '球员id',
	team_city varchar(100) null comment '球队城市',
	team_city_en varchar(100) null comment '球队城市的英文表示',
	team_name varchar(100) null comment '球队名称',
	team_name_en varchar(100) null comment '球队名称的英文表示',
	team_conference varchar(200) null comment '球队评论',
	team_conference_en varchar(200) null comment '球队评论英文表示',
	age int null comment '年龄'
)
comment 'nba 球员信息表';