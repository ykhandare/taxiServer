package com.mytaxi.mongodb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by yogesh on 30/3/21.
 */
public class MongoConfig {

    private static final ApplicationContext applicationContext;

    private static final String MONGO_CONFIG_LOC = "mongo-data.xml";
    private static final String MONGO_BEAN = "mongoTemplate";

    /**
     * static method to initialize application context while object of class
     * is created
     */
    static {
        applicationContext = new ClassPathXmlApplicationContext(MONGO_CONFIG_LOC);
    }

    private MongoConfig() {

    }

    /**
     * static utility method to get mongotemplate from
     * application context
     * USAGES:: MongoConfig.getMongoTemplate();
     *
     * @return
     */
    @Deprecated
    public static MongoTemplate getMongoTemplate() {
        MongoTemplate mongoTemplate = null;

        if (null != applicationContext) {
            mongoTemplate = (MongoTemplate) applicationContext.getBean(MONGO_BEAN);
        }

        return mongoTemplate;
    }




}
