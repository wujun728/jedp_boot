package com.wujunshen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class AggregatorApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(AggregatorApplication.class);

    public static void main(String[] args) {
        LOGGER.info("start execute AggregatorApplication....\n");
        //SpringApplication.run(AggregatorApplication.class, args);
        new SpringApplicationBuilder(AggregatorApplication.class).web(true).run(args);
        LOGGER.info("end execute AggregatorApplication....\n");
    }
}
