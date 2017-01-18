package com.libedi.myproject.jpatest_ch06.one_to_many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Team {
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private String id;
	
	private String name;
	
	/*
	 * 일대다 단방향 관계
	 * : 반대쪽 테이블의 외래 키를 관리한다.
	 */
	@OneToMany
	@JoinColumn(name = "TEAM_ID")	// MEMBER 테이블의 TEAM_ID (FK)
	private List<Member> members = new ArrayList<>();

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
