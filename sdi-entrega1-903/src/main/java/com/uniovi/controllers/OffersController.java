package com.uniovi.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
