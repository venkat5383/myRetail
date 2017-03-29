package org.my.retail.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import org.my.retail.contract.Product;
import org.my.retail.contract.ProductPriceInfo;
import org.my.retail.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductPriceInfo productPriceInfo;
	
	@RequestMapping(value = "/{productId}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
	public ResponseEntity getProduct(@Valid @PathVariable String productId){
		log.info("Inside getProduct method in controller with productId::"+productId);
		productPriceInfo.setProductId(productId);
		try{
		productPriceInfo = productService.enrichProductPriceInfo(productPriceInfo);
		}catch(Exception e){
			
		}
		return ResponseEntity.ok(productPriceInfo);
	}
	
	@RequestMapping(value = "/{productId}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT)
	public ResponseEntity updateProduct(@Valid @RequestBody ProductPriceInfo productPriceInfo){
		log.info("Inside updateProduct method in controller with productId::"+productPriceInfo.getProductId());
		try{
		productService.updateProductPrice(productPriceInfo);
		}catch(Exception e){
			
		}
		return ResponseEntity.ok("");
	}

}
