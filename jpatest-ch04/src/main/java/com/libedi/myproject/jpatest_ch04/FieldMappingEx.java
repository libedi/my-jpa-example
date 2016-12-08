package com.libedi.myproject.jpatest_ch04;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class FieldMappingEx {
	@Id
	@Column(name="ID")
	private String id;
	
	/*
	 * 필드와 컬럼 매핑 : 레퍼런스
	 * 
	 * 1. @Column : 객체 필드를 테이블 컬럼에 매핑.
	 * - name : 필드와 매핑할 테이블의 컬럼이름. 기본값 - 객체의 필드 이름
	 * - nullable : (DDL) null값의 허용 여부를 설정한다. false로 설정하면 DDL 생성시 not null 제약조건이 붙는다. 기본값 - true
	 * - insertable / updatable : 엔티티 저장/수정 시 이 필드도 같이 저장/수정한다. false는 읽기 전용일 경우. 기본값 - true
	 * - table : 하나의 엔티티를 두 개 이상의 테이블에 매핑할 경우 사용. 기본값 - 현재 클래스가 매핑한 테이블
	 * - unique : (DDL) @Table의 uniqueConstraints와 같지만 한 컬럼에 간단히 유니트 제약조건을 걸 때 사용. 만약, 두 컬럼 이상을 사용할 경우, 클래스 레벨에서 @Table.uniqueConstraints 사용.
	 * - columnDefinition : (DDL) 데이터베이스 컬럼 정보를 직접 줄 수 있다. (columnDefinition="varchar(100) default 'EMPTY'"). 기본값 - 필드의 자바 타입과 방언 정보를 사용해 적절한 컬럼 타입 생성.
	 * - length : (DDL) 문자 길이 제약조건, String 타입에만 사용. 기본값 - 255
	 * - precision, scale : (DDL) BigDeciaml 타입에서 사용. precision은 소수점 포함 전체 차릿수, scale은 소수점 자릿수. 기본값 - precision = 19, scale = 2
	 */
	@Column(name="NAME")
	private String username;
	
	/*
	 * 2. @Enumerated : enum 타입을 데이터베이스에 저장.
	 * - EnumType.ORDIANL : enum에 정의된 순서대로 저장. (Enum.ordianl()) 
	 * - EnumType.STRING : enum 이름 그대로 저장.(Enum.toString())
	 */
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
	/*
	 * 3. @Temporal : 날짜 타입을 매핑할 때 사용. (Date, Calendar)
	 * - value : Temporal.DATE - 날짜, 데이터베이스 date 타입과 매핑.(ex:2016-12-08)
	 *         : Temporal.TIME - 시간, 데이터베이스 time 타입과 매핑.(ex:12:17:00)
	 *         : Temporal.TIMESTAMP - 날짜와 시간, 데이터베이스 timestamp 타입과 매핑.(ex:2016-12-08 12:17:00)
	 */
	@Temporal(TemporalType.DATE)
	private Date date;
	@Temporal(TemporalType.TIME)
	private Date time;
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	/*
	 * 4. @Lob : BLOB, CLOB 타입과 매핑.
	 * - 지정할 수 있는 속성이 없다. 대신 자바 필드 타입이 문자면 CLOB, 나머지는 BLOB으로 매핑
	 * - CLOB : String, char[], java.sql.CLOB
	 * - BLOB : byte[], java.sql.BLOB
	 */
	@Lob
	private String lobString;
	@Lob
	private byte[] lobByte;
	
	/*
	 * 5. @Transient : 이 필드는 매핑하지 않는다.
	 * - DB에 저장하지 않고, 조회하지도 않는다.
	 * - 객체에 임시로 어떤 값을 보관하고 싶을 때 사용.
	 */
	@Transient
	private Integer temp;
	
	/*
	 * 6. Access : 엔티티 데이터에 접근하는 방식을 지정.
	 * - 필드 접근 : AccessType.FIELD 로 지정. 필드에 직접 접근. private 이어도 접근 가능. @Id가 필드에 있으면 생략가능.
	 * - 프로퍼티 접근 : AccessType.PROPERTY 로 지정. 접근자(getter)를 사용. @Id가 접근자에 있으면 생략가능.
	 * - 필드와 프로퍼티 접근을 함께 사용 가능.
	 */
	@Entity
	class Test2 {
		@Id
		private String id;	// 필드 접근방식
		@Transient
		private String firstName;
		@Transient
		private String lastName;
		
		@Access(AccessType.PROPERTY)	// 프로퍼티 접근방식
		public String getFullName(){
			return firstName + lastName;
		}
	}
	
}
