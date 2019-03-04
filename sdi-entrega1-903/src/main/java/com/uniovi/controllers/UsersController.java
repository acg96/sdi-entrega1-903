package com.uniovi.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.uniovi.auxiliar.UserToDelete;
import com.uniovi.entities.*;
import com.uniovi.services.OffersService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private OffersService offersService;
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	@Autowired
	private RolesService rolesService;
	
	private void storeUserInformation(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= usersService.getUserByEmail(auth.getName());
		if (user != null) {
			model.addAttribute("email", user.getEmail());
			model.addAttribute("money", user.getMoney());
		}
	}

	@RequestMapping("/user/list/{hayBorrado}")
	public String getListado(Model model, @PathVariable Long hayBorrado) {
		this.storeUserInformation(model);
		model.addAttribute("deleted", hayBorrado);
		model.addAttribute("usersList", usersService.getUsers());
		model.addAttribute("userToDelete", new UserToDelete()); // utilizado para almacenar los que se van a borrar
		return "user/list";
	}

	@RequestMapping("/user/remove")
	public String delete(Model model, @ModelAttribute("userToDelete") UserToDelete users) {
		this.storeUserInformation(model);
		int hayBorrado= usersService.deleteUsers(users);
		return "redirect:/user/list/" + hayBorrado;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		if (securityService.authenticationCorrect(usersService)) {
			return "redirect:home";
		}
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[1]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {		
		if (securityService.authenticationCorrect(usersService)) {
			return "redirect:home";
		} else {
			return "login";
		}		
	}
	
	@RequestMapping(value = "/login/error", method = RequestMethod.GET)
	public String loginError(Model model, HttpServletRequest request) {
		if (securityService.authenticationCorrect(usersService)) {
			return "redirect:/home";
		}
		else {
			if (request.getHeader("referer") == null) {
				return "redirect:/login";
			}
			model.addAttribute("error", 1);
			return "login";
		}
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		this.storeUserInformation(model);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= usersService.getUserByEmail(auth.getName());
		List<Offer> list= offersService.getStarredOffers(user);
		model.addAttribute("offerList", list);
		return "home";
	}

}
