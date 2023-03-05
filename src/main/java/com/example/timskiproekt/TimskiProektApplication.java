package com.example.timskiproekt;

import com.example.service.ServiceRootConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
		ServiceRootConfig.class
})
public class TimskiProektApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimskiProektApplication.class, args);
	}
}
