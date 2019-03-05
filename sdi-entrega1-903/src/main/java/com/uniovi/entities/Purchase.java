package com.uniovi.entities;

import javax.persistence.*;

@Entity
public class Purchase {
	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User buyer;

	public Purchase() {
	}

	public Purchase(User buyer, Offer offer) {
		if (buyer.getMoney() >= offer.getPrice()) {
			if (offer.getPurchase() == null && offer.getUser().getId() != buyer.getId()) {
				this.buyer = buyer;
				buyer.setMoney(Math.round((buyer.getMoney() - offer.getPrice()) * 100) / 100.0);
				this.offer = offer;
			}
		} else {
			throw new RuntimeException("No hay fondos");
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Purchase [id=" + id + "]";
	}
}
