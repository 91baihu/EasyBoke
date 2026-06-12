package com.easyboke.config;

import com.easyboke.common.Constants;
import com.easyboke.entity.po.User;
import com.easyboke.mappers.UserMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginInterceptor implements HandlerInterceptor {

    private final UserMapper userMapper;

    public UserLoginInterceptor(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_KEY_USER);

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"status\":\"error\",\"code\":401,\"info\":\"未登录或登录已过期\",\"data\":null}");
            return false;
        }

        // 单设备登录控制：检查session是否被其他登录踢下线
        String registeredSessionId = Constants.USER_SESSION_MAP.get(user.getId());
        if (registeredSessionId != null && !registeredSessionId.equals(session.getId())) {
            session.removeAttribute(Constants.SESSION_KEY_USER);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"status\":\"error\",\"code\":401,\"info\":\"您的账号在其他地方登录，已被迫下线\",\"data\":null}");
            return false;
        }

        // 检查用户是否被禁用
        User dbUser = (User) userMapper.selectById(user.getId());
        if (dbUser != null && Constants.STATUS_DISABLED.equals(dbUser.getStatus())) {
            Constants.USER_SESSION_MAP.remove(user.getId());
            session.removeAttribute(Constants.SESSION_KEY_USER);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"status\":\"error\",\"code\":401,\"info\":\"您的账号已被禁用，请联系管理员\",\"data\":null}");
            return false;
        }

        return true;
    }
}