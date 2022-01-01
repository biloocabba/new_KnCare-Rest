package com.knits.kncare;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@OpenAPIDefinition(info=@Info(title = "KnCare api", version = "1.0"))
@SpringBootApplication
public class KnCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnCareApplication.class, args);
	}

}
