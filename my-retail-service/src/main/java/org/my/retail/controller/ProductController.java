package org.my.retail.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import org.my.retail.contract.ErrorResponse;
import org.my.retail.contract.ProductPriceInfo;
import org.my.retail.service.ProductService;
import org.my.retail.utility.ProductValidator;
import org.my.retail.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@Autowired
	ProductValidator productValidator;

	
	ErrorResponse errorResponse;

	@RequestMapping(value = "/{productId}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(@Valid @PathVariable String productId){
		log.info("Inside getProduct method in controller with productId::"+productId);
		try{
			errorResponse = productValidator.validateProductId(productId);
			if(errorResponse.equals(null)||!errorResponse.getErrorCode().equals("")){
				System.out.println("line 47");
				return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
			}else{
				System.out.println("line 50");
				productPriceInfo.setProductId(productId);
				productPriceInfo = productService.enrichProductPriceInfo(productPriceInfo);
				return ResponseEntity.ok(productPriceInfo);
			}
		}catch(Exception e){
			System.out.println("line 56"+e.getMessage());
			errorResponse = new ErrorResponse();
			errorResponse.setErrorCode(Constants.ERROR_CODE_PRODUCT_FETCH_FAIED);
			errorResponse.setErrorMessage(e.getMessage());
			return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{productId}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductPriceInfo productPriceInfo){
		log.info("Inside updateProduct method in controller with productId::"+productPriceInfo.getProductId());
		errorResponse = productValidator.validateProductPriceInfo(productPriceInfo);
		if(!errorResponse.getErrorCode().equals("")){
			return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
		}else{
			try{
				productService.updateProductPrice(productPriceInfo);
				return ResponseEntity.ok("Succesfully Updated");
			}catch(Exception e){
				errorResponse = new ErrorResponse();
				errorResponse.setErrorCode(Constants.ERROR_CODE_UPDATE_FAILED);
				errorResponse.setErrorMessage(e.getMessage());
				return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
			}
		}
	}

}
