package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
	private static final double DEFAULT_MONEY=100;
	
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	private Double money;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Offer> offers;
	
	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
	private Set<Purchase> purchases;
	
	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
	private Set<Conversation> conversations;

	private String password;

	@Transient
	private String passwordConfirm;

	public User(String email, String name, String lastName) {
		super();
		this.email= email;
		this.name = name;
		this.lastName = lastName;
		this.money= DEFAULT_MONEY;
		this.purchases= new HashSet<Purchase>();
		this.offers= new HashSet<Offer>();
	}
	
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Set<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public User() {
		this.money= DEFAULT_MONEY;
	}

	public long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Double getMoney() {
		return this.money;
	}
	
	public void setMoney(Double money) {
		this.money= Math.round(money * 100) / 100.0;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}
}