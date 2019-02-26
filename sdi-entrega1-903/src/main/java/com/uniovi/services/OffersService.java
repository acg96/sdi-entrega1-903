package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;

@Service
public class OffersService {
	@Autowired
	private OffersRepository offersRepository;

	public List<Offer> getOffers() {
		List<Offer> offer = new ArrayList<Offer>();
		offersRepository.findAll().forEach(offer::add);
		return offer;
	}
	
	public List<Offer> getUserOffers(User user) {
		List<Offer> offer = new ArrayList<Offer>();
		offersRepository.findAllByUser(user).forEach(offer::add);
		return offer;
	}
	
	public Offer getOffer(Long id) {
		return offersRepository.findById(id).get();
	}

	public void addOffer(Offer offer) {
		offersRepository.save(offer);
	}

	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}
}
