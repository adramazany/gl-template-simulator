--##--##--##--##--##--##--##--##--##--##--##--##--##--##--##--##--##--
--##MySQL
-- CREATE DATABASE gl CHARACTER SET utf8 COLLATE utf8_general_ci;
-- CREATE USER 'gl'@'localhost' IDENTIFIED BY 'gl';
-- GRANT ALL PRIVILEGES ON gl. * TO 'gl'@'localhost';
--
-- /*************************************
-- * drops
-- **************************************/
-- --##
-- drop table limits ;
-- --##
-- drop table restriction ;
-- --##
-- drop table cutoff_day;
-- --##
-- drop table cutoff_month;
-- --##
-- drop table cutoff_year;
-- --##
-- drop table article ;
-- --##
-- drop table voucher ;
-- --##
-- drop table voucher_type ;
-- drop table templates;
-- drop table template_history;
-- drop table costcenter ;
-- --##
-- drop table biz_mapping ;
-- --##
-- drop table biz_profile ;
-- --##
-- drop table account ;
-- --##
-- drop table users ;
-- --##
-- drop table response_log ;
-- --##
-- drop table response ;
-- --##
-- --##
-- drop table request ;
-- --##
-- drop table transaction_res_log ;
-- --##
-- drop table transaction_req ;
-- drop table transaction_res ;
-- --##
-- drop table event ;
-- drop table config ;


/*************************************
* base
**************************************/
create table config (
    `key`     varchar(50) not null
    ,value    varchar(150)
    ,constraint pk_config primary key (`key`)
);
insert into config values ('account_code_count_level_1','3');
insert into config values ('account_code_count_level_2','3');
insert into config values ('account_code_count_level_3','6');
insert into config values ('account_code_count_level_4','8');
insert into config values ('biz_mapping_account_field','account_no');
insert into config values ('biz_mapping_name_field','biz_name');
insert into config values ('biz_mapping_costcenter_field','biz_id');
insert into config values ('current_financial_year','1399');
insert into config values ('last_voucher_no_final','0');
insert into config values ('last_voucher_no','0');

/*************************************
* event/product/line tables
**************************************/

create table events (
    id       int(9) not null auto_increment
    ,f_parent int(9)
    ,hierarchy_id varchar(50) not null
    ,code int(9)
    ,name     varchar(100) not null
    ,json_sample_business_agreed text
    ,voucher_description_formula    varchar(4000)
    ,voucher_voucher_type_formula   varchar(4000)
    ,constraint pk_event primary key (id)
    ,constraint uk_event_code unique (code)
    ,constraint fk_event_parent foreign key (f_parent) references event (id)
);
--##comment on column event.hierarchy_id is 'store all parent ids and self id splited by dots ex: .1.5. ';
--##comment on column event.name is 'in first level its line, second level is product and third level its event ';

/*************************************
* transaction request/response
**************************************/

create table transaction_req (
    id		int(12) not null auto_increment
    ,requestid  varchar(36) not null
    ,f_event    int(9) not null
    ,amount     int(12) not null
    ,clientid   varchar(36) not null
    ,json       text
    ,aggregationid  varchar(36)
    ,parent_requestid varchar(36)
    ,create_date datetime default NOW() not null
    ,constraint pk_transaction_req primary key (id)
    ,constraint uk_event_requestid unique ( requestid, f_event)
    ,constraint fk_transaction_req_event foreign key (f_event) references event (id)
);

create table transaction_res (
     f_transaction_req  int(12) not null
    ,status     int(1) not null
    ,response   int(5) not null
    ,duration   int(6) not null
    ,ctry       int(3)
    ,version    int(3)  default 0 not null
    ,create_date datetime default NOW() not null
    ,last_modify_date datetime
    ,constraint pk_transaction_res primary key (f_transaction_req)
    ,constraint fk_transaction_res_req foreign key (f_transaction_req) references transaction_req (id)
    ,constraint ck_transaction_res_status check (status in (0,1,2,3,4))
);
--##comment on column transaction_res.status is '0:new, 1:done, 2:inprogress, 3:failed, 4:unknown';
--##comment on column transaction_res.response is '0:succeed , ?:error code';


