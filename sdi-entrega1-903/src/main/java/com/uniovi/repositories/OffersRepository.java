package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public interface OffersRepository extends CrudRepository<Offer, Long> {
	List<Offer> findAllByUser(User user);

}
