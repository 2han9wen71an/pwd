/*
 Navicat Premium Data Transfer

 Source Server         : Oracle
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : localhost:1521
 Source Schema         : SCOTT

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 07/05/2019 16:49:16
*/


-- ----------------------------
-- Table structure for PWD_LOG
-- ----------------------------
DROP TABLE "SCOTT"."PWD_LOG";
CREATE TABLE "SCOTT"."PWD_LOG" (
  "ID" NUMBER NOT NULL ,
  "USERID" NUMBER NOT NULL ,
  "USERNAME" VARCHAR2(64 BYTE) NOT NULL ,
  "EV" NUMBER ,
  "URL" VARCHAR2(64 BYTE) NOT NULL ,
  "IP" VARCHAR2(64 BYTE) NOT NULL ,
  "UA" VARCHAR2(64 BYTE) NOT NULL ,
  "JSON" VARCHAR2(64 BYTE) ,
  "TIME" VARCHAR2(64 BYTE) ,
  "RECORD" VARCHAR2(64 BYTE) 
)
TABLESPACE "USERS"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for PWD_NOTEPAD
-- ----------------------------
DROP TABLE "SCOTT"."PWD_NOTEPAD";
CREATE TABLE "SCOTT"."PWD_NOTEPAD" (
  "ID" NUMBER NOT NULL ,
  "USERID" NUMBER NOT NULL ,
  "TITLE" VARCHAR2(64 BYTE) NOT NULL ,
  "CONTENT" VARCHAR2(64 BYTE) NOT NULL ,
  "STIME" VARCHAR2(64 BYTE) NOT NULL ,
  "TYPE" NUMBER NOT NULL 
)
TABLESPACE "USERS"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of PWD_NOTEPAD
-- ----------------------------
INSERT INTO "SCOTT"."PWD_NOTEPAD" VALUES ('22', '2', '1', '1', '2019-05-07 15:25:15', '0');
INSERT INTO "SCOTT"."PWD_NOTEPAD" VALUES ('21', '16', '1', '1', '2019-05-07 14:24:35', '0');
INSERT INTO "SCOTT"."PWD_NOTEPAD" VALUES ('2', '2', 'zztest', 'zztest', '2019-05-05 15:20:48', '0');

