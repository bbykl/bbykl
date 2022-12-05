package top.bbykl.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
//swagger3.0使用注解
@EnableOpenApi
/**
 * @description: TODO
 * @author 盛祥进
 * @date 2022/11/9 0:41
 * @version 1.0
 */
public class swaggerConfig {
    @Bean

    public Docket docket(){
        return new Docket(DocumentationType.OAS_30).apiInfo(new ApiInfo("蓝月亮","api接口文档描述","1.2","http://www.baidu.com",
                new Contact("sxj","http://www.baidu.com","747340585@qq.com"),"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList()))
                .select().
                apis(RequestHandlerSelectors.basePackage("top.bbykl.crm")).
//          paths(PathSelectors.ant("/help"))
       // apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                build().groupName("寶寳");
    }
}
