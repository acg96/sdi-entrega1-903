package com.uniovi.services;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.auxiliar.UserToDelete;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	public static int DELETED_FALSE = 0;
	public static int DELETED_TRUE = 1;
	
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		if (user.getPassword() != null) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		usersRepository.save(user);
	}
	
	public void updateUser(User user) {
		usersRepository.save(user);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
	
	public int deleteUsers(UserToDelete users) {
		int hayBorrado = DELETED_FALSE;
		for (User u : users.getUsers()) {
			try {
				deleteUser(u.getId());
			}catch(Exception ex) { //Excepción por detached
				usersRepository.findById(u.getId()); //Se quita de detached
				deleteUser(u.getId()); //Se vuelve a borrar
			}	
			hayBorrado = DELETED_TRUE;
		}
		return hayBorrado;
	}
}