-- ----------------------------
-- Table structure for PWD_NOTICE
-- ----------------------------
DROP TABLE "SCOTT"."PWD_NOTICE";
CREATE TABLE "SCOTT"."PWD_NOTICE" (
  "ID" NUMBER NOT NULL ,
  "CONTENT" VARCHAR2(64 BYTE) ,
  "TIME" VARCHAR2(64 BYTE) ,
  "GRADE" NUMBER 
)
TABLESPACE "USERS"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for PWD_PLAN
-- ----------------------------
DROP TABLE "SCOTT"."PWD_PLAN";
CREATE TABLE "SCOTT"."PWD_PLAN" (
  "ID" NUMBER NOT NULL ,
  "USERID" NUMBER ,
  "USERNAME" VARCHAR2(64 BYTE) ,
  "EMAIL" VARCHAR2(64 BYTE) ,
  "STIME" VARCHAR2(64 BYTE) ,
  "TYPE" NUMBER ,
  "CONTENT" VARCHAR2(500 BYTE) ,
  "STATUS" NUMBER 
)
TABLESPACE "USERS"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of PWD_PLAN
-- ----------------------------
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('13', '2', 'zzzwt', '991845135@qq.com', '1557282524786', '1', '亲爱的zzzwt，您好<br/>您在本章托管保存的账号密码已经达到你设置的保管时间，为了安全起见请尽快到本站修改所保存的账号密码<br/><a href=''/pwd/admin/plan.do''>/pwd/admin/plan.do</a><br/>温馨提示：账号密码都不要长时间不修改。也不要使用同样的密码.<br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问<br/>如果此次请求非你本人所发，请忽略本邮件。', '0');
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('18', '16', '18674367434', 'zhangwentian@vip.qq.com', '1557210645583', '3', '亲爱的18674367434：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href=''http://localhost:8080/pwd/user.do?method=reset_pwd_verify&reset_pwd_verify=SdkP'' target=''_blank''>http://localhost:8080?method=reset_pwd_verify&reset_pwd_verify=SdkP</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>', '0');
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('17', '2', 'zzzwt', '991845135@qq.com', '1557203100000', '3', '亲爱的zzzwt：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href=''http://localhost:8080/pwd/user.do?method=reset_pwd&reset_pwd_verify=61d66ec14517593d9ac703a954505660'' target=''_blank''>http://localhost:8080/pwd/user.do?method=reset_pwd&reset_pwd_verify=61d66ec14517593d9ac703a954505660</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>', '1');
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('19', '16', '18674367434', 'zhangwentian@vip.qq.com', '1557210915802', '3', '亲爱的18674367434：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href=''http://localhost:8080/pwd/user.do?method=reset_pwd_verify&reset_pwd_verify=9bb244b7fb0926669b2f888e427b754d'' target=''_blank''>http://localhost:8080?method=reset_pwd_verify&reset_pwd_verify=9bb244b7fb0926669b2f888e427b754d</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>', '0');
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('20', '16', '18674367434', 'zhangwentian@vip.qq.com', '1557211095855', '3', '亲爱的18674367434：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href=''http://localhost:8080/pwd/user.do?method=reset_pwd_verify&reset_pwd_verify=cbdc213ab5430129f196d049341b7d54'' target=''_blank''>http://localhost:8080?method=reset_pwd_verify&reset_pwd_verify=cbdc213ab5430129f196d049341b7d54</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>', '0');
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('21', '16', '18674367434', 'zhangwentian@vip.qq.com', '1557297629703', '3', '亲爱的18674367434：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href=''http://localhost:8080/pwd/user.do?method=reset_pwd_verify&reset_pwd_verify=9571b40021958c3e178fba81e1441dc7'' target=''_blank''>http://localhost:8080?method=reset_pwd_verify&reset_pwd_verify=9571b40021958c3e178fba81e1441dc7</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>', '1');
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('23', '16', '18674367434', 'zhangwentian@vip.qq.com', '1557298470939', '3', '亲爱的18674367434：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href=''http://localhost:8080/pwd/user.do?method=resetpwd&reset_pwd_verify=dfa7438aadd7800c7d1d9b365125ea49'' target=''_blank''>http://localhost:8080/pwd/user.do?method=resetpwd&reset_pwd_verify=dfa7438aadd7800c7d1d9b365125ea49</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>', '1');
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('24', '16', '18674367434', 'zhangwentian@vip.qq.com', '1557298718544', '3', '亲爱的18674367434：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href=''http://localhost:8080/pwd/user.do?method=resetpwd&reset_pwd_verify=59722b548be41610c82d3d150265d2a0'' target=''_blank''>http://localhost:8080/pwd/user.do?method=resetpwd&reset_pwd_verify=59722b548be41610c82d3d150265d2a0</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>', '1');
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('22', '16', '18674367434', 'zhangwentian@vip.qq.com', '1557298104717', '3', '亲爱的18674367434：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href=''http://localhost:8080/pwd/user.do?method=reset_pwd_verify&reset_pwd_verify=aba4193cd9b9499fb33529a0c0ec353c'' target=''_blank''>http://localhost:8080/pwd/user.do?method=reset_pwd_verify&reset_pwd_verify=aba4193cd9b9499fb33529a0c0ec353c</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>', '1');
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('25', '2', 'zzzwt', '991845135@qq.com', '1557300516997', '3', '亲爱的zzzwt：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href=''http://localhost:8080/pwd/user.do?method=resetpwd&reset_pwd_verify=a5b557bf1518eb711f6399f2662261ee'' target=''_blank''>http://localhost:8080/pwd/user.do?method=resetpwd&reset_pwd_verify=a5b557bf1518eb711f6399f2662261ee</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>', '1');
INSERT INTO "SCOTT"."PWD_PLAN" VALUES ('26', '20', '18674367434', 'zhangwentian@vip.qq.com', '1557301002376', '3', '亲爱的18674367434：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href=''http://localhost:8080/pwd/user.do?method=resetpwd&reset_pwd_verify=e49ffe707675c167d1990673287fb79c'' target=''_blank''>http://localhost:8080/pwd/user.do?method=resetpwd&reset_pwd_verify=e49ffe707675c167d1990673287fb79c</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>', '1');

