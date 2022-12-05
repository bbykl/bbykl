package top.bbykl.crm.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class viewControl implements WebMvcConfigurer {

    /**
     * 视图控制
     * @param registry
     */
    @Override

    public void addViewControllers(ViewControllerRegistry registry) {

        //配置首页绑定
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }

}
