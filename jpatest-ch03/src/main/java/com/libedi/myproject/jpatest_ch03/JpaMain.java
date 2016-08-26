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
	 * 영속성 컨텍스트
	 * - 1차 캐시 : connection 연결 횟수가 줄어듦으로 성능상 이점을 누린다.
	 * - 동일성 보장 : 1차 캐시를 통해 동일한 인스턴스 유지가 가능. 인스턴스간 == 비교 가능
	 * - 트랜잭션을 지원하는 쓰기 지연
	 * - 변경 감지
	 * - 지연 로딩
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
		// 1차 캐시에 저장됨
		em.persist(member);
		
		// 수정
		member.setAge(36);
		
		/*
		 * 1차 캐시에서 조회
		 * - 엔티티가 1차 캐시에 없으면 엔티티 매니저는 DB에서 조회해서 엔티티를 생성한다. 그리고 1차 캐시에 저장한 후에 영속 상태의 엔티티를 반환한다.
		 */
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
