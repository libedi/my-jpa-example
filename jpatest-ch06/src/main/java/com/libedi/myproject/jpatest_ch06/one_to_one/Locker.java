package com.libedi.myproject.jpatest_ch06.one_to_one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {
	@Id @GeneratedValue
	@Column(name = "LOCKER_ID")
	private String id;
	
	private String name;
	
	/*
	 * 일대일 양방향을 위해 설정.
	 * 연관관계의 주인은 FK가 MEMBER 테이블에 있으므로
	 * Member 엔티티이다.
	 */
	@OneToOne(mappedBy = "locker")
	private Member member;

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
