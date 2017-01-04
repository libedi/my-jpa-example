package com.libedi.myproject.jpatest_ch05;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.libedi.myproject.jpatest_ch05.entity.Member;
import com.libedi.myproject.jpatest_ch05.entity.Team;

/**
 * 5장. 연관관계 매핑 기초
 * @author libedi
 *
 */
public class TestJpa {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	
	@Before
	public void setUp() throws Exception{
		this.emf = Persistence.createEntityManagerFactory("jpabook");
		this.em = emf.createEntityManager();
		this.tx = em.getTransaction();
		this.tx.begin();
	}
	
	@After
	public void end() throws Exception{
		this.tx.commit();
		this.em.close();
		this.emf.close();
	}
	
	/**
	 * ex>5.6 : 회원과 팀을 저장하는 코드
	 * @throws Exception
	 */
//	@Test
	public void testSave() throws Exception{
		// 팀1 저장
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		
		// 회원1 저장
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1);		// 연관관계 설정 : member1 -> team1
		em.persist(member1);
		
		// 회원2 저장
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1);		// 연관관계 설정 : member2 -> team1
		em.persist(member2);
	}
	
	/**
	 * 객체 그래프 탐색 조회
	 * @throws Exception
	 */
//	@Test
	public void testSelectByObjectGraph() throws Exception{
		Member member = em.find(Member.class, "member1");
		Team team = member.getTeam();
		System.out.println("팀 이름 : " + team.getName());
		assertTrue(true);
	}
	
	/**
	 * ex>5.7 : 객체지향 쿼리 사용 조회 (JPQL)
	 * @throws Exception
	 */
//	@Test
	public void testSelectByJPQL() throws Exception{
		/*
		 * 회원이 팀과 관계를 가지고 있는 필드(m.team) 을 통해서 Member와 Team 을 조인.
		 * - JPQL은 테이블이 아닌 엔티티가 대상.
		 */
		String jpql = "select m from Member m join m.team t where t.name=:teamName";
		
		List<Member> resultList = em.createQuery(jpql, Member.class)
				.setParameter("teamName", "팀1")
				.getResultList();
		
		resultList.stream().forEach(m -> System.out.println("[query] member.username=" + m.getUsername()));
		assertTrue(true);
	}
	
	/**
	 * ex>5.8 : 연관관계 수정
	 * @throws Exception
	 */
//	@Test
	public void testUpdateRelation() throws Exception{
		// 새로운 팀2
		Team team2 = new Team("team2", "팀2");
		em.persist(team2);
		
		// 회원1에 새로운 팀2 설정
		Member member1 = em.find(Member.class, "member1");
		member1.setTeam(team2);
		/*
		 * 수정은 em.update() 같은 메소드 가 없다.
		 * 단지 불러온 엔티티의 값만 변경해두면, 트랜잭션을 커밋할 때 플러시가 일어나면서 변경 감지 기능이 작동.
		 * 그리고 변경사항을 DB에 자동으로 반영.
		 * 연관관계 수정도 동일. 참조대상만 변경하면 JPA가 자동으로 처리.
		 */
	}
	
	/**
	 * ex>5.9 : 연관관계 삭제
	 * @throws Exception
	 */
//	@Test
	public void testDeleteRelation() throws Exception{
		Member member1 = em.find(Member.class, "member1");
		member1.setTeam(null);		// 연관관계 제거
	}
	
	/**
	 * 연관된 엔티티 삭제
	 * @throws Exception
	 */
//	@Test
	public void testDeleteRelationEntity() throws Exception{
		Team team1 = em.find(Team.class, "team1");
		Member member1 = em.find(Member.class, "member1");
		Member member2 = em.find(Member.class, "member2");
		member1.setTeam(null);		// 회원1 연관관계 제거
		member2.setTeam(null);		// 회원2 연관관계 제거
		em.remove(team1);			// 팀1 삭제
		/*
		 * 연관된 엔티티를 삭제하려면, 기존에 있던 연관관계를 먼저 제거하고 삭제해야 한다.
		 * 그렇지 않으면, 외래키 제약조건으로 DB 오류가 발생.
		 */
	}
	
	/**
	 * ex>5.12 : 일대다 방향으로 객체 그래프 탐색
	 * @throws Exception
	 */
