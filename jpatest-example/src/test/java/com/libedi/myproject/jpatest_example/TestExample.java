package com.libedi.myproject.jpatest_example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.libedi.myproject.jpatest_example.model.entity.Item;
import com.libedi.myproject.jpatest_example.model.entity.Member;
import com.libedi.myproject.jpatest_example.model.entity.Order;
import com.libedi.myproject.jpatest_example.model.entity.OrderItem;

public class TestExample {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	
	@Before
	public void setUp() throws Exception{
		this.emf = Persistence.createEntityManagerFactory("jpabook");
		this.em = this.emf.createEntityManager();
		this.tx = this.em.getTransaction();
		this.tx.begin();
	}
	
	@After
	public void end() throws Exception{
		this.tx.commit();
		this.em.close();
		this.emf.close();
	}
	
	@Test
	public void testOrderMember() throws Exception{
		String orderId = "";
		Order order = em.find(Order.class, orderId);
		Member member = order.getMember();		// 주문한 회원, 참조 사용
		OrderItem orderItem = order.getOrderItem().get(0);	// 주문 상품
		Item item = orderItem.getItem();		// 상품
	}
}
