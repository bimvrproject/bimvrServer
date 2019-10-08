package com.jhbim.bimvr.system.config;

import com.alipay.api.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shuihu
 * @create 2019-09-24 9:49
 */
@Configuration
public class LogConfig {
    private static final Logger LOG = LoggerFactory.getLogger(LogConfig.class);

    @Bean
    public Person logMethod() {
        LOG.info("==========print log==========");
        return new Person();
    }
}
