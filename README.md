**myRetail**
----
  'myRetail' is a maven project which consists of contract maven module and service maven module. Service module at this point of time only supports GET and UPDATE operations on product price.

* **URL**

  	 		/products/{productId}

* **Method:**
  
  			`GET`
  
*  **URL Params**

  				We need to enter required "productId" in the URL so that the get service will fetch name and current price information of the product.

 *  **Required:**
 
   					`productId=[numeric]`

* **Success Response:**
  			on success, service will result in below like content
 
   			* **Code:** 200 <br />
    		**Content:** `{"productId":"13860428222","productName":"product name","current_price": { "value":7, "currency_code":"USD"}}`
 
* **Error Response:**

  Error will result in case of invalid product id or the product id which is not present in the data base.

  * **Code:** 400 Bad Request <br />
    **Content:** `{ "errorCode":"4001","errorMessage":"Invalid product id" }`

  OR

  * **Code:** 404 <br />
    **Content:** `{ "errorCode":"4004","errorMessage":"404 Not Found" }`

---------------------------------
* **URL**

  	 		/products/{productId}

* **Method:**
  
  			`PUT`
  
*  **URL Params**

  				We need to give a JSON form of ProductPriceInfo which need to have productId, productName (optional) and Price(value and currency code) to update thecurrent price of the product in the data base

 *  **Required:**
 
   					`productId=[numeric]`

* **Success Response:**
  			on success, service will result in below like content
 
   			* **Code:** 200 <br />
    		**Content:** `Successfully Updated`
 
 
* **Error Response:**

  Error will result in case of invalid product id or the product id which is not present in the data base or invalid price.

  * **Code:** 400 Bad Request <br />
    **Content:** `{ "errorCode":"4001","errorMessage":"Invalid product id" }`

 