package com.easyboke.config;

import com.easyboke.common.Constants;
import com.easyboke.entity.po.Admin;
import com.easyboke.mappers.AdminMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录验证拦截器
 * 用于检查管理员是否已登录，未登录用户无法访问受保护的资源
 */
public class LoginInterceptor implements HandlerInterceptor {

    private final AdminMapper adminMapper;

    public LoginInterceptor(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    /**
     * 前置处理方法，在控制器方法执行前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(Constants.SESSION_KEY_ADMIN);

        if (admin == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"status\":\"error\",\"code\":401,\"info\":\"未登录或登录已过期\",\"data\":null}");
            return false;
        }

        // 单设备登录控制
        String registeredSessionId = Constants.ADMIN_SESSION_MAP.get(admin.getId());
        if (registeredSessionId != null && !registeredSessionId.equals(session.getId())) {
            session.removeAttribute(Constants.SESSION_KEY_ADMIN);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"status\":\"error\",\"code\":401,\"info\":\"您的账号在其他地方登录，已被迫下线\",\"data\":null}");
            return false;
        }

        // 检查管理员是否被禁用
        Admin dbAdmin = (Admin) adminMapper.selectById(admin.getId());
        if (dbAdmin != null && Constants.STATUS_DISABLED.equals(dbAdmin.getStatus())) {
            Constants.ADMIN_SESSION_MAP.remove(admin.getId());
            session.removeAttribute(Constants.SESSION_KEY_ADMIN);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"status\":\"error\",\"code\":401,\"info\":\"账号已被禁用，请联系超级管理员\",\"data\":null}");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}