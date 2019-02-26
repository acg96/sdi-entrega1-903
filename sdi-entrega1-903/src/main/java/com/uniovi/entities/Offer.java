package com.uniovi.entities;

import java.util.Calendar;
import java.util.Date;

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
	@Transient
	private Date availableDate;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Offer() {
	}
	
	public Offer(String title, String details, String date, Double price) {
		this.setTitle(title);
		this.setDetails(details);
		this.setDate(date);
		this.setPrice(price);
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		try {
			String[] array= date.split("-");
			Calendar cld= Calendar.getInstance();
			cld.add(Calendar.HOUR, 0);
			cld.add(Calendar.MILLISECOND, 0);
			cld.add(Calendar.MINUTE, 0);
			cld.add(Calendar.SECOND, 0);
			cld.add(Calendar.DAY_OF_MONTH, Integer.valueOf(array[0]));
			cld.add(Calendar.MONTH, Integer.valueOf(array[1])-1);
			cld.add(Calendar.YEAR, Integer.valueOf(array[2]));
			this.availableDate= cld.getTime();
		}catch(Exception ex) {
			this.availableDate= null;
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
		this.price = price;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", details=" + details + ", availableDate=" + availableDate
				+ ", price=" + price + "]";
	}
}
