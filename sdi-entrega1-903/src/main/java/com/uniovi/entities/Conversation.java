package com.uniovi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"user_id", "offer_id"})
	)
@Entity
public class Conversation {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User buyer;
	
	@ManyToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;
	
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
	private List<Message> messages;


	public Conversation(User buyer, Offer offer, List<Message> messages) {
		super();
		this.buyer= buyer;
		this.offer= offer;
		this.messages= messages;
	}
	
	public Conversation(User buyer, Offer offer) {
		super();
		this.buyer= buyer;
		this.offer= offer;
		this.messages= new ArrayList<Message>();
	}

	public Conversation() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "Conversation [id=" + id + ", messages=" + messages + "]";
	}
	
}