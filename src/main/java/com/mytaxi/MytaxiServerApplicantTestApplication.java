package com.mytaxi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"com.mytaxi"})
@Slf4j
public class MytaxiServerApplicantTestApplication implements CommandLineRunner {

	final private  Environment environment;

	@Autowired
	public MytaxiServerApplicantTestApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(MytaxiServerApplicantTestApplication.class, args);

				}


	@Override
	public void run(String... args) throws Exception {
		log.info("Active profiles: [{}]", Arrays.toString(environment.getActiveProfiles()));
		log.info("db active:[{}]", environment.getProperty("spring.data.mongodb.uri"));
	}




}
