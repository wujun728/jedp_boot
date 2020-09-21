ALTER TABLE `user`
ADD COLUMN `salt`  varchar(36) NULL COMMENT '密码加密盐' AFTER `password`;