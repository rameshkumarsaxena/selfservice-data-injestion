package com.techedusoln.selfserve.datainjestion;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication(scanBasePackages = {"com.techedusoln.selfserve.datainjestion", "com.techedusoln.selfserve.datainjestion.controller"})
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan(basePackages={"com.techedusoln.selfserve.datainjestion", "com.techedusoln.selfserve.datainjestion.controller"})
@EnableJpaRepositories("com.techedusoln.selfserve.datainjestion.repository")
public class SelfServiceDataInjestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfServiceDataInjestionApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.paths(PathSelectors.any()).build().pathMapping("/")
				.apiInfo(apiInfo()).useDefaultResponseMessages(false);
	}

	@Bean
	public ApiInfo apiInfo() {
		final ApiInfoBuilder builder = new ApiInfoBuilder();
		builder.title("My Application API through Swagger UI").version("1.0").license("(C) Copyright Test")
				.description("List of all the APIs of My Application App through Swagger UI");
		return builder.build();
	}
}

