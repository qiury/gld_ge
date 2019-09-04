package com.gld.config;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.resource.HttpResource;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


/**
 * @Description: Swagger配置类
 * Created by qiuzx on 2019-09-03.
 * @Version 1.0
 * @Author qiuzx@gld.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 定义分隔符
    private static final String splitor = ";";

    //注入环境的方式
    @Autowired
    Environment environment;
    /**
     * 定义创建多个Docket，这样每个项目组使用自己的Docket定义自己负责的项目对外接口
     */
    @Bean
    public Docket docketA(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("DockerA");
    }
    @Bean
    public Docket docketB(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("DockerB");
    }

    /**
     * Swagger是通过Docket进行页面内容管理的，
     * 我们可以提供新的Docket进行页面内容定制
     */
    @Bean
    public Docket docket(ApiInfo apiInfo){
        //根据不同的环境决定是否启用swagger功能
        //方法1. 在配置文件中定义变量，通过读取配置文件中变量的值决定是否启用；
        //方法2. 通过Profiles类的方法判断当前环境是否是指定环境；

        //方式2的实现
        Profiles profiles = Profiles.of("dev","test","default");
        //判断当前环境是否包含profiles中指定的环境
        boolean enable = environment.acceptsProfiles(profiles);

        Predicate<RequestHandler> predicate01 = RequestHandlerSelectors.basePackage("com.gld.jee.controllers");
        Predicate<RequestHandler> predicate02 = RequestHandlerSelectors.basePackage("com.gld.jee.controllers");
        return new Docket(DocumentationType.SWAGGER_2)
                //在这里设置页面信息的提供对象
                .apiInfo(apiInfo)

                //自定义组名，组的作用是多个项目组协同开发时，各个项目组定义各自的对外接口
                .groupName("GLD_GEE")

                //是否启用swagger（true表示启用，false表示不启用）
                .enable(enable)

                //设置忽略的参数
                .ignoredParameterTypes(HttpSession.class, HttpRequest.class, HttpResource.class, BindingResult.class)

                .select()
                //基于包名的过滤，只能指定一个包名
                //.apis(RequestHandlerSelectors.basePackage("com.gld.jee.controllers"))
                //方式1：扩展支持多个包扫描，包名之间通过splitor分割
                //.apis(basePackage("com.gld.jee.controllers"))
                //方式2：同时创建多个RequestHandlerSelectors.basePackage，通过or进行连接
                .apis(Predicates.or(predicate01,predicate02))

                //基于类上注解进行过滤
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //基于方法上注解进行过滤
                //.apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * 定义在线文档的页面的内容信息
     */
    @Bean
    public ApiInfo apiInfo(){
        //联系人信息
        Contact contact = new Contact("qiuzx", "www.baidu.com", "80278100@qq.com");
        //ApiInfo对象中的属性是没有set方法，只提供构造函数方式设置值
        ApiInfo apiInfo = new ApiInfo("广联达GE项目文档", "广联达创新部门", "V1.0", "ge:dev", contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
        return  apiInfo;
    }


//    //让swagger的docket支撑扫描多个包，默认只能扫描一个指定的包
//    public static Predicate<RequestHandler> basePackage(final String basePackage) {
//        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
//    }
//
//    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
//        return input -> {
//            // 循环判断匹配
//            for (String strPackage : basePackage.split(splitor)) {
//                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
//                if (isMatch) {
//                    return true;
//                }
//            }
//            return false;
//        };
//    }
//
//    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
//        return Optional.fromNullable(input.declaringClass());
//    }

}
