insert into BIZ_PROFILE (id,code,name) values (1,'seller','پذیرندگان');
insert into BIZ_PROFILE (id,code,name) values (2,'buyer','مشتریان');
insert into BIZ_PROFILE (id,code,name) values (3,'loantype','نوع وام');

insert into BIZ_MAPPING (f_profile,biz_id,biz_name,account_no) values (1,1,'دیجی کالا','000100');
insert into BIZ_MAPPING (f_profile,biz_id,biz_name,account_no) values (1,2,'پاکشوما','000101');
insert into BIZ_MAPPING (f_profile,biz_id,biz_name,account_no) values (2,1,'دیجی پی','000987');
insert into BIZ_MAPPING (f_profile,biz_id,biz_name,account_no) values (2,2,'علی علیزاده','000201');
insert into BIZ_MAPPING (f_profile,biz_id,biz_name,account_no) values (3,1,'پارسیان','000301');
insert into BIZ_MAPPING (f_profile,biz_id,biz_name,account_no) values (3,2,'کارآفرین','000302');
insert into BIZ_MAPPING (f_profile,biz_id,biz_name,account_no) values (3,3,'آسان خرید','000303');




--lines
insert into event (id,f_parent,hierarchy_id,name) values (1001,null,'.1001.','Payment');
insert into event (id,f_parent,hierarchy_id,name) values (1002,null,'.1002.','APP');
insert into event (id,f_parent,hierarchy_id,name) values (1003,null,'.1003.','Credit');
insert into event (id,f_parent,hierarchy_id,name) values (1009,null,'.1009.','unknown');
--payment products
insert into event (id,f_parent,hierarchy_id,name) values (1011,1001,'.1001.1011.','Smart IPG');
insert into event (id,f_parent,hierarchy_id,name) values (1012,1001,'.1001.1012.','SDK');
insert into event (id,f_parent,hierarchy_id,name) values (1013,1001,'.1001.1013.','Refund');
insert into event (id,f_parent,hierarchy_id,name) values (1014,1001,'.1001.1014.','Wallet - Payout');
insert into event (id,f_parent,hierarchy_id,name) values (1015,1001,'.1001.1015.','Wallet');
insert into event (id,f_parent,hierarchy_id,name) values (1016,1001,'.1001.1016.','Subscription');
insert into event (id,f_parent,hierarchy_id,name) values (1017,1001,'.1001.1017.','CNB');
--app products
insert into event (id,f_parent,hierarchy_id,name) values (1020,1002,'.1002.1020.','APP');
insert into event (id,f_parent,hierarchy_id,name) values (1021,1002,'.1002.1021.','Campaign');
--payment products
insert into event (id,f_parent,hierarchy_id,name) values (1031,1003,'.1003.1031.','Credit');
--payment & app products
--unknown
insert into event (id,f_parent,hierarchy_id,name) values (1091,null,'.1009.1091.','UPG');
insert into event (id,f_parent,hierarchy_id,name) values (1099,null,'.1009.1099.','unknown');

