package com.uniovi.auxiliar;

import java.util.List;

import com.uniovi.entities.User;

public class UserToDelete {
	private Long id;
	private List<User> users;

	public UserToDelete() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
