package org.my.retail.bootstrap;

import org.my.retail.contract.Product;
import org.my.retail.contract.ProductPriceInfo;
import org.my.retail.dao.ProductDao;
import org.my.retail.utility.ProductValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("org.my.retail.*")
public class Bootstrap extends SpringServletContainerInitializer{

	 public static void main(String[] args) {
	        SpringApplication.run(Bootstrap.class, args);
	    }
	 
	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("org.my.retail.controller"))              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
	 
	 @Bean
	 ProductPriceInfo getProductPriceInfo(){
		 return new ProductPriceInfo();
	 }
	 
	 @Bean
	 RestTemplate getRestTemplate(){
		 return new RestTemplate();
	 }
	 
	 @Bean
	 ProductDao getProductDao(){
		 return new ProductDao();
	 }
	 
	 @Bean
	 Product getProduct(){
		 return new Product();
	 }
	 
	 @Bean
	 ProductValidator getValidator(){
		 return new ProductValidator();
	 }
	 
	
}
