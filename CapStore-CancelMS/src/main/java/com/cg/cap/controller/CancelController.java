 package com.cg.cap.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cap.beans.ProductResponse;
import com.cg.cap.exception.NullParameterException;
import com.cg.cap.service.CancelService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


import java.util.List;

import org.apache.log4j.Logger;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Cancel")

public class CancelController 
{
	private static final Logger logger = Logger.getLogger(CancelController.class);
	
	@Autowired
	private CancelService cancelService;
	
	/****************************Cancelling a order**********************************/
	
	@PostMapping("/cancelOrder")
	@HystrixCommand(fallbackMethod ="fallbackCancelOrder")
	public String cancelOrder(@RequestParam String orderId , @RequestParam String userId) throws Exception {
		
		if(orderId==null||userId==null) { 
			logger.error("Null request cart details not provided at /cancelOrder");
			throw new NullParameterException("Null request, please provide valid orderId or userId!");
		}
		String status="cancel order succesfully";
		 cancelService.cancelOrder(orderId,userId);
		 return status;
			
	}
	
	/****************************Cancelling a perticular product from an order**********************************/
				
	@PostMapping("/Cancel-product")
	@HystrixCommand(fallbackMethod ="fallbackCancelProduct")
	public String cancelProduct(@RequestParam String orderId,  @RequestParam String userId, @RequestParam String productId,@RequestParam int quantity) throws Exception
	{
		if(orderId==null ||userId==null||productId==null) 
		{
			logger.error("Null request, userId or orderId or productId not provided at /Cancel-product");
			throw new NullParameterException("Null request, please provide userId or orderId or productId");
		}
		String status="cancel product succesfully";
		cancelService.cancelProduct(orderId, userId, productId, quantity);
	    return status;
		
	}
	
	/****************************Viewing all the cancelled product**********************************/

	@GetMapping("/getCancelProducts")
	List<ProductResponse> getResponseProducts(){
		return cancelService.getResponseProducts();
	}
	
	/**************************** Fallback methods *************************************************/ 
	
	public String fallbackCancelOrder(@RequestParam String orderId , @RequestParam String userId) throws Exception {
		return "orderId Not Provided";
	
	}
	
	
	public String fallbackCancelProduct(@RequestParam String orderId,  @RequestParam String userId, @RequestParam String productId,@RequestParam int quantity) throws Exception{
		return "Enter valid OrderId, UserId and Quanity";
		
	}
}
