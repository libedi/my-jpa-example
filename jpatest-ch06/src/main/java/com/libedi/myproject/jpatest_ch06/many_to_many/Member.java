package com.libedi.myproject.jpatest_ch06.many_to_many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Member {
	@Id @GeneratedValue
	@JoinColumn(name = "MEMBER_ID")
	private String id;
	
	private String username;
	
	@ManyToMany
	@JoinTable(
			name = "MEMBER_PRODUCT",	// 다대다를 다대일, 일대다로 연결해주는 매핑 테이블 지정.
			joinColumns = @JoinColumn(name = "MEMBER_ID"),
			inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
			)
	private List<Product> products = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	// 다대다 양방향을 위한 연관관계 편의 메서드
	public void addProduct(Product product){
		this.products.add(product);
		product.getMembers().add(this);
	}
	
}
