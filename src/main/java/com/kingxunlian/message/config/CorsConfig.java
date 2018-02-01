//package com.kingxunlian.message.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
///**
// * Created by Yorke on 2017/4/26.
// * 解决前端跨域问题，跨域需要服务器支持
// */
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public CorsConfiguration corsConfiguration() {
//        final CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*"); // 1 允许任何域名使用
//        corsConfiguration.addAllowedHeader("*"); // 2 允许任何头
//        corsConfiguration.addAllowedMethod("*"); // 3 允许任何方法（post、get等）
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter(CorsConfiguration corsConfiguration) {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration); // 4
//        return new CorsFilter(source);
//    }
//
//
//}
