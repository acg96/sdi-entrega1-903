package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationsRepository;

@Service
public class ConversationsService {
	@Autowired
	private ConversationsRepository conversationsRepository;
	
	public List<Conversation> getBuyerConversations(User buyer) {
		List<Conversation> conversations = new ArrayList<Conversation>();
		conversationsRepository.findAllByBuyer(buyer).forEach(conversations::add);
		return conversations;
	}
	
	public Conversation getConversationByBuyerAndOffer(User buyer, Offer offer) {
		List<Conversation> conversations= new ArrayList<Conversation>();
		conversationsRepository.findConversationByBuyerAndOffer(buyer, offer).forEach(conversations::add);
		if (conversations != null && conversations.size() == 1) {
			return conversations.get(0);
		}
		return null;
	}
	
	public List<Conversation> getOfferConversations(Offer offer) {
		List<Conversation> conversations = new ArrayList<Conversation>();
		conversationsRepository.findAllByOffer(offer).forEach(conversations::add);
		return conversations;
	}
	
	public Conversation getConversation(Long id) {
		return conversationsRepository.findById(id).get();
	}

	public void addConversation(Conversation conversation) {
		conversationsRepository.save(conversation);
	}

	public void deleteConversation(Long id) {
		conversationsRepository.deleteById(id);
	}
}
