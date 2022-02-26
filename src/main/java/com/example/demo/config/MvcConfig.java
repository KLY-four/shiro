package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/index","/templates/index.html");
        registry.addRedirectViewController("/register", "/templates/user/register.html");
        registry.addRedirectViewController("/login","/templates/user/login.html");
        registry.addRedirectViewController("/toJd","/templates/jd.html");
        registry.addRedirectViewController("/","/templates/user/login.html");
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        final InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/static/");
        internalResourceViewResolver.setSuffix(".html");
        return internalResourceViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/user/**").addResourceLocations("classpath:/templates/user/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    }
}
