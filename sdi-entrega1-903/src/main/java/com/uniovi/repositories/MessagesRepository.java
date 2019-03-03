package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;

public interface MessagesRepository extends CrudRepository<Message, Long> {
	List<Message> findAllByConversation(Conversation conversation);

}
