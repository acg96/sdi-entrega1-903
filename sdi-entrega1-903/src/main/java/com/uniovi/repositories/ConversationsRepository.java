package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public interface ConversationsRepository extends CrudRepository<Conversation, Long> {
	List<Conversation> findAllByBuyer(User buyer);
	List<Conversation> findAllByOffer(Offer offer);
}
