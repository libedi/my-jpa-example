package com.libedi.myproject.jpatest_ch02;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Member Entity class
 * @author libedi
 *
 */
@Entity
@Table(name = "MEMBER")
public class Member {
	/*
	 * 1. @Entity
	 * - 이 클래스를 테이블과 매핑한다고 JPA에게 알려준다. 
	 * - @Entity가 사용된 클래스를 엔티티 클래스라고 한다.
	 * 
	 * 2. @Table
	 * - 엔티티 클래스에 매핑할 테이블 정보를 알려준다.
	 * - 생략하면 클래스명으로 테이블 이름을 매핑
	 * 
	 * 3. @Id
	 * - 엔티티 클래스의 필드를 PK에 매핑한다.
	 * - 식별자 필드라고 한다.
	 * 
	 * 4. @Column
	 * - 필드를 컬럼에 매핑
	 * - 생략하면 필드명으로 컬럼명을 매핑한다.
	 * - DB가 대소문자 구분시에는 명시적으로 매핑해야 한다.
	 */

	@Id
	@Column(name = "ID")
	private String id;			// 아이디
	
	@Column(name = "NAME")
	private String username;	// 이름
	
	// 매핑정보가 없는 필드
	private Integer age;		// 나이
	
	// Getter, Setter
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
	
}