//	@Test
	public void testBiDirection() throws Exception{
		Team team = em.find(Team.class, "team1");
		List<Member> members = team.getMembers();	// 팀 -> 회원. 객체 그래프 탐색
		
		members.stream().forEach(m -> System.out.println("member.username = " + m.getUsername()));
	}
	
	/**
	 * ex>5.13 : 양방향 연관관계 저장
	 * @throws Exception
	 */
//	@Test
	public void testSaveBiDirection() throws Exception{
		// 팀1 저장
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		
		/*
		 * 연관관계의 주인인 엔티티에 연관관계 설정을 한다.
		 */
		// 회원1 저장
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1);	// 연관관계 설정 : member1 -> team1
		em.persist(member1);
		
		// 회원2 저장
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1);	// 연관관계 설정 : member2 -> team1
		em.persist(member2);
		
		// But, 객체 상태에서 완전한 양방향이라고 할 수 있을까? -> team1 size : 0
		System.out.println("team1 size : " + team1.getMembers().size());
	}
	
	/**
	 * ex>5.14 : 양방향 연관관계 주의점
	 * @throws Exception
	 */
//	@Test
	public void testSaveNonOwner() throws Exception{
		// 회원1 저장
		Member member1 = new Member("member1", "회원1");
		em.persist(member1);
		
		// 회원2 저장
		Member member2 = new Member("member2", "회원2");
		em.persist(member2);
		
		Team team1 = new Team("team1", "팀1");
		/*
		 * 주인이 아닌 곳만 연관관계 설정
		 * - 연관관계의 주인만이 외래키 값을 변경할 수 있다!!!
		 * - 따라서 아래 코드는 MEMBER 테이블에 아무런 영향을 끼치지 않는다!
		 */
		team1.getMembers().add(member1);	// 아무런 SQL도 실행안됨
		team1.getMembers().add(member2);	// 아무런 SQL도 실행안됨
		em.persist(team1);
	}

	/**
	 * ex>5.15 : 순수한 객체 연관관계
	 * @throws Exception
	 */
//	@Test
	public void test순수한객체_양방향() throws Exception{
		// 팀1
		Team team1 = new Team("team1", "팀1");
		Member member1 = new Member("member1", "회원1");
		Member member2 = new Member("member2", "회원2");
		
		member1.setTeam(team1);	// 연관관계 설정 : member1 -> team1
		member2.setTeam(team1);	// 연관관계 설정 : member2 -> team1
		
		System.out.println("members.size = " + team1.getMembers().size());
	}
	
	/**
	 * ex>5.17 : JPA로 양방향 모두 관계설정 코드 완성
	 */
//	@Test
	public void testORM_bidirection(){
		// 팀1 저장
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		
		Member member1 = new Member("member1", "회원1");
		// 양방향 연관관계 설정
		member1.setTeam(team1);				// 연관관계 설정 : member1 -> team1
		team1.getMembers().add(member1);	// 연관관계 설정 : team1 -> member1
		em.persist(member1);
		
		Member member2 = new Member("member2", "회원2");
		// 양방향 연관관계 설정
		member2.setTeam(team1);				// 연관관계 설정 : member2 -> team1
		team1.getMembers().add(member2);	// 연관관계 설정 : team1 -> member2
		em.persist(member2);
		
		/*
		 * Member.team : 연관관계 주인. 이 값으로 외래키를 관리한다.
		 * Team.members : 연관관계의 주인이 아니다. 따라서 저장 시에 사용되지 않는다.
		 * 
		 * 결론 : 객체의 양방향 연관관계는 양쪽 모두 관계를 맺어주자!
		 */
	}
	
	/**
	 * ex>5.18 : 양방향 리팩토링 코드
	 */
	@Test
	public void testORM_bidirection_refactoriong(){
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1);			// 양방향 설정
		em.persist(member1);
		
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1);			// 양방향 설정
		em.persist(member2);
	}
	
}
