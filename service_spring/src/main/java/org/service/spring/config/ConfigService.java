package org.service.spring.config;

import org.dao.spring.config.DataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("org.service.spring")
@Import (DataConfig.class)
public class ConfigService {
 
}