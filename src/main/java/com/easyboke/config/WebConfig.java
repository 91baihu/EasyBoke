package com.easyboke.config;

import com.easyboke.mappers.AdminMapper;
import com.easyboke.mappers.UserMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(adminMapper))
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login", "/admin/logout", "/admin/forgotPassword", "/admin/resetPassword")
                .excludePathPatterns("/**/*.html", "/css/**", "/js/**", "/images/**");
        registry.addInterceptor(new UserLoginInterceptor(userMapper))
                .addPathPatterns("/user/profile/**", "/comment/publish", "/comment/reply")
                .excludePathPatterns("/user/login", "/user/register", "/user/logout",
                        "/user/forgotPassword", "/user/resetPassword")
                .excludePathPatterns("/**/*.html", "/css/**", "/js/**", "/images/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
