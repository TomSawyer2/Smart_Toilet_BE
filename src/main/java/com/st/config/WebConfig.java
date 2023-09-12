package com.st.config;

import com.st.common.interceptor.AuthInterceptor;
import com.st.common.util.JwtUtils;
import com.st.modules.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserMapper userMapper;

    /**
     * 添加拦截器
     *
     * @param registry 注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(jwtUtils, userMapper)).addPathPatterns("/**");
    }
}
