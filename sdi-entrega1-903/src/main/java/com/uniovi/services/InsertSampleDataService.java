package com.uniovi.services;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;

	@PostConstruct
	public void init() {
		User user1 = new User("prueba@gmail.com", "Daniel", "González");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[1]);
		User user2 = new User("prueba2@gmail.com", "Sara", "García");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[1]);
		User user3 = new User("prueba3@gmail.com", "Sofía", "Zamora");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[1]);
		User user4 = new User("prueba4@gmail.com", "Raul", "Rodríguez");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[1]);
		User user5 = new User("prueba5@gmail.com", "Lucía", "Méndez");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[1]);
		User user6 = new User("admin@email.com", "Andrés", "Casillas");
		user6.setPassword("admin");
		user6.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}
}