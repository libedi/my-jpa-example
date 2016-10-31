package com.libedi.myproject.jpatest_ch04;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="MEMBER",
		/*
		 * 유니크 제약조건 추가
		 */
		uniqueConstraints = {@UniqueConstraint(
			name = "NAME_AGE_UNIQUE",
			columnNames = {"NAME", "AGE"}
		)})
public class Member {

	/**
	 * 기본 키 매핑
	 * - 키 생성 전략을 사용하려면 persistence.xml 에
	 * - hibernate.id.new_generator_mappings=true 속성을 반드시 추가해야 한다.
	 * 
	 * 1. 기본 키 직접 할당 전략 : 기본 키를 애플리케이션에서 직접 할당
	 * - @Id 로 매핑하면 된다.
	 * 
	 * 2. IDENTITY 전략 : 기본 키 생성을 DB에 위임하는 전략
	 * - MySQL의 AUTO_INCREMENT와 같이 DB에서 자동으로 생성해주는 전략.
	 * - @GeneratedValue(strategy = GenerationType.IDENTITY) 어노테이션을 사용한다.
	 * - em.persist() 호출해서 엔티티를 저장한 후에 식별자 값을 얻어온다.
	 * - 엔티티가 영속상태가 되려면 식별자가 반드시 필요한데, INSERT 후에 얻을 수 있으므로, 트랜잭션을 지원하는 쓰기 지연은 동작하지 않는다.
	 */
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private String id;
	
	/*
	 * nullable = false : 자동 생성되는 DDL에 not null 제약 조건을 추가할 수 있다.
	 * length : 자동 생성되는 DDL에 문자의 크기를 지정할 수 있다.
	 */
	@Column(name="NAME", nullable = false, length = 10)	// 제약조건 추가
	private String username;
	
	private Integer age;
	
	// == 추가 ==
	/*
	 * 자바의 enum을 사용해서 회원의 타입을 구분.
	 * enum을 사용하려면 @Enumerated 어노테이션으로 매핑.
	 */
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
	/*
	 * 자바의 날짜 타입은 @Temporal 을 사용해서 매핑.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	/*
	 * CLOB, BLOB 타입을 매핑하려면 @Lob 을 사용.
	 */
	@Lob
	private String description;
	
	// getter, setter
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
