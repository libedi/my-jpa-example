package com.libedi.myproject.jpatest_example.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
//	@Column(name = "ITEM_ID")
//	private Long itemId;
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Item item;		// 외래키를 갖고 있는 연관관계 주인
	
//	@Column(name = "ORDER_ID")
//	private Long orderId;
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;	// 외래키를 갖고 있는 연관관계 주인
	
	private int orderPrice;	// 주문가격
	private int count;		// 주문수량
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public Long getItemId() {
//		return itemId;
//	}
//	public void setItemId(Long itemId) {
//		this.itemId = itemId;
//	}
//	public Long getOrderId() {
//		return orderId;
//	}
//	public void setOrderId(Long orderId) {
//		this.orderId = orderId;
//	}
	public Item getItem() {
		return item;
	}
	/*
	 * 비즈니스 요구사항을 분석한 결과, 주문상품 -> 상품 의 참조는 많지만,
	 * 상품 -> 주문상품 의 참조는 거의 없어 다대일 단방향 설정
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
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
