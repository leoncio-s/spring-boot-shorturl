package com.leoncio.shorturl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SwaggerUIWebMvcConf extends WebMvcConfigurationSupport {


    private final String baseUrl;

    public SwaggerUIWebMvcConf(@Value("${springfox.documentation.swagger-ui.base-url:ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()}") String baseUrl){
        this.baseUrl = baseUrl;
    }
    

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');

        registry.addResourceHandler(baseUrl + "/swagger-ui/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
        .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController(baseUrl + "/swagger-ui/")
            .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
    }
}
