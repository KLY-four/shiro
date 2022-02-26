package com.example.demo.config;

import com.example.demo.realm.MyRealm;
import com.example.demo.redisCacheConfig.RedisCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(Realm realm) {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/userLogin");
        factoryBean.setSuccessUrl("/index");
        factoryBean.setUnauthorizedUrl("/unAuth");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/index.html", "authc");
        map.put("/login", "anon");
        map.put("/register","anon");
        map.put("/logout", "logout");
        map.put("/save/user","anon");
//        map.put("/jd", "perms[jd:1:2]");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        final AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public Realm realm(){

        final MyRealm myRealm = new MyRealm();
        final HashedCredentialsMatcher md5 = new HashedCredentialsMatcher("md5");
        md5.setHashIterations(1024);
        myRealm.setCredentialsMatcher(md5);

        /**
         * EhCache缓存框架(shiro默认的缓存组件)，不是一个第三方应用，所以使用该缓存实际上数据还是缓存在自身应用内存中
         * 这样会被该应用造成一定的压力，所以我们也可以使用redis这样的第三方缓存。
         * */
        //设置缓存
       // myRealm.setCacheManager(new EhCacheManager());
        //打开缓存功能
        //myRealm.setCachingEnabled(true);
        //开启认证和授权的缓存
        //myRealm.setAuthenticationCachingEnabled(true);
        //myRealm.setAuthorizationCachingEnabled(true);
        //return myRealm;

        /**
         * 配置redis缓存
         * */
         myRealm.setCacheManager(new RedisCacheManager());
        myRealm.setCachingEnabled(true);
        myRealm.setAuthenticationCachingEnabled(true);
        myRealm.setAuthorizationCachingEnabled(true);
        myRealm.setAuthenticationCacheName("authentication");
        myRealm.setAuthorizationCacheName("authorization");
        return myRealm;
    }
}

































































































































