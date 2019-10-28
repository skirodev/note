package com.junzijian.framework.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import com.junzijian.framework.common.interceptor.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhe
 * @date 2019/10/24
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private CustomInterceptor customInterceptor;


    /**
     * 跨域支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("OPTIONS", "GET", "POST", "DELETE", "PUT")
                .maxAge(1800);
    }

    /**
     * add interceptors
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // exclude
        List<String> excludePathPatterns = Lists.newArrayList(
                "/error",
                "/v1/common/**"
        );
        // exclude swagger
        excludeSwagger(excludePathPatterns);

        // add interceptor
        registry.addInterceptor(customInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatterns);
    }

    /**
     * 添加静态资源 -- swagger-ui
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        removeDefaultConver(converters);
//
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        //自定义配置...
//        FastJsonConfig config = new FastJsonConfig();
//        config.setSerializerFeatures(
//                SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteNullListAsEmpty,
//                SerializerFeature.WriteNullStringAsEmpty
//        );
//        fastJsonHttpMessageConverter.setFastJsonConfig(config);
//
//        List<MediaType> supportedMediaTypes = new ArrayList<>();
//        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
//        converters.add(fastJsonHttpMessageConverter);
//    }
//
//    private void removeDefaultConver(List<HttpMessageConverter<?>> converters) {
//        converters.removeIf(e -> e instanceof MappingJackson2HttpMessageConverter);
//    }

    /**
     * exclude swagger
     *
     * @param excludePathPatterns
     * @return
     */
    private void excludeSwagger(List<String> excludePathPatterns) {

        // 放开swagger文档
        excludePathPatterns.addAll(Lists.newArrayList(
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**")
        );
    }

}