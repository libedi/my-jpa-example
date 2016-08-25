package com.libedi.myproject.jpatest_ch03;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * JPA 애플리케이션
 * @author libedi
 *
 */
public class JpaMain {

	public static void main(String[] args){
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

	/*
	 * 영속성 컨텍스트 설명
	 */
	private static void logic(EntityManager em) {
		String id = "id1";
		
		/*
		 * 1. 비영속 상태 (New)
		 * - 영속성 컨텍스트와 관계가 없는 상태
		 */
		Member member = new Member();
		member.setId(id);
		member.setUsername("상준");
		member.setAge(35);
		
		/*
		 * 2. 영속 상태 (Managed)
		 * - 영속성 컨텍스트에 의해 엔티티가 관리되는 상태
		 * - em.persist(), em.find(), JPQL
		 */
		em.persist(member);
		
		// 수정
		member.setAge(36);
		
		// 한건 조회
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember = " + findMember.getUsername() + ", age=" + findMember.getAge());
		
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("members.size = " + members.size());
		
		/*
		 * 3. 준영속 상태
		 * - 영속성 컨테스트에서 분리된 상태
		 * - em.detach(), em.clear(), em.close()
		 */
		em.detach(members);
		
		/*
		 * 4. 삭세 상태
		 * - 영속성 컨텍스트 또는 DB에서 엔티티가 삭제된 상태 
		 * - em.remove()
		 */
		em.remove(member);
	}
}
