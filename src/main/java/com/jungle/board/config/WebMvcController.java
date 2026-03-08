package com.jungle.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jungle.board.interceptor.DefaultInterceptor;

@Configuration
public class WebMvcController implements WebMvcConfigurer{
    
    private final DefaultInterceptor defaultInterceptor;

    public WebMvcController(DefaultInterceptor defaultInterceptor) {
        this.defaultInterceptor = defaultInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(defaultInterceptor)
            .excludePathPatterns("/api/member/**");
    }

}
