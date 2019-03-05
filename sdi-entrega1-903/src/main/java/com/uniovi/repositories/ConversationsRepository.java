package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public interface ConversationsRepository extends CrudRepository<Conversation, Long> {
	List<Conversation> findAllByBuyer(User buyer);

	List<Conversation> findAllByOffer(Offer offer);

	@Query("SELECT c FROM Conversation c WHERE c.buyer=?1 and c.offer=?2")
	List<Conversation> findConversationByBuyerAndOffer(User buyer, Offer offer);

	@Query("SELECT c FROM Conversation c WHERE c.offer.user=?1 or c.buyer=?1")
	List<Conversation> findAllUserConversations(User user);
}
