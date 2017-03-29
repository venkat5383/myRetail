package org.my.retail.service;

import org.my.retail.contract.ProductPriceInfo;
import org.springframework.stereotype.Component;

@Component
public interface ProductService {

	ProductPriceInfo enrichProductPriceInfo(ProductPriceInfo productPriceInfo)throws Exception;
	String updateProductPrice(ProductPriceInfo productPriceInfo)throws Exception;
	
}
