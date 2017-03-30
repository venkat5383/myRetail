package org.my.retail.utility;

import org.my.retail.contract.ErrorResponse;
import org.my.retail.contract.ProductPriceInfo;
import org.my.retail.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {
	
	ErrorResponse errorResponse;
	
	public ErrorResponse validateProductPriceInfo(ProductPriceInfo productPriceInfo){
		errorResponse = validateProductId(productPriceInfo.getProductId());
		if(!errorResponse.getErrorCode().equals("")){
			return errorResponse;
		}else if(productPriceInfo.getCurrent_price().getValue()==null){
			errorResponse.setErrorCode(Constants.ERROR_CODE_INVALID_PRICE);
			errorResponse.setErrorMessage(Constants.ERROR_MSG_INVALID_PRICE);
			return errorResponse;
		}
		return errorResponse;
	}

	public ErrorResponse validateProductId(String productId) {
		// TODO Auto-generated method stub
		errorResponse = new ErrorResponse();
		if(productId.isEmpty()||productId.equals(null)){
			errorResponse.setErrorCode(Constants.ERROR_CODE_INVALID_PRODUCTID);
			errorResponse.setErrorMessage(Constants.ERROR_MSG_INVALID_PRODUCTID);
			return errorResponse;
		}else{
			try{
				Integer.parseInt(productId);
			}catch(Exception e){
				errorResponse.setErrorCode(Constants.ERROR_CODE_INVALID_PRODUCTID);
				errorResponse.setErrorMessage(Constants.ERROR_MSG_INVALID_PRODUCTID);
				return errorResponse;
			}
		}
		return errorResponse;
	}
	

}
