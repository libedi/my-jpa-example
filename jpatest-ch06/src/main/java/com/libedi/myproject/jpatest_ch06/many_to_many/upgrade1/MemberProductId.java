package com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1;

import java.io.Serializable;

/**
 * 복합키를 위한 회원상품 식별자 클래스
 * @author libedi
 *
 */
public class MemberProductId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2358512977997944943L;
	
	/*
	 * 복합 키를 위한 식별자 클래스의 특징
	 * - 복합 키는 별도의 식별자 클래스로 만들어야 한다.
	 * - Serializable 인터페이스를 구현해야 한다.
	 * - equals와 hashCode 메소드를 구현해야 한다.
	 * - 기본 생성자가 있어야 한다.
	 * - 식별자 클래스는 public 이어야 한다.
	 * - @IdClass 를 사용하는 방법 외에 @EmbeddedId 를 사용하는 방법도 있다. 
	 */
	private String member;		// MemberProduct.member 와 연결
	private String product;		// MemberProduct.product 와 연결
	
	// hashCode ans equals
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
}
