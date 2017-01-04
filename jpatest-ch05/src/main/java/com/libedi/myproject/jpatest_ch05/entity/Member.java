package com.libedi.myproject.jpatest_ch05.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {
	/*
	 * CREATE TABLE MEMBER (
	 * 	MEMBER_ID VARCHAR(255) NOT NULL,
	 * 	TEAM_ID VARCHAR(255),
	 * 	USERNAME VARCHAR(255),
	 * 	PRIMARY KEY(MEMBER_ID)
	 * );
	 * 
	 * ALTER TABLE MEMBER ADD CONSTRAINT FK_MEMBER_TEAM
	 * 	FOREIGN KEY(TEAM_ID)
	 * 	REFERENCES TEAM;
	 */
	@Id
	@Column(name = "MEMBER_ID")
	private String id;
	
	private String username;
	
	public Member() {
	}
	
	public Member(String id, String username) {
		this.id = id;
		this.username = username;
	}
	
	/*
	 * 연관관계 매핑 : 객체 연관관계와 테이블 연관관계를 매핑
	 * - @ManyToOne : 다대일 관계 매핑정보. 연관관계 매핑에서 다중성을 나타내는 어노테이션은 필수.
	 * - @JoinColumn(name="TEAM_ID") : 외래키 매핑시 사용. 이 어노테이션은 생략 가능.
	 * 
	 */
	
	/*
	 * @JoinColumn : 외래키를 매핑할 때 사용.
	 * - name : 매핑할 외래키 이름. 기본값 : 필드명 + "_" + 참조하는 테이블의 기본 키 컬러명 (여기서는 team_TEAM_ID)
	 * - referenecedColumnName : 외래 키가 참조하는 대상 테이블의 컬럼명. 기본값 : 참조하는 테이블의 기본키 컬럼명
	 * - foreignKey : (DDL) 외래 키 제약조건을 직접 지정할 수 있다. 이 속성은 테이블 생성시에만 사용.
	 * - unique, nullable, insertable, updatable, columnDefinition, table : @Column 속성과 동일
	 * 
	 */
	
	/*
	 * @ManyToOne : 다대일 관계에서 사용.
	 * - optional : false로 설정하면 연관된 엔티티가 항상 있어야 한다. 기본값 : true
	 * - fetch : 글로벌 페치 전략 설정. 기본값 : @ManyToOne=FetchType.EAGER, @OneToMany=FetchType.LAZY
	 * - cascase : 영속성 전이 기능을 사용.
	 * - targetEntity : 연관된 엔티티의 타입 정보를 설정. 사실 제네릭을 통해 알 수 있으므로 거의 사용 안함.
	 * ex)
	 * @OneToMany
	 * private List<Member> members;	// 제네릭으로 타입 정보를 알 수 있다.
	 * @OneToMany(targetEntity = Member.class)
	 * private List members;	// 제네릭이 없으면 타입 정보를 알 수 없다.
	 */
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;	// 팀의 참조를 보관

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
		// 기존 팀과 관계를 제거
		if(this.team != null){
			this.team.getMembers().remove(this);
		}
		this.team = team;
		/*
		 * 양방향 연관관계 설정시 양쪽 모두 신경써야 하므로,
		 * 양쪽 모두 관계를 맺어주는 것을 편리하게 하기 위해,
		 * 아래 코드를 추가한다.
		 */
		team.getMembers().add(this);
	}
	
}
