package com.libedi.myproject.jpatest_example.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 주문 엔티티
 * - 상품을 주문한 회원의 외래키값과 주문 날짜, 주문 상태를 가진다.
 * @author libedi
 *
 */
@Entity
@Table(name = "ORDERS")
public class Order {
	
	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	
	@Column(name = "MEMBER_ID")
	private Long memberId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;		// 주문 날짜
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;	// 주문 상태
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
}
