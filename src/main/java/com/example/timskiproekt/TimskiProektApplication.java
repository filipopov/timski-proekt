package com.example.timskiproekt;

import com.example.repository.RepositoryRootConfig;
import com.example.service.ServiceRootConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Import({
		ServiceRootConfig.class,
		RepositoryRootConfig.class
})
@PropertySource("classpath:application-h2.properties")
public class TimskiProektApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimskiProektApplication.class, args);
	}
}
