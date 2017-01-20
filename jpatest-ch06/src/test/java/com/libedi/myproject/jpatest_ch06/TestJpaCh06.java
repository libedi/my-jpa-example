package com.libedi.myproject.jpatest_ch06;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 6장. 다양한 연관관계 매핑
 * @author libedi
 *
 */
public class TestJpaCh06 {
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
	public void finish() throws Exception{
		tx.commit();
		em.close();
		emf.close();
	}
	
	/**
	 * ex>6.7 - 일대다 단방향 매핑의 단점
	 * @throws Exception
	 */
	@Test
	public void testOneToMany() throws Exception{
		com.libedi.myproject.jpatest_ch06.one_to_many.Member member1 = new com.libedi.myproject.jpatest_ch06.one_to_many.Member();
		com.libedi.myproject.jpatest_ch06.one_to_many.Member member2 = new com.libedi.myproject.jpatest_ch06.one_to_many.Member();
		
		com.libedi.myproject.jpatest_ch06.one_to_many.Team team1 = new com.libedi.myproject.jpatest_ch06.one_to_many.Team();
		team1.getMembers().add(member1);
		team1.getMembers().add(member2);
		
		em.persist(member1);	// INSERT - member1
		em.persist(member2);	// INSERT - member2
		em.persist(team1);		// INSERT - team1, UPDATE - member1.fk, UPDATE - member2.fk
		/*
		 * 다른 테이블에 외래키가 있으면, 연관관게 처리를 위한 UPDATE SQL을 추가로 실행해야 한다.
		 * - Member는 Team 엔티티를 모르므로, TEAM_ID 외래키에 아무값도 저장되지 않는다.
		 * - Team 엔티티를 저장할 때, Team.members의 참조값을 확인해서 MEMBER 테이블의 TEAM_ID 외래키를 업데이트 한다.
		 * : 일대다 단반향 매핑보다는 다대일 단방향 매핑을 사용하자!
		 */
	}
	
	/**
	 * ex>6.15 - 다대다 단방향 저장
	 * @throws Exception
	 */
	@Test
	public void testSaveManyToManySingleDir() throws Exception{
		com.libedi.myproject.jpatest_ch06.many_to_many.Product productA = new com.libedi.myproject.jpatest_ch06.many_to_many.Product();
		productA.setId("ProductA");
		productA.setName("상품A");
		em.persist(productA);
		
		com.libedi.myproject.jpatest_ch06.many_to_many.Member member1 = new com.libedi.myproject.jpatest_ch06.many_to_many.Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		member1.getProducts().add(productA);	// 연관관계 설정
		em.persist(member1);
	}
	
	/**
	 * ex>6.16 - 다대다 단방향 탐색
	 * @throws Exception
	 */
	@Test
	public void testFindManyToManySingleDir() throws Exception{
		com.libedi.myproject.jpatest_ch06.many_to_many.Member member = em.find(com.libedi.myproject.jpatest_ch06.many_to_many.Member.class, "member1");
		List<com.libedi.myproject.jpatest_ch06.many_to_many.Product> products = member.getProducts();	// 객체 그래프 탐색
		for(com.libedi.myproject.jpatest_ch06.many_to_many.Product product : products){
			System.out.println("product.name = " + product.getName());
		}
	}
}
