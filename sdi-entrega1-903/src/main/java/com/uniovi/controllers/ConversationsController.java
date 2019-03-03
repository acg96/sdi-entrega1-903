package com.uniovi.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.ConversationsService;
import com.uniovi.services.MessagesService;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

@Controller
public class ConversationsController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private MessagesService messagesService;

	@Autowired
	private ConversationsService conversationsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OffersService offersService;
	
	private void storeUserInformation(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= usersService.getUserByEmail(auth.getName());
		if (user != null) {
			model.addAttribute("email", user.getEmail());
			model.addAttribute("money", user.getMoney());
		}
	}
	
	@RequestMapping("/conversation/list")
	public String getList(Model model, Principal principal) {
		storeUserInformation(model);
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		List<Conversation> list= conversationsService.getAllUserConversations(user);
		model.addAttribute("conversationList", list);
		model.addAttribute("userAuth", user);
		return "conversation/list";
	}
	
	@RequestMapping("/conversation/remove/{id}")
	public String removeConversation(@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= usersService.getUserByEmail(auth.getName());
		Conversation conversation= null;
		try {
			conversation= conversationsService.getConversation(id);
		}catch(NoSuchElementException ex) {
			return "redirect:/conversation/list";
		}
		
		if (checkConversationOwner(user, conversation)) {
			conversationsService.deleteConversation(id);
		}
		return "redirect:/conversation/list";
	}
	
	private boolean checkConversationOwner(User user, Conversation conversation) {
		if (conversation.getBuyer().getEmail().equals(user.getEmail())) {
			return true;
		}
		if (conversation.getOffer().getUser().getEmail().equals(user.getEmail())) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/conversation/add/{id}", method = RequestMethod.POST)
	public String setMessage(Model model, @PathVariable Long id, @RequestParam(value = "", required = false) String newMessageField) {
		storeUserInformation(model);
		Integer carga= 0;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= usersService.getUserByEmail(auth.getName());
		Conversation conversation= null;
		try {
			conversation= conversationsService.getConversation(id);
		}catch(NoSuchElementException ex) {
		}
		if (conversation == null) {
			model.addAttribute("carga", carga);
			return "conversation/add";
		}
		if (!checkConversationOwner(user, conversation)) {
			model.addAttribute("carga", carga);
			return "conversation/add";
		}
		
		if (!newMessageField.trim().equals("")) {
			Message message= new Message(user.getFullName(), newMessageField, conversation);
			messagesService.addMessage(message);
		}
		carga= 1;
		List<Message> mensajes= conversation.getMessages();
		Collections.sort(mensajes);
		model.addAttribute("carga", carga);
		model.addAttribute("conversation", conversation);
		model.addAttribute("messageList", mensajes);
		return "conversation/add";
	}
	
	@RequestMapping(value = "/conversation/add/{id}")
	public String getConversation(Model model, @PathVariable Long id) {
		storeUserInformation(model);
		Integer carga= 0;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= usersService.getUserByEmail(auth.getName());
		Conversation conversation= null;
		try {
			conversation= conversationsService.getConversation(id);
		}catch(NoSuchElementException ex) {
		}
		if (conversation == null) {
			model.addAttribute("carga", carga);
			return "conversation/add";
		}
		if (!checkConversationOwner(user, conversation)) {
			model.addAttribute("carga", carga);
			return "conversation/add";
		}
		
		carga= 1;
		List<Message> mensajes= conversation.getMessages();
		Collections.sort(mensajes);
		model.addAttribute("carga", carga);
		model.addAttribute("conversation", conversation);
		model.addAttribute("messageList", mensajes);
		httpSession.setAttribute("conver", conversation.getId());		
		return "conversation/add";
	}
	
	@RequestMapping(value = "/conversation/update")
	public String updateConversation(Model model) {
		Long id= (Long)httpSession.getAttribute("conver");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= usersService.getUserByEmail(auth.getName());
		Conversation conversation= null;
		try {
			conversation= conversationsService.getConversation(id);
		}catch(NoSuchElementException ex) {
		}
		if (conversation == null) {
			return "conversation/add :: tableMessages";
		}
		if (!checkConversationOwner(user, conversation)) {
			return "conversation/add :: tableMessages";
		}
		
		List<Message> mensajes= conversation.getMessages();
		Collections.sort(mensajes);
		model.addAttribute("messageList", mensajes);
		return "conversation/add :: tableMessages";
	}
	
	@RequestMapping(value = "/conversation/start/{id}")
	public String startConversation(Model model, @PathVariable Long id) {
		storeUserInformation(model);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= usersService.getUserByEmail(auth.getName());
		Offer offer= null;
		
		Conversation conversation= null;
		try {
			offer= offersService.getOffer(id);
			conversation= conversationsService.getConversationByBuyerAndOffer(user, offer);
		}catch(NoSuchElementException ex) {
			//Si no existe la oferta
			if (offer == null) {
				return "redirect:/purchase/list";
			}
		}
		//Si la oferta ya está vendida
		if (offer.getPurchase() != null) {
			return "redirect:/purchase/list";
		}
		
		//Si el usuario intenta chatear consigo mismo
		if (offer.getUser().getEmail().equals(user.getEmail())) {
			return "redirect:/purchase/list";
		}
		
		if (conversation == null) { //Se crea una nueva conversacion
			conversation= new Conversation(user, offer);
			conversationsService.addConversation(conversation);
		}
		
		//Se redirige a la zona de envío de mensajes
		model.addAttribute("carga", 1);
		List<Message> mensajes= conversation.getMessages();
		Collections.sort(mensajes);
		model.addAttribute("conversation", conversation);
		model.addAttribute("messageList", mensajes);
		httpSession.setAttribute("conver", conversation.getId());
		return "conversation/add";
	}

}
