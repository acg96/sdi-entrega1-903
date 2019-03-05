package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public interface OffersRepository extends CrudRepository<Offer, Long> {
	List<Offer> findAllByUser(User user);

	@Query("SELECT o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?1)) and o.user!=?2")
	Page<Offer> searchOffersByTitle(Pageable pageable, String seachtext, User user);

	@Query("SELECT o FROM Offer o WHERE o.user!=?1")
	Page<Offer> searchAllOffers(Pageable pageable, User user);

	@Query("SELECT o FROM Offer o WHERE o.star='true' and o.user!=?1")
	List<Offer> searchStarredOffers(User user);
}
