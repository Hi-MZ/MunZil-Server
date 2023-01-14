package io.munzil.munzil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class MunzilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MunzilApplication.class, args);
	}

}
