package com.uniovi.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Offer {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String details;
	private String date;
	private Double price;
	private Boolean star;
	@Transient
	private Date availableDate;

	@OneToOne(mappedBy = "offer", cascade = CascadeType.REMOVE)
	private Purchase purchase;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
	private Set<Conversation> conversations;

	public Offer() {
		this.setStar(false);
	}

	public Offer(String title, String details, String date, Double price, User user) {
		this.setTitle(title);
		this.setDetails(details);
		this.setDate(date);
		this.setPrice(price);
		this.setStar(false);
		this.setUserNewRecord(user);
	}

	public Offer(String title, String details, String date, Double price, User user, Boolean star) {
		this.setTitle(title);
		this.setDetails(details);
		this.setDate(date);
		this.setPrice(price);
		this.setStar(star);
		this.setUserNewRecord(user);
	}

	public void setStarLater() {
		if (user.getMoney() >= 20) {
			this.setStar(true);
			user.setMoney(user.getMoney() - 20);
		} else {
			throw new IllegalStateException("El usuario no tiene fondos");
		}
	}

	public void setUserNewRecord(User user) {
		if (star) { // Si está marcada como destacada se comprueba monedero
			if (user.getMoney() >= 20) {
				user.setMoney(user.getMoney() - 20);
			} else {
				throw new IllegalStateException("El usuario no tiene fondos");
			}
		}
		this.setUser(user);
	}

	public void setStar(Boolean star) {
		this.star = star;
	}

	public boolean getStar() {
		return this.star;
	}

	public Set<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}

	public String getDate() {
		return date;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public void setDate(String date) {
		this.date = date;
		try {
			String[] array = date.split("-");
			Calendar cld = Calendar.getInstance();
			cld.add(Calendar.HOUR, 0);
			cld.add(Calendar.MILLISECOND, 0);
			cld.add(Calendar.MINUTE, 0);
			cld.add(Calendar.SECOND, 0);
			cld.add(Calendar.DAY_OF_MONTH, Integer.valueOf(array[0]));
			cld.add(Calendar.MONTH, Integer.valueOf(array[1]) - 1);
			cld.add(Calendar.YEAR, Integer.valueOf(array[2]));
			this.availableDate = cld.getTime();
		} catch (Exception ex) {
			this.availableDate = null;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getAvailableDate() {
		return availableDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = Math.round(price * 100) / 100.0;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", details=" + details + ", availableDate=" + availableDate
				+ ", price=" + price + ", star=" + star + "]";
	}
}
