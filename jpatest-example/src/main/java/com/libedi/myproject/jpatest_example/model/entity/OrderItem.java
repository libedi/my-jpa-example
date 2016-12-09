package com.libedi.myproject.jpatest_example.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 주문상품 엔티티
 * - 주문의 외래키값과 주문한 상품의 외래키값을 가진다. 그리고 주문 금액과 주문 수량 정보를 가진다.
 * @author libedi
 *
 */
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	
	@Column(name = "ITEM_ID")
	private Long itemId;
	
	@Column(name = "ORDER_ID")
	private Long orderId;
	
	private int orderPrice;	// 주문가격
	private int count;		// 주문수량
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
