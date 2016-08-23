package com.libedi.myproject.jpatest_ch02;

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
		/*
		 *  1. 엔티티 매니저 팩토리 생성
		 *  - Persistence 클래스가 persistence.xml 의 설정정보를 읽어와 엔티티 매니저 팩토리를 생성한다.
		 *  - 엔티티 매니저 팩토리 생성 비용이 크므로, 애플리케이션 전체에서 딱 한번만 생성하고 공유해서 사용해야 한다.
		 */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		
		/*
		 *  2. 엔티티 매니저 생성
		 *  - JPA 기능 대부분은 이 엔티티 매니저가 제공한다.
		 */
		EntityManager em = emf.createEntityManager();
		
		/*
		 *  3. 엔티티 트랜잭션 획득
		 *  - JPA를 사용하려면 항상 트랜잭션 안에서 데이터를 변경해야 한다.
		 *  - 엔티티 매니저에서 트랜잭션 API를 받아온다.
		 */
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

	// 비즈니스 로직
	private static void logic(EntityManager em) {
		String id = "id1";
		Member member = new Member();
		member.setId(id);
		member.setUsername("상준");
		member.setAge(35);
		
		// 등록
		em.persist(member);
		
		// 수정
		member.setAge(36);
		
		// 한건 조회
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember = " + findMember.getUsername() + ", age=" + findMember.getAge());
		
		/*
		 *  목록 조회
		 *  - JPQL (Java Persistence Query Language)
		 *  - 엔티티 객체를 대상으로 쿼리한다. ("from Member" 에서 Member는 엔티티 객체이지 MEMBER 테이블이 아니다.)
		 */
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("members.size = " + members.size());
		
		// 삭제
		em.remove(member);
	}
}
