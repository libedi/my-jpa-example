package com.libedi.myproject.jpatest_ch03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 준영속 테스트
 * - em.detach()
 * - em.clear()
 * - em.close()
 * @author libedi
 *
 */
public class JpaTest3_detached {

	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		// 1. em.detach() - 준영속 상태 전환
		// 회원 엔티티 생성, 비영속 상태
		Member member1 = new Member();
		member1.setId("memberA");
		member1.setUsername("회원A");
		// 회원 엔티티 영속 상태
		em.persist(member1);
		/*
		 * 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태.
		 * - 이 메소드를 호출하는 순간, 1차 캐시부터 쓰기 지연 SQL 저장소까지
		 * - 해당 엔티티를 관리하기 위한 모든 정보가 제거된다.
		 */
		em.detach(member1);

		
		// 2. em.clear() - 영속성 컨텍스트 초기화
		// 엔티티 조회, 영속 상태
		Member member2 = em.find(Member.class, "memberA");
		/*
		 * 영속성 컨텍스트 초기화
		 * - 영속성 컨텍스트를 초기화해서 해당 영속성 컨텍스트의 모든 엔티티를 준영속 상태로 만든다.
		 * - 영속성 컨텍스트를 제거하고 새로 만든 것과 같은 상태.
		 */
		em.clear();
		// 준영속 상태
		member2.setUsername("changeName");
		
		
		// 3. em.close() - 영속성 컨텍스트 종료
		Member memberA = em.find(Member.class, "memberA");
		Member memberB = em.find(Member.class, "memberB");
		
		transaction.commit();	// 트랜잭션 커밋
		
		em.close();		// 영속성 컨텍스트 종료
		
		/*
		 * 준영속 상태의 특징
		 * 
		 * 1. 거의 비영속 상태에 가깝다.
		 *  - 영속성 컨텍스트가 제공하는 어떠한 기능도 동작하지 않는다.
		 * 
		 * 2. 식별자 값을 가지고 있다.
		 *  - 비영속 상태는 식별자 값이 없을 수도 있지만,
		 *  - 준영속 상태는 이미 한번 영속 상태였으므로 반드시 식별자 값을 가지고 있다.
		 * 
		 * 3. 지연 로딩을 할 수 없다.
		 */
	}
}
