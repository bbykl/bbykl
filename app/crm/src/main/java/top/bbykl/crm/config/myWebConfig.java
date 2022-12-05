package top.bbykl.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import top.bbykl.crm.setting.controller.interceptor.loginInterceptor;

@Configuration
public class myWebConfig {
    /**
     * 自定义视图控制
     * @return
     */
    @Bean("myViewControl")
    public viewControl viewControl(){
        return new viewControl();
    }

    /**
     * thymleaf模板引擎
     * @return
     */
    @Bean("myViewResolver")
    public viewResolver viewResolver(){
        return new viewResolver();
    }

    /**
     *
     * 拦截器的配置
     * @return:
     */
    @Bean("loginInterceptor")
    public interceptors loginInterceptor(){
        return new interceptors();
    }
    /**
     *
     *  文件上传配置（表单编码方式以及内存要求）
     * @return:
     */
//    @Bean("multipartResolver")
//    public fileUpload fileUpload(){
//        return new fileUpload();
//    }
}
