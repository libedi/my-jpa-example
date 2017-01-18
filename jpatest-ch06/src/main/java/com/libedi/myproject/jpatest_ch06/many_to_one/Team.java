package com.libedi.myproject.jpatest_ch06.many_to_one;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private String id;
	
	private String name;
	
	@OneToMany(mappedBy = "team")
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
	/*
	 * 편의 메서드
	 */
	public void addMember(Member member){
		this.members.add(member);
		// 무한루프에 빠지지 않도록 체크
		if(member.getTeam() != this){
			member.setTeam(this);
		}
	}
	
}
