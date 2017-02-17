package com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 주문 엔티티
 * - 다대다 : 새로운 기본 키 사용
 * - 추천하는 기본 키 생성 전략은 DB에서 자돋으로 생성해주는 대리 키를 Long 값으로 사용하는 것.
 * - 비즈니에 의존하지 않고, 영구적이며, ORM 매핑시에 복합 키를 
 * @author libedi
 *
 */
@Entity
public class Order {
	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	private int orderAmount;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
}
