package com.exam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security配置类
 * 配置登录接口允许匿名访问
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置HTTP安全策略
     * 允许登录接口匿名访问
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 允许所有请求访问，关闭CSRF保护
            .authorizeRequests()
            // 允许登录接口匿名访问
            .antMatchers("/login").permitAll()
            .antMatchers("/logout").permitAll()
            // 其他请求需要认证
            .anyRequest().authenticated()
            .and()
            // 关闭CSRF保护，方便测试
            .csrf().disable();
    }
}
