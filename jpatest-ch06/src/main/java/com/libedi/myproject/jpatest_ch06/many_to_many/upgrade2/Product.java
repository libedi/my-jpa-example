package com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2;

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
}
