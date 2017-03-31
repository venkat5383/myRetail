package org.my.retail.contract;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Document(collection = "ProductPriceInfo")
public class ProductPriceInfo {

	@NotNull
	@Id
	@ApiModelProperty( value = "Product id", required = true ) 
	private String productId;
	@ApiModelProperty( value = "Product name", required = false ) 
	private String productName;
	@ApiModelProperty( value = "Current price", required = true ) 
	private Price current_price;

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	@Override
	public String toString() {
		return "ProductPriceInfo [productId=" + productId + ", productName=" + productName + ", current_price="
				+ current_price.toString() + "]";
	}
	public ProductPriceInfo() {

	}
	public ProductPriceInfo(String productId, String productName, Price current_price) {

		this.productId = productId;
		this.productName = productName;
		this.current_price = current_price;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Price getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(Price current_price) {
		this.current_price = current_price;
	}

}
