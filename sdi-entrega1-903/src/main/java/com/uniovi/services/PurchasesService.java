package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;
import com.uniovi.repositories.PurchasesRepository;

@Service
public class PurchasesService {
	@Autowired
	private PurchasesRepository purchaseRepository;

	public List<Purchase> getPurchases() {
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchaseRepository.findAll().forEach(purchases::add);
		return purchases;
	}

	public List<Purchase> getUserPurchases(User buyer) {
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchaseRepository.findAllByBuyer(buyer).forEach(purchases::add);
		return purchases;
	}

	public void addPurchase(Purchase purchase) {
		purchaseRepository.save(purchase);
	}

	public void deletePurchase(Long id) {
		purchaseRepository.deleteById(id);
	}
}
