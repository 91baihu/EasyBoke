package com.easyboke.utils;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 密码重置令牌管理器（内存存储）
 * 生产环境建议使用数据库存储
 */
@Component
public class PasswordResetTokenManager {

    /**
     * 令牌存储：token -> TokenInfo
     */
    private static final Map<String, TokenInfo> TOKEN_MAP = new ConcurrentHashMap<>();

    /**
     * 令牌有效期：24小时（毫秒）
     */
    private static final long EXPIRY_MS = 24 * 60 * 60 * 1000L;

    /**
     * 生成重置令牌
     * @param account 账号（用户名或邮箱）
     * @param type 类型：user 或 admin
     * @return 生成的token
     */
    public String generateToken(String account, String type) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenInfo info = new TokenInfo(account, type, System.currentTimeMillis() + EXPIRY_MS);
        TOKEN_MAP.put(token, info);
        return token;
    }

    /**
     * 验证令牌是否有效
     * @param token 令牌
     * @return 令牌信息（若有效），null（若无效或已过期）
     */
    public TokenInfo validateToken(String token) {
        TokenInfo info = TOKEN_MAP.get(token);
        if (info == null) {
            return null;
        }
        // 检查是否过期
        if (System.currentTimeMillis() > info.getExpiryTime()) {
            TOKEN_MAP.remove(token);
            return null;
        }
        return info;
    }

    /**
     * 使用令牌后移除（防止重复使用）
     */
    public void removeToken(String token) {
        TOKEN_MAP.remove(token);
    }

    /**
     * 令牌信息
     */
    public static class TokenInfo {
        /** 关联的账号 */
        private final String account;
        /** 类型：user 或 admin */
        private final String type;
        /** 过期时间戳 */
        private final long expiryTime;

        public TokenInfo(String account, String type, long expiryTime) {
            this.account = account;
            this.type = type;
            this.expiryTime = expiryTime;
        }

        public String getAccount() {
            return account;
        }

        public String getType() {
            return type;
        }

        public long getExpiryTime() {
            return expiryTime;
        }
    }
}
