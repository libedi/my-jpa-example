package com.libedi.myproject.jpatest_ch04;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/*
 * @SequenceGenerator
 * ** 속성 **
 * - name : 식별자 생성기 이름, 필수값
 * - sequenceName : DB에 등록되어 있는 시퀀스 이름, 기본값 hibernate_sequence
 * - initialValue : DDL 생성 시에만 사용, 시퀀스 DDL을 생성할 때 처음 시작하는 수를 지정, 기본값 1
 * - allocationSize : 시퀀스 한 번 호출에 증가하는 수(성능 최적화에 사용), 기본값 50
 * - catalog, schema : 데이터 베이스 catalog, schema 이름
 * 
 * ** 전략과 최적화 **
 * - SEQUENCE 전략은 DB 시퀀스를 통해 식별자를 조회하는 추가 작업이 필요하여 DB와 2번 통신한다.
 * - 따라서 allocationSize 를 통해 미리 시퀀스 값을 가져오고, 메모리에서 할당한다. 
 */
@Entity
@SequenceGenerator(
		/*
		 * 시퀀스 생성
		 * CREATE SEQUENCE BOARD_SEQ START WITH 1 INCREMENT BY 1;
		 * - SEQUENCE 전략은 em.persist() 를 호출할 때, 먼저 DB 시퀀스를 사용해서 식별자를 조회한다.
		 * - 그리고 조회한 시퀀스를 엔티티에 할당한 후에 엔티티를 영속성 컨텍스트에 저장한다.
		 * - 이후, 트랜잭션을 커밋해서 플러시가 일어나면 엔티티를 DB에 저장한다.
		 */
		name = "BOARD_SEQ_GENERATOR",	// 시퀀스 생성기 등록
		sequenceName = "BOARD_SEQ",		// 매핑할 DB 시퀀스 이름
		initialValue = 1,
		allocationSize = 1)
public class BoardSeq {
	@Id
	@GeneratedValue(    
			strategy = GenerationType.SEQUENCE,
			generator = "BOARD_SEQ_GENERATOR")
//	@SequenceGenerator() - 여기에 사용해도 된다.
	private Long id;
}
