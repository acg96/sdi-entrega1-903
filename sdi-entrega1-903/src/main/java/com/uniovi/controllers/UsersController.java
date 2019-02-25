package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.uniovi.entities.*;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	private static int DELETED_FALSE = 0;
	private static int DELETED_TRUE = 1;

	@Autowired
	private UsersService usersService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	@Autowired
	private RolesService rolesService;

	@RequestMapping("/user/list/{hayBorrado}")
	public String getListado(Model model, @PathVariable Long hayBorrado) {
		model.addAttribute("deleted", hayBorrado);
		model.addAttribute("usersList", usersService.getUsers());
		model.addAttribute("userToDelete", new UserToDelete()); // utilizado para almacenar los que se van a borrar
		return "user/list";
	}

	@RequestMapping("/user/remove")
	public String delete(Model model, @ModelAttribute("userToDelete") UserToDelete users) {
		int hayBorrado = DELETED_FALSE;
		for (User u : users.getUsers()) {
			usersService.deleteUser(u.getId());
			hayBorrado = DELETED_TRUE;
		}
		return "redirect:/user/list/" + hayBorrado;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
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
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

}
