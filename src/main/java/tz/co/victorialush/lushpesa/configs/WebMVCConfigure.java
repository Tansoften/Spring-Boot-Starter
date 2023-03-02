package tz.co.victorialush.lushpesa.configs;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import tz.co.victorialush.lushpesa.middlewares.AuthMiddleware;

import java.awt.*;

@Configuration
@EnableWebMvc
public class WebMVCConfigure implements WebMvcConfigurer {
//    @Override
//    public void configurePathMatch(@NotNull PathMatchConfigurer configurer) {
//
//        WebMvcConfigurer.super.configurePathMatch(configurer);
//    }

    @Autowired
    AuthMiddleware authMiddleware;
    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(authMiddleware)
                .addPathPatterns("/api/profile/**")
                .addPathPatterns("/api/sign-out");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOrigins("http://localhost:5173").allowCredentials(true);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