create table transaction_res_log (
    id         int(12) not null auto_increment
    ,f_transaction_req  int(12) not null
    ,status     int(1) not null
    ,response   int(5) not null
    ,duration   int(6) not null
    ,ctry       int(3)
    ,version    int(3) default 0 not null
    ,create_date datetime default NOW() not null
    ,last_modify_date date
    ,exception_log text
    ,constraint pk_transaction_res_log primary key (id)
    ,constraint ck_transaction_res_log_status check (status in (0,1,2,3,4))
);
--##comment on column transaction_res_log.status is '0:new, 1:done, 2:inprogress, 3:failed, 4:unknown';
--##comment on column transaction_res_log.response is '0:succeed , ?:error code';

/*************************************
* other request/response
**************************************/

create table request (
    id      int(12) not null auto_increment
    ,requestid  varchar(36) not null
    ,f_event    int(9) not null
    ,clientid   varchar(36) not null
    ,json       text
    ,aggregationid  varchar(36)
    ,parent_requestid varchar(36)
    ,create_date datetime default NOW() not null
    ,constraint pk_request primary key (id)
    ,constraint uk_request unique ( requestid, f_event)
    ,constraint fk_request_event foreign key (f_event) references event (id)
);

create table response (
     f_request  int(12) not null
    ,status     int(1) not null
    ,response   int(5) not null
    ,duration   int(6) not null
    ,ctry       int(3)
    ,version    int(3) default 0 not null
    ,create_date datetime default NOW() not null
    ,last_modify_date datetime
    ,constraint pk_response primary key (f_request)
    ,constraint fk_response_request foreign key (f_request) references request (id)
    ,constraint ck_response_status check (status in (0,1,2,3,4))
);
--##comment on column response.status is '0:new, 1:done, 2:inprogress, 3:failed, 4:unknown';
--##comment on column response.response is '0:succeed , ?:error code';


create table response_log (
    id         int(12) not null auto_increment
    ,f_request  int(12) not null
    ,status     int(1) not null
    ,response   int(5) not null
    ,duration   int(6) not null
    ,ctry       int(3)
    ,version    int(3) default 0 not null
    ,create_date datetime default NOW() not null
    ,last_modify_date datetime
    ,exception_log text
    ,constraint pk_response_log primary key (id)
    ,constraint ck_response_log_status check (status in (0,1,2,3,4))
);
--##comment on column response_log.status is '0:new, 1:done, 2:inprogress, 3:failed, 4:unknown';
--##comment on column response_log.response is '0:succeed , ?:error code';

/*************************************
* security
**************************************/

create table users (
     id   int(9)  not null auto_increment
    ,username   varchar(36) not null
    ,password_hash   varchar(256)
    ,first_name varchar(50)
    ,last_name  varchar(150)
    ,mobile     varchar(12)
    ,constraint pk_users primary key (id)
    ,constraint uk_users unique ( username )
);

/*************************************
* account
**************************************/

