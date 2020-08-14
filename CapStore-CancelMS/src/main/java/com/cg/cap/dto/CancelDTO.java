package com.cg.cap.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cancel_table")
public class CancelDTO {
	@Column(name = "order_id")
	private String orderid;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "product_id")
	private String productId;

	@Id
	@Column(name = "product_uin")
	private String productuin;
	@Column(name = "order_cancel_time")
	private Date orderCancelTime;

	@Column(name = "order_cancel_status", unique = false, length = 1)
	private int orderCancelStatus;
	
	public CancelDTO() {
	}
	//Constructors
	public CancelDTO(String orderid, String userId, String productId, String productuin, Date orderCancelTime,
			int orderCancelStatus) {
		super();
		this.orderid = orderid;
		this.userId = userId;
		this.productId = productId;
		this.productuin = productuin;
		this.orderCancelTime = orderCancelTime;
		this.orderCancelStatus = orderCancelStatus;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getOrdercanceltime() {
		return orderCancelTime;
	}

	public void setOrdercanceltime(Date ordercanceltime) {
		this.orderCancelTime = ordercanceltime;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductuin() {
		return productuin;
	}

	public void setProductuin(String productuin) {
		this.productuin = productuin;
	}

	public Date getOrderCancelTime() {
		return orderCancelTime;
	}

	public void setOrderCancelTime(Date orderCancelTime) {
		this.orderCancelTime = orderCancelTime;
	}

	public int getOrderCancelStatus() {
		return orderCancelStatus;
	}

	public void setOrderCancelStatus(int orderCancelStatus) {
		this.orderCancelStatus = orderCancelStatus;
	}

	public void setOrderId(String orderId) {
		this.orderid = orderId;
	}
	
	
	//toString
	@Override
	public String toString() {
		return "CancelDTO [orderid=" + orderid + ", userId=" + userId + ", productId=" + productId + ", productuin="
				+ productuin + ", orderCancelTime=" + orderCancelTime + ", orderCancelStatus=" + orderCancelStatus
				+ "]";
	}
}
