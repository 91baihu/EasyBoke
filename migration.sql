-- ============================================
-- EasyBoke 数据库迁移脚本
-- 新增标签、访问日志、点赞持久化相关表
-- ============================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 1. 视频标签表 (video_tag)
-- ----------------------------
DROP TABLE IF EXISTS `video_tag`;
CREATE TABLE `video_tag` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '标签ID',
    `tag_name` varchar(50) NOT NULL COMMENT '标签名称',
    `use_count` int DEFAULT 0 COMMENT '使用次数',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tag_name` (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频标签表';

-- ----------------------------
-- 2. 视频-标签关联表 (video_tag_relation)
-- ----------------------------
DROP TABLE IF EXISTS `video_tag_relation`;
CREATE TABLE `video_tag_relation` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `video_id` int NOT NULL COMMENT '视频ID',
    `tag_id` int NOT NULL COMMENT '标签ID',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_video_tag` (`video_id`, `tag_id`),
    KEY `idx_video_id` (`video_id`),
    KEY `idx_tag_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频标签关联表';

-- ----------------------------
-- 3. 视频点赞表 (video_like) — 持久化点赞记录
-- ----------------------------
DROP TABLE IF EXISTS `video_like`;
CREATE TABLE `video_like` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
    `video_id` int NOT NULL COMMENT '视频ID',
    `user_id` int NOT NULL COMMENT '用户ID',
    `create_time` datetime DEFAULT NULL COMMENT '点赞时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_video_user` (`video_id`, `user_id`),
    KEY `idx_video_id` (`video_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频点赞记录表';

-- ----------------------------
-- 4. 视频访问日志表 (video_access_log)
-- ----------------------------
DROP TABLE IF EXISTS `video_access_log`;
CREATE TABLE `video_access_log` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `video_id` int NOT NULL COMMENT '视频ID',
    `user_id` int DEFAULT NULL COMMENT '用户ID（未登录可为空）',
    `ip_address` varchar(50) DEFAULT NULL COMMENT '访问IP',
    `user_agent` varchar(500) DEFAULT NULL COMMENT '设备信息',
    `referer` varchar(500) DEFAULT NULL COMMENT '来源页面',
    `access_time` datetime DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`id`),
    KEY `idx_video_id` (`video_id`),
    KEY `idx_ip_address` (`ip_address`),
    KEY `idx_access_time` (`access_time`),
    KEY `idx_video_ip_time` (`video_id`, `ip_address`, `access_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频访问日志表';

-- ----------------------------
-- 5. 确保video表包含必要字段（如不存在则添加）
-- ----------------------------
-- 检查并添加 like_count 字段
SET @exist_like_count = (SELECT COUNT(*) FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = 'easyboke' AND TABLE_NAME = 'video' AND COLUMN_NAME = 'like_count');
SET @sql_like = IF(@exist_like_count = 0,
    'ALTER TABLE video ADD COLUMN like_count int DEFAULT 0 COMMENT ''点赞数'' AFTER age_above_45',
    'SELECT ''like_count already exists''');
PREPARE stmt FROM @sql_like; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- 检查并添加 comment_count 字段
SET @exist_comment = (SELECT COUNT(*) FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = 'easyboke' AND TABLE_NAME = 'video' AND COLUMN_NAME = 'comment_count');
SET @sql_comment = IF(@exist_comment = 0,
    'ALTER TABLE video ADD COLUMN comment_count int DEFAULT 0 COMMENT ''评论数'' AFTER like_count',
    'SELECT ''comment_count already exists''');
PREPARE stmt FROM @sql_comment; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- 检查并添加 update_time 字段
SET @exist_update = (SELECT COUNT(*) FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = 'easyboke' AND TABLE_NAME = 'video' AND COLUMN_NAME = 'update_time');
SET @sql_update = IF(@exist_update = 0,
    'ALTER TABLE video ADD COLUMN update_time datetime DEFAULT NULL COMMENT ''更新时间'' AFTER create_time',
    'SELECT ''update_time already exists''');
PREPARE stmt FROM @sql_update; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- 检查并添加 duration 字段
SET @exist_duration = (SELECT COUNT(*) FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = 'easyboke' AND TABLE_NAME = 'video' AND COLUMN_NAME = 'duration');
SET @sql_duration = IF(@exist_duration = 0,
    'ALTER TABLE video ADD COLUMN duration int DEFAULT NULL COMMENT ''视频时长（秒）'' AFTER cover_image',
    'SELECT ''duration already exists''');
PREPARE stmt FROM @sql_duration; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET FOREIGN_KEY_CHECKS = 1;

-- ============================================
-- 迁移完成
-- ============================================
