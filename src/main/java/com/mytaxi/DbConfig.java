package com.mytaxi;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Configuration
@EnableMongoRepositories()

public class DbConfig extends AbstractMongoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DbConfig.class);

    @Value("${db.name}")
    private String dbName;

    @Value("${db.host}")
    private String dbHost;

    @Value("${db.port}")
    private int dbPort;

    @Value("${db.totalConnection}")
    private int connectionPerHost;

    @Value("${db.client.uri}")
    private String mongoUri;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Arrays.asList("com.mytaxi","com.mytaxi.mongodb.*");
    }

    @Override
    public Mongo mongo() throws Exception {

        logger.debug("Initializing mongo db with URI {}", mongoUri);

        ServerAddress serverAddress = new ServerAddress(dbHost, dbPort);

        MongoClientOptions options = MongoClientOptions.builder()
                .connectTimeout(1000).
                        connectionsPerHost(connectionPerHost).
                        socketKeepAlive(true).
                        writeConcern(WriteConcern.ACKNOWLEDGED).
                        build();

        MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
        Mongo mongo = new MongoClient(mongoClientURI);

        logger.debug("Initialization of mongo db client completed successfully using URI {}!!",mongoUri);

        return mongo;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {


        logger.debug("Initializing mongo template with dbName [{}]", dbName);

        MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);

        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClientURI);

        MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(new DefaultDbRefResolver
                (mongoDbFactory), new
                MongoMappingContext());

        mappingMongoConverter.setCustomConversions(customConversions());

        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

        //mappingMongoConverter.setMapKeyDotReplacement(".");
        mappingMongoConverter.afterPropertiesSet();


        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, mappingMongoConverter);

        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);

        logger.debug("Initialization of mongo template completed successfully with URI {}",mongoUri);

        return mongoTemplate;
    }



    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(JSR310DateConverters.DateToZonedDateTimeConverter.INSTANCE);
        converters.add(JSR310DateConverters.ZonedDateTimeToDateConverter.INSTANCE);
        return new CustomConversions(converters);
    }

    @Override
    public MongoDbFactory mongoDbFactory() throws Exception {
        return super.mongoDbFactory();
    }


    @Bean
    public LoggingEventListener mappingEventsListener() {
        return new LoggingEventListener();
    }
}
