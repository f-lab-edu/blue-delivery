package com.delivery.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.delivery.config.interceptor.LoginInterceptor;
import com.delivery.config.interceptor.SessionRepository;
import com.delivery.config.interceptor.UserAuthInterceptor;
import com.delivery.config.resolver.AuthenticatedUserArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Autowired
    private SessionRepository sessionRepository;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(sessionRepository));
        registry.addInterceptor(new UserAuthInterceptor(sessionRepository));
    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticatedUserArgumentResolver());
    }
}
