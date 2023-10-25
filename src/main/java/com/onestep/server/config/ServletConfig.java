package com.onestep.server.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ServletConfig implements WebMvcConfigurer {
    private static final String[] CLASSPATH_PATH_PATTERNS = {"swagger-ui.html", "/webjars/**"};
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/",
            "classpath:/META-INF/resources/webjars/"};

    /**
     * mvc:resources 설정을 처리합니다.
     *
     * @param registry {@link ResourceHandlerRegistry} 클래스
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(CLASSPATH_PATH_PATTERNS).addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

    }

    /**
     * ROOT(/) path 진입시 /swagger-ui.html 쪽으로 리다이렉트 처리합니다.
     *
     * @param registry {@link ViewControllerRegistry} 클래스
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
    }
}
