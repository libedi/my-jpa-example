package com.libedi.myproject.jpatest_ch04;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try{
			tx.begin();		// 트랜잭션 시작
			logic(em);		// 비즈니스 로직 실행
			tx.commit();	// 트랜잭션 커밋 
		} catch(Exception e){
			tx.rollback();	// 트랜잭션 롤백
		} finally{
			em.close();		// 엔티티 매니저 종료
		}
		emf.close();		// 엔티티 매니저 팩토리 종료
	}

	private static void logic(EntityManager em) {
		String id = "id1";
		
		Member member = new Member();
		member.setId(id);
		member.setUsername("상준");
		member.setAge(35);
		
		em.persist(member);
		
		member.setAge(36);
		
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember = " + findMember.getUsername() + ", age=" + findMember.getAge());
		
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("members.size = " + members.size());
		
		em.detach(members);
		
		em.remove(member);
	}
}
