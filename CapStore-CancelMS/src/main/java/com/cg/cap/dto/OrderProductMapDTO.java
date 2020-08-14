package com.cg.cap.dto;




public class OrderProductMapDTO {

	private String productUIN;
	private  String orderId;
	private String productId;
	private int productStatus;
	private int giftStatus;

	public OrderProductMapDTO() {}

	public OrderProductMapDTO(String productUIN, String orderId, String productId, int productStatus, int giftStatus) {
		super();
		this.productUIN = productUIN;
		this.orderId = orderId;
		this.productId = productId;
		this.productStatus = productStatus;
		this.giftStatus = giftStatus;
	}

	public String getProductUIN() {
		return productUIN;
	}

	public void setProductUIN(String productUIN) {
		this.productUIN = productUIN;
	}

	public  String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}

	public int getGiftStatus() {
		return giftStatus;
	}

	public void setGiftStatus(int giftStatus) {
		this.giftStatus = giftStatus;
	}
	
	
	
	
	
}