create table account (
     id               int(12)  not null auto_increment
    ,f_parent         int(12)
    ,hierarchy_id     varchar(60)
    ,account_no       int(10)
    ,full_account_no  varchar(60)
    ,account_name     varchar(250)
    ,inherited        int(1) default 1 not null
    ,status           int(1) default 1 not null
    ,account_type     int(1) not null
    ,account_origin   int(1) not null
    ,is_cutoff   		int(1) not null
    ,version          int(3) default 0 not null
    ,create_date      datetime default NOW() not null
    ,last_modify_date datetime
    ,constraint pk_account primary key (id)
    ,constraint uk_account unique ( f_parent, account_no)
    ,constraint fk_account_parent foreign key (f_parent) references event (id)
    ,constraint ck_account_inherited check (inherited in (0,1))
    ,constraint ck_account_status check (status in (0,1,2))
    ,constraint ck_account_type check (account_type in (1,2,3))
    ,constraint ck_account_origin check (account_origin in (0,1,2,3))
    ,constraint ck_account_cutoff check (is_cutoff in (0,1))
);
--##comment on column account.hierarchy_id is 'store all parent ids and self id splited by dots ex: .1.5. ';
--##comment on column account.inherited is '1:inherited , 0:not-inherited(override)   when some fields (status,type,origin) of parent changes, system should changes all inherited child nodes';
--##comment on column account.status is '0:disabled  ,  1:enabled  ,  2:enabled-just-for-restriction-or-limits  when you could use an account in any transaction, that it has be enabled ';
--##comment on column account.account_type is '1:حساب های موقت  ,  2:حساب های دائمی   ,   3:حساب های مخلوط';
--##comment on column account.account_origin is '0:حساب های فاقد ماهیت   ,  1:ماهیت بدهکار  ,   2:ماهیت بستانکار   ,   3:ماهیت دو گانه-انتظامی';

/*************************************
* biz mappping
**************************************/

create table biz_profile (
     id   int(6) not null auto_increment
    ,code varchar(20) not null
    ,name varchar(100) not null
    ,f_event_owner  int(9)
    ,constraint pk_biz_profile primary key (id)
    ,constraint uk_biz_profile_code unique (code)
    ,constraint uk_biz_profile_name unique (name)
);
--##comment on table biz_profile is 'every record on this table is an agreement between accounting and business';
--##comment on column biz_profile.code is 'agreemented code ex: seller,buyer,loantype';
--##comment on column biz_profile.f_event_owner is 'it could be general profile between mutiple line or line related profile';



create table biz_mapping (
     id   int(12) not null auto_increment
    ,f_request  int(12)
    ,f_profile    int(6) not null
    ,account_no int(10) not null
    ,biz_id     int(18) not null
    ,biz_name   varchar(250) not null
    ,constraint pk_biz_mapping primary key (id)
    ,constraint uk_biz_mapping_profile unique (f_profile, account_no)
    ,constraint uk_biz_mapping_biz unique (f_profile, biz_id)
    ,constraint fk_biz_mapping_request foreign key (f_request) references request (id)
);
--##comment on table biz_mapping is 'when we uss a biz_mapping record in voucher that account_no should add to account table and also for costcenter';
--##comment on column biz_mapping.account_no is 'auto generated by max(account_no)+1 of this f_profile';


/*************************************
* costcenter
**************************************/

create table cost_centers (
     id     int(12)  not null auto_increment
--     ,f_parent         int(12)
    ,parent_id         int(12)
    ,hierarchy_id     varchar(60)
    ,code             int(10)
    ,name             varchar(150)
    ,status           int(1) default 1 not null
    ,constraint pk_costcenter primary key (id)
    ,constraint uk_costcenter unique ( f_parent, code)
);

/*************************************
* template
**************************************/

create table templates (
     id   int(9) not null auto_increment
    ,name varchar(150) not null
    ,f_event  int(9) not null
    ,order_no  int(4) not null
    ,f_account_base       int(12) not null
    ,account_no_formula   tinytext
    ,debit_formula        tinytext
    ,credit_formula       tinytext
    ,description_formula  tinytext
    ,costcenter1_formula  tinytext
    ,costcenter2_formula  tinytext
    ,costcenter3_formula  tinytext
    ,status           int(1) default 1 not null
    ,version          int(3) default 0 not null
    ,create_date      datetime default NOW() not null
    ,last_modify_date datetime
    ,constraint pk_template primary key (id)
    ,constraint uk_template unique ( f_event, orderno)
    ,constraint fk_template_event foreign key (f_event) references event (id)
    ,constraint fk_template_account foreign key (f_account_base) references account (id)
    ,constraint ck_template_status check (status in (0,1))
);

