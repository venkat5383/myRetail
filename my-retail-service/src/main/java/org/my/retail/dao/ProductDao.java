package org.my.retail.dao;

import org.my.retail.contract.ProductPriceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;



@Repository
public class ProductDao {
	
	@Autowired
    MongoTemplate mongoTemplate;

	public ProductPriceInfo getProductPricingInfo(ProductPriceInfo productPriceInfo) {
		// TODO Auto-generated method stub
		productPriceInfo.setCurrent_price(mongoTemplate.findById(productPriceInfo.getProductId(), ProductPriceInfo.class).getCurrent_price());
		return productPriceInfo;
	}

	public String updateProduct(ProductPriceInfo productPriceInfo) {
		// TODO Auto-generated method stub
		mongoTemplate.save(productPriceInfo);
		return "Updated Successfully";
	}

}
