package com.libedi.myproject.jpatest_ch04;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/*
 * @TableGenerator
 * ** 속성 **
 * - name : 식별자 생성기 이름, 필수값
 * - table : 키 생성 테이블, 기본값 : hibernate_sequences
 * - pkColumnName : 시퀀스 컬럼명, 기본값 : sequence_name
 * - valueColumnName : 시퀀스 값 컬럼명, 기본값  : next_val
 * - pkColumnValue : 키로 사용할 값 이름, 기본값 : 엔티티 이름
 * - initialValue : 초기값, 마지막으로 생성된 값이 기준, 기본값 : 0
 * - allocationSize : 시퀀스 한 번 호출에 증가하는 수(성능 최적화에 사용), 기본값 : 50
 * - catalog, schema : 데이터베이스 catalog, schema 이름
 * - uniqueConstraints : (DDL) 유니크 제약 조건을 지정할 수 있다.
 */
@Entity
@TableGenerator(
		/*
		 * 테이블 생성
		 * create table MY_SEQUENCES (
		 *   sequence_name varchar(255) not null,
		 *   next_val bigint,
		 *   primary key(sequence_name)
		 * )
		 * - @TableGenerator 를 사용해서 테이블 키 생성기를 등록.
		 * - 여기서는 BOARD_SEQ_GENERATOR 라는 이름의 테이블 키 생성기를 등록하고, MY_SEQUENCES 테이블을 키 생성용 테이블로 매핑.
		 * - TABLE 전략을 사용하기 위해 GenerationType.TABLE을 사용하고,
		 * - @GeneratedValue.generator 에 방금 만든 테이블 키 생성기를 지정.
		 * - SEQUENCE 전략과 내부동작이 동일.
		 */
		name = "BOARD_SEQ_GENERATOR",
		table = "MY_SEQUENCES",
		pkColumnValue = "BOARD_SEQ",
		allocationSize = 1)
public class BoardTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,
		generator = "BOARD_SEQ_GENERATOR")
	private Long id;
}
