package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;

public interface PurchasesRepository extends CrudRepository<Purchase, Long> {
	List<Purchase> findAllByBuyer(User buyer);

}
