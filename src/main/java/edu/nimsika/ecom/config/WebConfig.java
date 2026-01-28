package edu.nimsika.ecom.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    public void addCrosMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