-- ----------------------------
-- Table structure for PWD_PWD
-- ----------------------------
DROP TABLE "SCOTT"."PWD_PWD";
CREATE TABLE "SCOTT"."PWD_PWD" (
  "ID" NUMBER NOT NULL ,
  "USERID" NUMBER NOT NULL ,
  "TITLE" VARCHAR2(64 BYTE) ,
  "DESCR" VARCHAR2(64 BYTE) ,
  "USERNAME" VARCHAR2(64 BYTE) NOT NULL ,
  "PASS" VARCHAR2(255 BYTE) NOT NULL ,
  "WEBURL" VARCHAR2(64 BYTE) ,
  "INTIME" VARCHAR2(64 BYTE) ,
  "LASTTIME" VARCHAR2(64 BYTE) NOT NULL ,
  "TPASS" VARCHAR2(64 BYTE) NOT NULL ,
  "STATUS" NUMBER NOT NULL ,
  "TYPE" NUMBER NOT NULL 
)
TABLESPACE "USERS"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of PWD_PWD
-- ----------------------------
INSERT INTO "SCOTT"."PWD_PWD" VALUES ('4', '2', 'zztest', 'zztest', 'zztest', '1892CW4iw7nCncKULcK2wohyHDdJFMOswpTClgHClcOiw53CvDk7wr7CqsOYwpzCu8KWwoQq', 'zztest', '2019-05-05 15:11:10', '2019-05-05 15:11:10', 'YtyjmCSy', '1', '0');
INSERT INTO "SCOTT"."PWD_PWD" VALUES ('6', '2', 'test', 'test', 'test', '517cw77Cp0LCiWgewrrDhQhDNm7DnMOBwqcDw4BCFcOjQ3RlIkEwNAUhTcKkZsKXFw==', 'test', '2019-05-07 15:23:10', '2019-05-07 15:23:10', '202cb962ac59075b964b07152d234b70', '1', '1');
INSERT INTO "SCOTT"."PWD_PWD" VALUES ('5', '1', 'test', 'test', 'test', 'a8bawq3Cl8OVOxYtw6AFQHYiw4DDoF3CpF5PwqYHwotSH8Krwps6DcOkwp4OHMOANcKzwrA=', 'test', '2019-05-05 16:39:18', '2019-05-05 16:39:18', '7090535b2dfe6879f28549fa5b0cfdba', '1', '1');

-- ----------------------------
-- Table structure for PWD_SET
-- ----------------------------
DROP TABLE "SCOTT"."PWD_SET";
CREATE TABLE "SCOTT"."PWD_SET" (
  "ID" NUMBER NOT NULL ,
  "USERNAME" VARCHAR2(64 BYTE) NOT NULL ,
  "PASS" VARCHAR2(64 BYTE) NOT NULL ,
  "QQ" NUMBER NOT NULL ,
  "EMAIL" VARCHAR2(64 BYTE) NOT NULL ,
  "TITLE" VARCHAR2(64 BYTE) ,
  "DESCRIBE" VARCHAR2(64 BYTE) ,
  "KEYWORDS" VARCHAR2(64 BYTE) ,
  "SIGN" NUMBER NOT NULL ,
  "EMAIL_SIGN" NUMBER ,
  "IPADMIN" VARCHAR2(64 BYTE) 
)
TABLESPACE "USERS"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of PWD_SET
-- ----------------------------
INSERT INTO "SCOTT"."PWD_SET" VALUES ('1', 'zhangwentian', '6a4bcd2e92a2e1ee7108f86f7ee481db', '862163687', '862163687@qq.com', '标题', '描述', '关键词1', '1', '1', ' ');

