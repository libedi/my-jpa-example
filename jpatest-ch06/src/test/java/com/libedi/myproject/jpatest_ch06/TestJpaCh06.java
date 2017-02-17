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
		com.libedi.myproject.jpatest_ch06.one_to_many.Member member1 = 
				new com.libedi.myproject.jpatest_ch06.one_to_many.Member();
		com.libedi.myproject.jpatest_ch06.one_to_many.Member member2 = 
				new com.libedi.myproject.jpatest_ch06.one_to_many.Member();
		
		com.libedi.myproject.jpatest_ch06.one_to_many.Team team1 = 
				new com.libedi.myproject.jpatest_ch06.one_to_many.Team();
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
	public void testManyToManySaveSingleDir() throws Exception{
		com.libedi.myproject.jpatest_ch06.many_to_many.Product productA = 
				new com.libedi.myproject.jpatest_ch06.many_to_many.Product();
		productA.setId("ProductA");
		productA.setName("상품A");
		em.persist(productA);
		
		com.libedi.myproject.jpatest_ch06.many_to_many.Member member1 = 
				new com.libedi.myproject.jpatest_ch06.many_to_many.Member();
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
	public void testManyToManyFindSingleDir() throws Exception{
		com.libedi.myproject.jpatest_ch06.many_to_many.Member member = 
				em.find(com.libedi.myproject.jpatest_ch06.many_to_many.Member.class, "member1");
		List<com.libedi.myproject.jpatest_ch06.many_to_many.Product> products = member.getProducts();	// 객체 그래프 탐색
		for(com.libedi.myproject.jpatest_ch06.many_to_many.Product product : products){
			System.out.println("product.name = " + product.getName());
		}
	}
	
	/**
	 * ex>6.18 - 다대다 양방향 역방향 탐색
	 * @throws Exception
	 */
	@Test
	public void testManyToManyFindDualInverse() throws Exception {
		com.libedi.myproject.jpatest_ch06.many_to_many.Product product = 
				em.find(com.libedi.myproject.jpatest_ch06.many_to_many.Product.class, "productA");
		List<com.libedi.myproject.jpatest_ch06.many_to_many.Member> members = product.getMembers();
		for(com.libedi.myproject.jpatest_ch06.many_to_many.Member member : members) {
			System.out.println("member = " + member.getUsername());
		}
	}
	/*
	 * 다대다 매핑의 한계와 극복, 연결 엔티티 사용
	 * - @ManyToMany를 사용하면 자동으로 매핑테이블을 처리해주므로 편리하다.
	 * - 하지만 실무에서 사용하기에는 한계가 있다.
	 * - 매핑테이블에 다른 컬럼들이 들어가면 (예를 들어 주문수량, 주문날짜 등) 해당 컬럼들을 매핑할 수 없기 떄문이다.
	 * - 따라서 매핑 테이블에 대한 엔티티를 새로 생성하고 다대다을 매핑 테이블을 기준으로 일대다-다대일 관계로 변경해야 한다. 
	 */
	
	/**
	 * ex>6.23 - 개선된 다대다 (일래다 - 다대일로 변형) 를 저장
	 * @throws Exception
	 */
	@Test
	public void testManyToManyUpgrade1Save() throws Exception {
		// 회원 저장
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.Member member1 = 
				new com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		em.persist(member1);
		
		// 상품 저장
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.Product productA = 
				new com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.Product();
		productA.setId("productA");
		productA.setName("상품1");
		em.persist(productA);
		
		// 회원상품 저장
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.MemberProduct memberProduct = 
				new com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.MemberProduct();
		memberProduct.setMember(member1);		// 주문 회원 - 연관관계 설정
		memberProduct.setProduct(productA);		// 주문 상품 - 연관관계 설정
		memberProduct.setOrderAmount(2);		// 주문 수량
		em.persist(memberProduct);
	}
	
	/**
	 * ex>6.24 - 개선된 다대다 (일대다 - 다대일 변환) 을 조회
	 * @throws Exception
	 */
	@Test
	public void testManyToManyUpgrade1Find() throws Exception {
		// 기본키 값 생성
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.MemberProductId memberProductId = 
				new com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.MemberProductId();
		memberProductId.setMember("member1");
		memberProductId.setProduct("productA");
		
		// 식별자 클래스로 엔티티를 조회
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.MemberProduct memberProduct = 
				em.find(com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.MemberProduct.class, memberProductId);
		
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.Member member = memberProduct.getMember();
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade1.Product product = memberProduct.getProduct();
		
		System.out.println("member = " + member.getUsername());
		System.out.println("product = " + product.getName());
		System.out.println("orderAmount = " + memberProduct.getOrderAmount());
	}
	
	/**
	 * ex>6.27 - 추천하는 다대다 (새로운 기본키 생성전략) 저장
	 * @throws Exception
	 */
	@Test
	public void testManyToManyUpgrade2Save() throws Exception {
		// 회원 저장
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2.Member member1 = 
				new com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2.Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		em.persist(member1);
		
		// 상품 저장
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2.Product productA = 
				new com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2.Product();
		productA.setId("productA");
		productA.setName("상품1");
		em.persist(productA);
		
		// 주문 저장
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2.Order order = 
				new com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2.Order();
		order.setMember(member1);		// 주문 회원 - 연관관계 설정
		order.setProduct(productA);		// 주문 상품 - 연관관계 설정
		order.setOrderAmount(2);		// 주문 수량
		em.persist(order);
	}
	
	/**
	 * ex>6.28 - 추천하는 다대다 (새로운 기본키 생성전략) 조회
	 * @throws Exception
	 */
	@Test
	public void testManyToManyUpgrade2Find() throws Exception {
		Long orderId = 1L;
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2.Order order = 
				em.find(com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2.Order.class, orderId);
		
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2.Member member = order.getMember();
		com.libedi.myproject.jpatest_ch06.many_to_many.upgrade2.Product product = order.getProduct();
		
		System.out.println("member = " + member.getUsername());
		System.out.println("product = " + product.getName());
		System.out.println("orderAmount = " + order.getOrderAmount());
	}
}
