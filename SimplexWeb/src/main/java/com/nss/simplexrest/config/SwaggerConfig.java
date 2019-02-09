package com.nss.simplexrest.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
	@Bean
	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.nss.simplexrest"))
				.paths(regex("/rest.*"))
				.build()
				.apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Sapience Intelligence StoryLine API",
                "API built for storyline integration development",
                "1.0",
                "Terms of Service",
                new Contact("Sapience Intelligence", "http://www.sapienceintelligence.com",
                        "agawande@nicesoftwaresolutions.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );
		
		return apiInfo;
	}
}