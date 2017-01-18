package com.libedi.myproject.jpatest_ch06.many_to_one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private String id;
	
	private String username;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;

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
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
		
		// 무한루프에 빠지지 않도록 체크
		if(!team.getMembers().contains(this)){
			team.getMembers().add(this);
		}
	}
	
}
