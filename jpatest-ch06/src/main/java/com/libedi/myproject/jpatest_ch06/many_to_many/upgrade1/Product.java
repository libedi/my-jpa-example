package com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 상품 엔티티
 * @author libedi
 *
 */
@Entity
public class Product {
	@Id @Column(name = "PRODUCT_ID")
	private String id;
	
	private String name;
	
	/*
	 * 상품 엔티티에서 회원상품 엔티티로 객체 그래프 탐색 기능이 필요하지 않다고 판단하여 연관관계를 만들지 않았다.
	 */
	
//	@ManyToMany(mappedBy = "products")	// 다대다 양방향을 위한 역방향 추가
//	private List<Member> members;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public List<Member> getMembers() {
//		return members;
//	}
//	public void setMembers(List<Member> members) {
//		this.members = members;
//	}
}
