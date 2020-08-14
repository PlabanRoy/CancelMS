package com.cg.cap.service;




import java.util.List;

import com.cg.cap.beans.ProductResponse;
import com.cg.cap.exception.CrudException;


public interface CancelService 
{
	
    /**
     * name-cancelOrder
     * description: Cancel a order that is placed
     */
	String cancelOrder(String orderId , String userId) throws   CrudException ;
	 /**
     * name-cancelProduct
     * description:cancel a product of particular order that is placed
     */
	String cancelProduct(String orderId, String userId, String productId, int quantity) throws Exception ;
	
	/**
	 * name:-getResponseProducts
	 * description:-to get the list of cancelled products
	 */
	List<ProductResponse> getResponseProducts();

}
