package com.libedi.myproject.jpatest_ch06.many_to_many;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product {
	@Id @Column(name = "PRODUCT_ID")
	private String id;
	
	private String name;
	
	@ManyToMany(mappedBy = "products")	// 다대다 양방향을 위한 역방향 추가
	private List<Member> members;

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
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
}
