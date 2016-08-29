package com.libedi.myproject.jpatest_ch03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest1 {
	
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		// 엔티티 매니저는 데이터 변경 시 트랜잭션을 시작해야 한다.
		transaction.begin();	// 트랜잭션 시작
		
		Member memberA = new Member();
		Member memberB = new Member();
		
		em.persist(memberA);
		em.persist(memberB);
		// 여기까지 INSERT SQL을 DB에 보내지 않는다. But, 1차 캐시에는 보관하여 엔티티를 영속화 한다.
		
		// 커밋하는 순간 DB에 INSERT SQL을 보낸다. - 쓰기 지연 : 트랜잭션 커밋 직전까지 내부 쿼리 저장소에 쿼리를 모아둔다.
		transaction.commit();	// 트랜잭션 커밋
		
		em.close();
		emf.close();
	}
}
