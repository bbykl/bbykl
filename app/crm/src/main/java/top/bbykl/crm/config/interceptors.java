package top.bbykl.crm.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.bbykl.crm.setting.controller.interceptor.loginInterceptor;

/**
 * @author 盛祥进
 * @version 1.0
 * @description: TODO
 * @date 2022/11/17 14:38
 */
public class interceptors implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor()).addPathPatterns("/settings/**","/workbench/**").excludePathPatterns("/settings/qx/user/login.do","/settings/qx/user/login");
    }
}
