package com.kingxunlian.message.config;

import com.kingxunlian.callback.business.callbackcenter.feginclient.ExternalCallBackFeignClient;
import com.kingxunlian.common.id.IdGeneratorClient;
import com.kingxunlian.domain.file.client.IFileServiceFeignClient;
import com.kingxunlian.domain.invoice.client.IInvoiceFeignClient;
import com.kingxunlian.mq.producer.MQGatewayClient;
import com.kingxunlian.user.client.ICompanyConfigClient;
import feign.Logger;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrations;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableFeignClients(basePackageClasses = { ExternalCallBackFeignClient.class,
        MQGatewayClient.class, IdGeneratorClient.class, IFileServiceFeignClient.class,
        IInvoiceFeignClient.class, ICompanyConfigClient.class})
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    @Bean
    public WebMvcRegistrations feignWebRegistrations() {
        return new WebMvcRegistrationsAdapter() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new FeignRequestMappingHandlerMapping();
            }
        };
    }

    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(final Class<?> beanType) {
            return super.isHandler(beanType) &&
                    !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
        }
    }
}
