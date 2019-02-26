package com.uniovi.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.Offer;
import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.PurchasesService;
import com.uniovi.services.UsersService;

@Controller
public class PurchasesController {

	@Autowired
	private OffersService offersService;

	@Autowired
	private PurchasesService purchasesService;

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

	@RequestMapping(value = "/purchase/add/{id}")
	public String setPurchase(@PathVariable Long id, Model model) {
		Offer offer= offersService.getOffer(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User buyer= usersService.getUserByEmail(auth.getName());
		
		try {
			Purchase purchase= new Purchase(buyer, offer);	
			purchasesService.addPurchase(purchase);
		}catch(RuntimeException ex) {
			model.addAttribute("money", 1);
			return "redirect:/offer/list"; //Sería donde buscas no en esa, pero mientras
		}
		return "redirect:/purchase/list";
	}
	
	@RequestMapping("/purchase/list")
	public String getList(Model model, Principal principal) {
		storeUserInformation(model);
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		List<Purchase> purchases= purchasesService.getUserPurchases(user);
		model.addAttribute("purchaseList", purchases);
		return "purchase/list";
	}
}
