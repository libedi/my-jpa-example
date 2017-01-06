package com.libedi.myproject.jpatest_example.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
//	@Column(name = "MEMBER_ID")
//	private Long memberId;
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;		// 외래키를 갖고 있는 연관관계 주인
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItem = new ArrayList<>();
	
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
//	public Long getMemberId() {
//		return memberId;
//	}
//	public void setMemberId(Long memberId) {
//		this.memberId = memberId;
//	}
	public Member getMember() {
		return member;
	}
	// == 연관관계 메소드 == //
	/*
	 * 연관관계 메소드
	 * 비즈니스 요구사항에서 회원 -> 주문, 주문 -> 회원으로의 참조가 많아
	 * 양방향 연관관계 편의 메소드를 정의했다.
	 */
	public void setMember(Member member) {
		// 기존관계 제거
		if(this.member != null){
			this.member.getOrders().remove(this);
		}
		this.member = member;
		member.getOrders().add(this);
	}
	public List<OrderItem> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
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
