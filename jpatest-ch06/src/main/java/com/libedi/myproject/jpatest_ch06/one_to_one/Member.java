package com.libedi.myproject.jpatest_ch06.one_to_one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Member {
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private String id;
	
	private String username;
	
	@OneToOne
	@JoinColumn(name = "LOCKER_ID")
	private Locker locker;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Locker getLocker() {
		return locker;
	}
	public void setLocker(Locker locker) {
		this.locker = locker;
	}
}
