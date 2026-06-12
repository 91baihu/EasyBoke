package com.easyboke.common;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class Constants {
    public static final String SESSION_KEY_ADMIN = "session_admin";

    public static final String SESSION_KEY_USER = "session_user";

    public static final Integer STATUS_ENABLED = 1;

    public static final Integer STATUS_DISABLED = 0;

    /** 点赞缓存（视频点赞已迁移至DB，文章点赞暂用缓存） */
    public static final Map<String, Object> LIKE_CACHE = new ConcurrentHashMap<>();

    /** 用户ID -> SessionID 映射（用于单设备登录控制） */
    public static final Map<Integer, String> USER_SESSION_MAP = new ConcurrentHashMap<>();

    /** 管理员ID -> SessionID 映射（用于单设备登录控制） */
    public static final Map<Integer, String> ADMIN_SESSION_MAP = new ConcurrentHashMap<>();
}
