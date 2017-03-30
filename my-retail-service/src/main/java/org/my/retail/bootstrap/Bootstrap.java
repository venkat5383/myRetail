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

@SpringBootApplication
@ComponentScan("org.my.retail.*")
public class Bootstrap extends SpringServletContainerInitializer{

	 public static void main(String[] args) {
	        SpringApplication.run(Bootstrap.class, args);
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
