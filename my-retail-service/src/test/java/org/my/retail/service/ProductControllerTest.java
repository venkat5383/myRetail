package org.my.retail.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.my.retail.bootstrap.Bootstrap;
import org.my.retail.contract.ErrorResponse;
import org.my.retail.contract.Price;
import org.my.retail.contract.ProductPriceInfo;
import org.my.retail.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bootstrap.class,webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations="classpath:application-dev.properties")
public class ProductControllerTest {


	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getProductHappyPathTest() {
		ProductPriceInfo productPriceInfo = this.restTemplate.getForObject("/products/{productId}", 
				ProductPriceInfo.class,"13860428");
		Assert.assertEquals("The Big Lebowski (Blu-ray)",productPriceInfo.getProductName());
		Assert.assertEquals(BigDecimal.valueOf(789),productPriceInfo.getCurrent_price().getValue());
		Assert.assertEquals("USD",productPriceInfo.getCurrent_price().getCurrency_code());
	
	}
	
	@Test
	public void getProductNotFoundTest() {
		ErrorResponse errorResponse = this.restTemplate.getForObject("/products/{productId}", 
				ErrorResponse.class,"13860428234");
		Assert.assertEquals("4004",errorResponse.getErrorCode());	
	}
	
	@Test
	public void getProductInvalidProductIdTest() {
		ErrorResponse errorResponse = this.restTemplate.getForObject("/products/{productId}", 
				ErrorResponse.class,"13860428234s");
		Assert.assertEquals("4001",errorResponse.getErrorCode());
		Assert.assertEquals("Invalid product id",errorResponse.getErrorMessage());
	}
	
	@Test
	public void updateProductHappyPathTest() {
		ProductPriceInfo request = new ProductPriceInfo("123456","test", new Price(BigDecimal.valueOf(123), "USD"));
		this.restTemplate.put("/products/{productId}", request,"123456");
		ProductPriceInfo response = this.restTemplate.getForObject("/products/{productId}", 
				ProductPriceInfo.class,"123456");
		Assert.assertEquals("", response.getProductName());
		Assert.assertEquals(BigDecimal.valueOf(123),response.getCurrent_price().getValue());
	}
}
