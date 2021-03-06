package com.ventulus95.ouathjwt;

import com.ventulus95.ouathjwt.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class OuathJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(OuathJwtApplication.class, args);
	}

}