-- ----------------------------
-- Table structure for PWD_SMTP
-- ----------------------------
DROP TABLE "SCOTT"."PWD_SMTP";
CREATE TABLE "SCOTT"."PWD_SMTP" (
  "ID" NUMBER NOT NULL ,
  "HOST" VARCHAR2(64 BYTE) NOT NULL ,
  "PORT" NUMBER NOT NULL ,
  "USERNAME" VARCHAR2(64 BYTE) NOT NULL ,
  "PASSWORD" VARCHAR2(64 BYTE) NOT NULL ,
  "SUB" VARCHAR2(64 BYTE) NOT NULL ,
  "SSL" NUMBER NOT NULL 
)
TABLESPACE "USERS"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of PWD_SMTP
-- ----------------------------
INSERT INTO "SCOTT"."PWD_SMTP" VALUES ('1', 'smtp.163.com', '465', '18674367434@163.com', 'to00i0toi9', 'Zzz', '1');

-- ----------------------------
-- Table structure for PWD_USER
-- ----------------------------
DROP TABLE "SCOTT"."PWD_USER";
CREATE TABLE "SCOTT"."PWD_USER" (
  "ID" NUMBER NOT NULL ,
  "USERNAME" VARCHAR2(64 BYTE) NOT NULL ,
  "PASS" VARCHAR2(64 BYTE) NOT NULL ,
  "EMAIL" VARCHAR2(64 BYTE) NOT NULL ,
  "QQ" NUMBER ,
  "STIME" VARCHAR2(64 BYTE) ,
  "LTIME" VARCHAR2(64 BYTE) NOT NULL ,
  "SIP" VARCHAR2(64 BYTE) ,
  "LIP" VARCHAR2(64 BYTE) NOT NULL ,
  "TOKEN" VARCHAR2(64 BYTE) NOT NULL ,
  "STATUS" NUMBER NOT NULL ,
  "ERROR_NUM" NUMBER NOT NULL ,
  "EMAIL_TOKEN" VARCHAR2(64 BYTE) ,
  "TOKEN_EXPTIME" LONG 
)
TABLESPACE "USERS"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of PWD_USER
-- ----------------------------
INSERT INTO "SCOTT"."PWD_USER" VALUES ('20', '18674367434', '00fab78bcccb3a9e44f1aa21d90d972b', 'zhangwentian@vip.qq.com', '0', '2019-05-07 15:32:23', '2019-05-07 15:32:23', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', 'QsxGvxmy', '1', '0', 'e49ffe707675c167d1990673287fb79c', '1557301002376');
INSERT INTO "SCOTT"."PWD_USER" VALUES ('2', 'zzzwt', '6a4bcd2e92a2e1ee7108f86f7ee481db', '991845135@qq.com', '0', '2019-05-05 13:41:48', '2019-05-07 15:21:38', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', 'Cyvj', '0', '0', 'a5b557bf1518eb711f6399f2662261ee', '1557300516997');
INSERT INTO "SCOTT"."PWD_USER" VALUES ('1', 'zhangwentian', '6a4bcd2e92a2e1ee7108f86f7ee481db', '862163687@qq.com', '862163687', ' ', '2019-05-07 15:26:29', ' ', '0:0:0:0:0:0:0:1', 'JFEn', '1', '2', '123', NULL);

-- ----------------------------
-- Primary Key structure for table PWD_LOG
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_LOG" ADD CONSTRAINT "SYS_C0011057" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table PWD_LOG
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_LOG" ADD CONSTRAINT "SYS_C0011119" CHECK ("USERID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_LOG" ADD CONSTRAINT "SYS_C0011120" CHECK ("USERNAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_LOG" ADD CONSTRAINT "SYS_C0011121" CHECK ("URL" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_LOG" ADD CONSTRAINT "SYS_C0011122" CHECK ("IP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_LOG" ADD CONSTRAINT "SYS_C0011123" CHECK ("UA" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table PWD_NOTEPAD
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_NOTEPAD" ADD CONSTRAINT "SYS_C0011058" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table PWD_NOTEPAD
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_NOTEPAD" ADD CONSTRAINT "SYS_C0011124" CHECK ("USERID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_NOTEPAD" ADD CONSTRAINT "SYS_C0011125" CHECK ("TITLE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_NOTEPAD" ADD CONSTRAINT "SYS_C0011126" CHECK ("CONTENT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_NOTEPAD" ADD CONSTRAINT "SYS_C0011127" CHECK ("STIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_NOTEPAD" ADD CONSTRAINT "SYS_C0011128" CHECK ("TYPE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table PWD_NOTICE
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_NOTICE" ADD CONSTRAINT "SYS_C0011059" PRIMARY KEY ("ID");

-- ----------------------------
-- Primary Key structure for table PWD_PLAN
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_PLAN" ADD CONSTRAINT "SYS_C0011174" PRIMARY KEY ("ID");

-- ----------------------------
-- Primary Key structure for table PWD_PWD
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_PWD" ADD CONSTRAINT "SYS_C0011061" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table PWD_PWD
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_PWD" ADD CONSTRAINT "SYS_C0011136" CHECK ("USERID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_PWD" ADD CONSTRAINT "SYS_C0011137" CHECK ("USERNAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_PWD" ADD CONSTRAINT "SYS_C0011138" CHECK ("PASS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_PWD" ADD CONSTRAINT "SYS_C0011139" CHECK ("LASTTIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_PWD" ADD CONSTRAINT "SYS_C0011140" CHECK ("TPASS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_PWD" ADD CONSTRAINT "SYS_C0011141" CHECK ("STATUS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_PWD" ADD CONSTRAINT "SYS_C0011142" CHECK ("TYPE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table PWD_SET
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_SET" ADD CONSTRAINT "SYS_C0011062" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table PWD_SET
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_SET" ADD CONSTRAINT "SYS_C0011144" CHECK ("USERNAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_SET" ADD CONSTRAINT "SYS_C0011145" CHECK ("PASS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_SET" ADD CONSTRAINT "SYS_C0011146" CHECK ("QQ" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_SET" ADD CONSTRAINT "SYS_C0011147" CHECK ("EMAIL" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_SET" ADD CONSTRAINT "SYS_C0011148" CHECK ("SIGN" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table PWD_SMTP
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_SMTP" ADD CONSTRAINT "SYS_C0011063" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table PWD_SMTP
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_SMTP" ADD CONSTRAINT "SYS_C0011149" CHECK ("HOST" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_SMTP" ADD CONSTRAINT "SYS_C0011150" CHECK ("PORT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_SMTP" ADD CONSTRAINT "SYS_C0011151" CHECK ("USERNAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_SMTP" ADD CONSTRAINT "SYS_C0011152" CHECK ("PASSWORD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_SMTP" ADD CONSTRAINT "SYS_C0011153" CHECK ("SUB" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_SMTP" ADD CONSTRAINT "SYS_C0011154" CHECK ("SSL" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table PWD_USER
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_USER" ADD CONSTRAINT "SYS_C0011064" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table PWD_USER
-- ----------------------------
ALTER TABLE "SCOTT"."PWD_USER" ADD CONSTRAINT "SYS_C0011111" CHECK ("USERNAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_USER" ADD CONSTRAINT "SYS_C0011112" CHECK ("PASS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_USER" ADD CONSTRAINT "SYS_C0011113" CHECK ("EMAIL" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_USER" ADD CONSTRAINT "SYS_C0011114" CHECK ("LTIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_USER" ADD CONSTRAINT "SYS_C0011115" CHECK ("LIP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_USER" ADD CONSTRAINT "SYS_C0011116" CHECK ("TOKEN" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_USER" ADD CONSTRAINT "SYS_C0011117" CHECK ("STATUS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SCOTT"."PWD_USER" ADD CONSTRAINT "SYS_C0011118" CHECK ("ERROR_NUM" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
