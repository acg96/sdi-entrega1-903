package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.repositories.MessagesRepository;

@Service
public class MessagesService {
	@Autowired
	private MessagesRepository messagesRepository;
	
	public List<Message> getConversationMessages(Conversation conversation) {
		List<Message> messages = new ArrayList<Message>();
		messagesRepository.findAllByConversation(conversation).forEach(messages::add);
		return messages;
	}
	
	public Message getMessage(Long id) {
		return messagesRepository.findById(id).get();
	}

	public void addMessage(Message message) {
		messagesRepository.save(message);
	}
}
