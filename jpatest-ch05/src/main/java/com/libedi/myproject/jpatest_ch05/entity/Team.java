package com.libedi.myproject.jpatest_ch05.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	/*
	 * 양방향 연관관계 추가
	 * - @OneToMany : 일대다 관계를 매핑
	 * - mappedBy : 양방향 매핑일 떄 사용. 반대쪽 매핑의 필드명을 값으로 주면 됨.
	 */
	/*
	 * 양방향 매핑의 규칙 : 연관관계의 주인
	 * - DB가 외래키 하나로 양방향이 되지만, 객체는 참조로 관계를 갖기 때문에,
	 * - 양방향이 되려면, 두 객체가 서로를 참조해야 한다.
	 * - 외래키가 하나인데 참조는 두개이므로, 테이블의 외래키를 관리하는 연관관계 주인을 설정해야 한다.
	 * - 연관관계 주인만이 DB 연관관계와 매핑되고, 외래키를 관리(등록,수정,삭제) 할 수 있다.
	 * - 주인이 아닌쪽은 읽기만 가능.
	 * - 1. 주인은 mappedBy 속성을 사용하지 않는다.
	 * - 2. 주인이 아니면 mappedBy 속성을 사용해서 연관관계 주인 지정.
	 * 
	 * - 연관관계의 주인은 테이블에 외래키가 있는 곳으로 정해야 한다.
	 * - DB 테이블의 다대일, 일대다 관계에서는 항상 다 쪽이 외래키를 가진다. 따라서, @ManyToOne에는 mappedBy 속성이 없다.
	 */
	@OneToMany(mappedBy = "team")	// 외래키가 MEMBER 테이블에 있으므로, 주인도 Member 클래스가 된다.
									// 해당 속성의 값은 Member.team의 team
	private List<Member> members = new ArrayList<>();
	
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
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
}
