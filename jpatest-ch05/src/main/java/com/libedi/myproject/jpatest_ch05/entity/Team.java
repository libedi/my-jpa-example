package com.libedi.myproject.jpatest_ch05.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {
	/*
	 * CREATE TABLE TEAM(
	 * 	TEAM_ID VARCHAR(255) NOT NULL,
	 * 	NAME VARCHAR(255),
	 * 	PRIMARY KEY(TEAM_ID)
	 * );
	 */
	@Id
	@Column(name = "TEAM_ID")
	private String id;
	private String name;
	
	public Team() {
	}
	public Team(String id, String name) {
		this.id = id;
		this.name = name;
	}
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