create table template_history(
     id   int(9) not null
    ,name varchar(150) not null
    ,f_event  int(9) not null
    ,orderno  int(4) not null
    ,f_account_base       int(12) not null
    ,account_no_formula   tinytext
    ,debit_formula        tinytext
    ,credit_formula       tinytext
    ,description_formula  tinytext
    ,costcenter1_formula  tinytext
    ,costcenter2_formula  tinytext
    ,costcenter3_formula  tinytext
    ,status           int(1) default 1 not null
    ,version          int(3) default 0 not null
    ,create_date      datetime default NOW() not null
    ,last_modify_date datetime
    ,constraint pk_template_history primary key (id)
);

/*************************************
* voucher
**************************************/

create table voucher_type (
     id   int(5)  not null auto_increment
    ,name varchar(50) not null
    ,version          int(3) default 0 not null
    ,create_date      datetime default NOW() not null
    ,last_modify_date datetime
    ,constraint pk_voucher_type primary key (id)
    ,constraint uk_voucher_type unique ( name)
);
insert into voucher_type (id,name) values (1,'عادی');
insert into voucher_type (id,name) values (2,'اصلاحی');
insert into voucher_type (id,name) values (3,'به تاریخ گذشته');


create table voucher (
     id   int(12)  not null auto_increment
    ,f_transaction_req  int(12)
    ,f_parent		  int(12)
    ,register_date  datetime  default NOW() not null
    ,effective_date datetime  default NOW() not null
    ,description    varchar(250)
    ,f_voucher_type int(5)
    ,financial_year int(4) not null
    ,orderno        int(12) not null
    ,status         int(1)
    ,final_no       int(12)
    ,f_user_create  int(9)
    ,f_user_confirm int(9)
    ,version          int(3) default 0 not null
    ,create_date      datetime default NOW() not null
    ,confirm_date	  datetime
    ,last_modify_date datetime
    ,constraint pk_voucher primary key (id)
    ,constraint uk_voucher_transaction unique ( f_transaction_req)
    ,constraint uk_voucher_financial unique ( financial_year, orderno)
    ,constraint uk_voucher_final unique ( financial_year, final_no)
    ,constraint fk_voucher_type foreign key (f_voucher_type) references voucher_type (id)
    ,constraint fk_voucher_transaction_req foreign key (f_transaction_req) references transaction_req (id)
    ,constraint fk_voucher_parent foreign key (f_parent) references voucher (id)
    ,constraint fk_voucher_users_create foreign key (f_user_create) references users (id)
    ,constraint fk_voucher_users_confirm foreign key (f_user_confirm) references users (id)
    ,constraint ck_voucher_status check (status in (0,1,2,3,4))
);
--##comment on column voucher.status is '0:not-processed, 1:final, 2:temporal 3:in-progress, 4:confirmed'


create table article (
   id               int(18)  not null auto_increment
  ,f_voucher        int(12)  not null
  ,f_template       int(12)
  ,orderno          int(4)   not null
  ,f_account_leaf   int(12)  not null
  ,debit            int(12)
  ,credit           int(12)
  ,description      varchar(250) not null
  ,f_costcenter1    int(12)
  ,f_costcenter2    int(12)
  ,f_costcenter3    int(12)
  ,version          int(3) default 0 not null
  ,create_date      datetime default NOW() not null
  ,last_modify_date datetime
	,constraint pk_article primary key (id)
  ,constraint uk_article unique ( f_voucher, orderno)
  ,constraint fk_article_voucher foreign key (f_voucher) references voucher (id)
  ,constraint fk_article_account foreign key (f_account_leaf) references account (id)
  ,constraint fk_article_template foreign key (f_template) references template (id)
  ,constraint fk_article_costcenter1 foreign key (f_costcenter1) references costcenter (id)
  ,constraint fk_article_costcenter2 foreign key (f_costcenter2) references costcenter (id)
  ,constraint fk_article_costcenter3 foreign key (f_costcenter3) references costcenter (id)
);



/*************************************
* cutoff
**************************************/