--transactionTypes
insert into event(id,f_parent,hierarchy_id,name)values(0,  1011, '.1001.1011.0' ,'خرید از طریق وب (دیجی کالا) - PURCHASE');
insert into event(id,f_parent,hierarchy_id,name)values(1,  1012, '.1001.1012.1' ,'خرید کالا از sdk  - IN_APP_PURCHASE');
insert into event(id,f_parent,hierarchy_id,name)values(2,  1099, '.1009.1099.2' ,' - PORTAL_PURCHASE');
insert into event(id,f_parent,hierarchy_id,name)values(3,  1013, '.1001.1013.3' ,'برگشت از فروش اتوماتیک _Refund-Auto√ - REFUND');
insert into event(id,f_parent,hierarchy_id,name)values(4,  1021, '.1002.1021.4' ,'چایزه - REWARD');
insert into event(id,f_parent,hierarchy_id,name)values(5,  1099, '.1009.1099.5' ,' اعتباری - CREDIT_PURCHASE');
insert into event(id,f_parent,hierarchy_id,name)values(10, 1014, '.1001.1014.10' ,' - PAYMENT_OFFLINE');
insert into event(id,f_parent,hierarchy_id,name)values(11, 1099, '.1009.1099.11' ,' - PAYMENT_REQUEST');
insert into event(id,f_parent,hierarchy_id,name)values(12, 1099, '.1009.1099.12' ,'پرداخت با کارت بانکی - PAYMENT_CARD');
insert into event(id,f_parent,hierarchy_id,name)values(13, 1099, '.1009.1099.13' ,'پرداخت از حساب بانکی - PAYMENT_ACH');
insert into event(id,f_parent,hierarchy_id,name)values(14, 1099, '.1009.1099.14' ,' - PAYMENT_HAMPAY');
insert into event(id,f_parent,hierarchy_id,name)values(15, 1099, '.1009.1099.15' ,'پرداخت از کیف پول  - PAYMENT_WALLET');
insert into event(id,f_parent,hierarchy_id,name)values(16, 1015, '.1001.1015.16' ,'واریزی نقدی به کیف پول - WALLET_CASH_IN');
insert into event(id,f_parent,hierarchy_id,name)values(17, 1015, '.1001.1015.17' ,'برداشت از کیف پول  - WALLET_CASH_OUT');
insert into event(id,f_parent,hierarchy_id,name)values(30, 1020, '.1002.1020.30' ,'خرید شارژ - TOP_UP');
insert into event(id,f_parent,hierarchy_id,name)values(31, 1020, '.1002.1020.31' ,'خرید بسته اینترنت - INTERNET_PACKAGE');
insert into event(id,f_parent,hierarchy_id,name)values(32, 1091, '.1009.1091.32' ,'پرداخت مستقیم با لینک مشخص - PAYMENT_LINK');
insert into event(id,f_parent,hierarchy_id,name)values(40, 1020, '.1002.1020.40' ,'پرداخت قبض  - UTILITY_BILL');
insert into event(id,f_parent,hierarchy_id,name)values(50, 1020, '.1002.1020.50' ,'بیمه - INSURANCE');
insert into event(id,f_parent,hierarchy_id,name)values(60, 1099, '.1009.1099.60' ,' - COMMERCIAL');
insert into event(id,f_parent,hierarchy_id,name)values(70, 1020, '.1002.1020.70' ,'عوارض آزادراه - TOLL');
insert into event(id,f_parent,hierarchy_id,name)values(80, 1020, '.1002.1020.80' ,'پیش پرداخت عوارض آزادراه - TOLL_PAYOFF');
insert into event(id,f_parent,hierarchy_id,name)values(90, 1013, '.1001.1013.90' ,'واریز به کارت بانکی - PAYOUT_CARD');
insert into event(id,f_parent,hierarchy_id,name)values(91, 1013, '.1001.1013.91' ,'بازگشت از فروش دستی Refund-Manual - REFUND_MANUAL');
insert into event(id,f_parent,hierarchy_id,name)values(92, 1020, '.1002.1020.92' ,'طرح ترافیک  - CONGESTION_PRICING');
insert into event(id,f_parent,hierarchy_id,name)values(93, 1013, '.1001.1013.93' ,'بازگشت از فروش به حساب بانکی-RefundCustom - REFUND_DIGIPAY');
insert into event(id,f_parent,hierarchy_id,name)values(94, 1011, '.1001.1011.94' ,' - REVERSE_MANUAL');
insert into event(id,f_parent,hierarchy_id,name)values(100, 1099, '.1009.1099.100' ,' - INSTALLMENT');
insert into event(id,f_parent,hierarchy_id,name)values(110, 1020, '.1002.1020.110' ,'خرید بلیت (فعلا نمک‌آبرود) - THIRD_PARTY_TICKET ');
insert into event(id,f_parent,hierarchy_id,name)values(111, 1099, '.1009.1099.111' ,' - CREDIT_INQUIRY');
insert into event(id,f_parent,hierarchy_id,name)values(112, 1020, '.1002.1020.112' ,'خیریه - DONATION');
insert into event(id,f_parent,hierarchy_id,name)values(113, 1020, '.1002.1020.113' ,'جریمه رانندگی - TRAFFIC_FINE');
insert into event(id,f_parent,hierarchy_id,name)values(120, 1017, '.1001.1017.120' ,' - PARTNER_AGENT_PAYOUT');
insert into event(id,f_parent,hierarchy_id,name)values(130, 1099, '.1009.1099.130' ,' - CREDIT_PREPAYMENT');
insert into event(id,f_parent,hierarchy_id,name)values(140, 1020, '.1002.1020.140' ,'پرداخت هزینه‌ی مینی‌اپ‌های فروشگاه‌سازها - MARKETPLACE');
insert into event(id,f_parent,hierarchy_id,name)values(150, 1020, '.1002.1020.150' ,'خرید سهام - STOCK');
insert into event(id,f_parent,hierarchy_id,name)values(160, 1016, '.1001.1016.160' ,'پرداخت خودکار - SUBSCRIPTION');
insert into event(id,f_parent,hierarchy_id,name)values(170, 1020, '.1002.1020.170' ,'پرداخت کرایه تاکسی - TAXI_PAYMENT');

insert into event(id,f_parent,hierarchy_id,name)values(1000, 1099, '.1009.1099.1000' ,'تست - Test');

insert into ACCOUNT (ID,F_PARENT, HIERARCHY_ID, ACCOUNT_NO, FULL_ACCOUNT_NO, ACCOUNT_NAME, ACCOUNT_TYPE, ACCOUNT_ORIGIN, IS_CUTOFF)
VALUES (1,null,'.',100,'100','حساب تست',1,0,0  );

insert into TEMPLATE (NAME, F_EVENT, ORDERNO, F_ACCOUNT_BASE)
VALUES ( 'الگوی تست',1000,1, 1);
