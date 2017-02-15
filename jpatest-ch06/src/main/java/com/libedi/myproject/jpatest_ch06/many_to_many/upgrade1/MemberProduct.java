package com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 회원상품 엔티티
 * @author libedi
 *
 */
@Entity
@IdClass(MemberProductId.class)		// 회원상품 엔티티는 기본키가 MEMBER_ID와 PRODUCT_ID로 이루어진 복합키.
									// JPA에서 복합키를 사용하려면 별도의 식별자 클래스를 만들어야 한다.
									// 그리고 엔티티에 @IdClass 를 사용해서 식별자 클래스를 지정해준다.
public class MemberProduct {
	// @Id와 @JoinColumn 을 이용하여 기본키 + 외래키 를 한번에 매핑 : 식별관계
	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;		// MemberProductId.member 와 연결
	
	// @Id와 @JoinColumn 을 이용하여 기본키 + 외래키 를 한번에 매핑 : 식별관계
	@Id
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;	// MemberProductId.product 와 연결
	
	private int orderAmount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
