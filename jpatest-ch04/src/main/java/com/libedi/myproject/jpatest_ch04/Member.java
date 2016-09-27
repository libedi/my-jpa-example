package com.libedi.myproject.jpatest_ch04;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	@Id
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
