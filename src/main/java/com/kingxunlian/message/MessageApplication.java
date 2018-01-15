package com.kingxunlian.message;

import com.ctrip.framework.apollo.XLApplication;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.kingxunlian.annotation.EnableXLStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/15 下午2:42
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableXLStarter
@EnableApolloConfig({"application","kxl.consul", "kxl.actuator"})
public class MessageApplication {

    private static final Logger logger = LoggerFactory.getLogger(MessageApplication.class);


    public static void main(final String[] args) {
        logger.info("\n\n" +
                "----------------------------------------------------------------\n" +
                "  " + " - S T A R T ...\n" +
                "----------------------------------------------------------------\n");
        ApplicationContext applicationContext = XLApplication.run(MessageApplication.class, args);

        logger.info("\n\n" +
                "----------------------------------------------------------------\n" +
                "  " + " - S T A R T E D ! \n" +
                "----------------------------------------------------------------\n");
    }

}
