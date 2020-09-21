ALTER TABLE `resource`
ADD COLUMN `opened` tinyint(2) NOT NULL DEFAULT 1 AFTER `status`;