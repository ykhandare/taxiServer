package com.mytaxi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by yogesh on 30/3/21.
 */

@Configuration
public class PropertiesSourcesConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesSourcesConfiguration.class);

    private static final Resource PROPERTIES = new ClassPathResource("application.properties");

    @Bean
    //@Profile("dev")
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

        logger.info("preparing properties file for application configured with profile {} ");

        PropertySourcesPlaceholderConfigurer property = getProperty();

        property.setLocation(PROPERTIES);

        property.setIgnoreUnresolvablePlaceholders(true);

        return property;
    }

    private static PropertySourcesPlaceholderConfigurer getProperty() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return propertySourcesPlaceholderConfigurer;
    }


}