create table cutoff_year(
   f_account  int(12)  not null
  ,year       int(4)   not null
  ,debit      int(18)  default 0 not null
  ,credit     int(18)  default 0 not null
  ,version    int(9)   default 0 not null
  ,last_modify_date datetime
  ,f_user_modify  int(9)
  ,constraint pk_cutoff_year primary key (f_account,year)
    ,constraint fk_cutoff_year_account foreign key (f_account) references account (id)
);


create table cutoff_month(
   f_account  int(12)  not null
  ,yearmonth  int(6)   not null
  ,debit      int(18)  default 0 not null
  ,credit     int(18)  default 0 not null
  ,version    int(9)   default 0 not null
  ,last_modify_date datetime
  ,f_user_modify  int(9)
  ,constraint pk_cutoff_month primary key (f_account,yearmonth)
    ,constraint fk_cutoff_month_account foreign key (f_account) references account (id)
);


create table cutoff_day(
   f_account  int(12)  not null
  ,yearmonthday  int(8)   not null
  ,debit      int(18)  default 0 not null
  ,credit     int(18)  default 0 not null
  ,version    int(9)   default 0 not null
  ,last_modify_date datetime
  ,f_user_modify  int(9)
  ,constraint pk_cutoff_day primary key (f_account,yearmonthday)
    ,constraint fk_cutoff_day_account foreign key (f_account) references account (id)
);

/*************************************
* restriction
**************************************/

create table restriction (
   id   int(9) not null auto_increment
  ,name varchar(100) not null
  ,f_account  int(12) not null
  ,effective_date datetime default NOW() not null
  ,expire_date    datetime
  ,status     int(1) default 1 not null
  ,min_amount  int(12)
  ,max_amount  int(12)
  ,debit_credit int(1) default 0 not null
  ,f_user     int(12)
  ,version          int(3) default 0 not null
  ,create_date      datetime default NOW() not null
  ,last_modify_date datetime
  ,constraint pk_restriction primary key (id)
  ,constraint uk_restriction unique ( f_account, f_user , effective_date)
  ,constraint ck_restriction_status check (status in (0,1))
  ,constraint ck_restriction_debit_credit check (debit_credit in (0,1,2))
);
--##comment on column restriction.expire_date is 'expire_date=null means life-time';
--##comment on column restriction.status is '1:enabled  ,  0:disabled  , between effective and expire datetime could store transaction on this f_account';
--##comment on column restriction.min_amount is 'minimum amount of transaction , null means any amount';
--##comment on column restriction.max_amount is 'maximum amount of transaction , null means any amount';
--##comment on column restriction.debit_credit is '0:both state  ,  1:debit-only   ,   2:credit-only';


/*************************************
* limit
**************************************/

create table limits (
   id   int(9) not null auto_increment
  ,name varchar(100) not null
  ,f_account  int(12) not null
  ,effective_date datetime default NOW() not null
  ,expire_date    datetime
  ,status         int(1) default 1 not null
  ,day_max_amount int(12)
  ,day_max_count  int(12)
  ,month_max_amount   int(12)
  ,month_max_count    int(12)
  ,debit_credit   int(1) default 0 not null
  ,version        int(3) default 0 not null
  ,create_date    datetime default NOW() not null
  ,last_modify_date   datetime
  ,constraint pk_limit primary key (id)
  ,constraint uk_limit unique ( f_account, effective_date)
  ,constraint ck_limit_status check (status in (0,1))
  ,constraint ck_limit_debit_credit check (debit_credit in (0,1,2))
);
--##comment on column limits.expire_date is 'expire_date=null means life-time';
--##comment on column limits.status is '1:enabled  ,  0:disabled  , between effective and expire datetime could store transaction on this f_account';
--##comment on column limits.day_max_amount is 'maximum amount of transaction per day , null means un-limit';
--##comment on column limits.day_max_count is 'maximum count of transaction per day, null means un-limit';
--##comment on column limits.month_max_amount is 'maximum amount of transaction per month , null means un-limit';
--##comment on column limits.month_max_count is 'maximum count of transaction per month, null means un-limit';
--##comment on column limits.debit_credit is '0:both state  ,  1:debit-only   ,   2:credit-only';


