package com.uniovi.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddOfferFormValidator;

@Controller
public class OffersController {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private OffersService offersService;

	@Autowired
	private AddOfferFormValidator addOfferFormValidator;

	@Autowired
	private UsersService usersService;
	
	private void storeUserInformation(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= usersService.getUserByEmail(auth.getName());
		if (user != null) {
			model.addAttribute("email", user.getEmail());
			model.addAttribute("money", user.getMoney());
		}
	}
	
	@RequestMapping("/offer/remove/{id}")
	public String removeOffer(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/offer/list";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(@ModelAttribute Offer offer, BindingResult result, Model model) {
		storeUserInformation(model);		
		addOfferFormValidator.validate(offer, result);
		if (result.hasErrors()) {
			return "offer/add";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= usersService.getUserByEmail(auth.getName());
		offer.setUser(user);
		offersService.addOffer(offer);
		return "redirect:/offer/list";
	}
	
	@RequestMapping("/offer/list")
	public String getList(Model model, Principal principal) {
		storeUserInformation(model);
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		List<Offer> offers= offersService.getUserOffers(user);
		model.addAttribute("offerList", offers);
		return "offer/list";
	}
	
	@RequestMapping("/offer/search")
	public String getProductsToBuy(Model model, Pageable pageable, Principal principal, @RequestParam(value = "", required = false) String searchText) {
		storeUserInformation(model);
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchOffersByTitle(pageable, searchText, user);
		} else {
			offers = offersService.getAllOffers(pageable, user);
		}
		Object money= httpSession.getAttribute("money");
		if (money != null) {
			httpSession.setAttribute("money", null);
			model.addAttribute("nomoney", (Integer)money);
		}
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "offer/search";
	}

	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model) {
		storeUserInformation(model);
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		Date systemDate= new Date();
		model.addAttribute("systemDate", sdf.format(systemDate));
		model.addAttribute("offer", new Offer());
		return "offer/add";
	}

}
