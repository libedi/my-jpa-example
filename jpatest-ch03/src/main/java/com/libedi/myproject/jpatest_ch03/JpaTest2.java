package com.libedi.myproject.jpatest_ch03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 엔티티 수정 및 삭제 예제
 * @author libedi
 *
 */
public class JpaTest2 {

	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();	// 트랜잭션 시작
		
		// 영속 엔티티 조회
		Member memberA = em.find(Member.class, "memberA");
		
		// 영속 엔티티 데이터 수정 : 변경 감지
		memberA.setUsername("hi");
		memberA.setAge(10);
		
		// em.update(memberA); 이런 코드가 있어야 하지 않을까?
		
		// 엔티티 삭제
		// em.refresh(memberA);	// 영속성 컨텍스트에서 제거되므로, 삭제된 엔티티는 재사용하지 말자.
		
		transaction.commit();	// 트랜잭션 커밋
		
		/*
		 * 변경 감지
		 * - JPA는 엔티티를 영속성 컨텍스트에 보관할 때, 최초 상태를 복사해서 저장해두는데 이를 스냅샷이라 한다.
		 * - 플러시 시점에 스냅샷과 엔티티를 비교해서 변경된 엔티티를 찾는다.
		 * 1. 트랜잭션을 커밋하면 엔티티 매니저 내부에서 먼저 flush() 가 호출된다.
		 * 2. 엔티티와 스냅샷을 비교해서 변경된 엔티티를 찾는다.
		 * 3. 변경된 엔티티가 있으면 수정쿼리를 생성해서 쓰기 지연 SQL 저장소에 보낸다.
		 * 4. 쓰기 지연 저장소의 SQL을 데이터베이스에 보낸다.
		 * 5. 데이터베이스 트랜잭션을 커밋한다.
		 * 
		 * - 변경감지는 영속성 컨텍스트가 관리하는 영속상태의 엔티티에만 적용된다.
		 * - JPA 기존전략은 엔티티의 모든 필드를 업데이트 한다.
		 * 1. 수정쿼리가 항상 같기 때문에, 애플리케이션 로딩 시점에 수정쿼리를 미리 생성해두고 재사용할 수 있다.
		 * 2. DB에 동일한 쿼리를 보내면 DB는 이전에 한번 파싱된 쿼리를 재사용할 수 있다.
		 * 
		 */
	}
}
