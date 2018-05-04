package top.imyzt.ms.core.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * <p>
 *     Swagger2配置文件
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 15:02
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    /**
     * 创建api文档.默认分组.
     * 创建多个bean实现创建多组文档.
     * @return
     */
    @Bean(value = "defaultApi")
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("默认分组")
                .select()
                //控制层所在的包
                .apis(RequestHandlerSelectors.basePackage("top.imyzt.ms"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Management-System")
                .description("使用Spring boot + Mybatis plus 搭建的后台管理系统脚手架")
                .termsOfServiceUrl("https://github.com/imyzt/MS")
                .contact(new Contact("imyzt", "https://imyzt.top", "imyzt01@gmail.com"))
                .version("1.0")
                .build();
    }
}
