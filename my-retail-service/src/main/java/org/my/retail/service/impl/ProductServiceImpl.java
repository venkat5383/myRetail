package org.my.retail.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.my.retail.contract.Product;
import org.my.retail.contract.ProductPriceInfo;
import org.my.retail.dao.ProductDao;
import org.my.retail.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDao productDao;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Product product;

	@Value("${product.service.url}")
	private String productUrl;

	@Override
	public ProductPriceInfo enrichProductPriceInfo(ProductPriceInfo productPriceInfo) throws Exception{
		// TODO Auto-generated method stub
		log.info("Inside ProductServiceImpl in enrichProductPriceInfo with product::"+productPriceInfo.getProductId());
		productPriceInfo = getProductName(productPriceInfo);
		productPriceInfo = productDao.getProductPricingInfo(productPriceInfo);
		return productPriceInfo;
	}

	private ProductPriceInfo getProductName(ProductPriceInfo productPriceInfo) {
		// TODO Auto-generated method stub
		log.info("Inside ProductServiceImpl in getProductName with product::"+productPriceInfo.getProductId());
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("productId", productPriceInfo.getProductId());
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(productUrl)
				.queryParam("excludes", "taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");
		try{
		product = restTemplate.getForObject(builder.buildAndExpand(uriParams).toUri(), Product.class);
		productPriceInfo.setProductName(product.getProduct().getItem().getProductDescription().getTitle());
		}catch(Exception e){
			productPriceInfo.setProductName("");
		}
		return productPriceInfo;
	}

	@Override
	public String updateProductPrice(ProductPriceInfo productPriceInfo)throws Exception {
		// TODO Auto-generated method stub
		log.info("Inside ProductServiceImpl in updateProductPrice with product::"+productPriceInfo.getProductId());
		return productDao.updateProduct(productPriceInfo);
	}


}
