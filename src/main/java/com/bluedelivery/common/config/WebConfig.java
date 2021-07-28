package com.bluedelivery.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bluedelivery.api.authentication.AuthenticatedUserArgumentResolver;
import com.bluedelivery.api.authentication.UserAuthInterceptor;
import com.bluedelivery.application.authentication.AuthenticationService;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Autowired
    private AuthenticationService authService;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAuthInterceptor(authService));
    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticatedUserArgumentResolver(authService));
    }
}
