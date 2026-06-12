-- 将所有用户和管理员邮箱绑定为 3348098119@qq.com
UPDATE user SET email = '3348098119@qq.com';
UPDATE admin SET email = '3348098119@qq.com';

-- 验证结果
SELECT id, username, email FROM user;
SELECT id, username, email FROM admin;
