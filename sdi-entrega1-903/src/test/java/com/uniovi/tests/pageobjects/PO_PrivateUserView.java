package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

public class PO_PrivateUserView extends PO_PrivateView {
	
	/**
	 * Comprobar que el menú está como debe para un usuario estándar
	 * @param driver
	 */
	public static void checkMenu(WebDriver driver) {
		checkMenuBeingInLoggedUser(driver);
	}
	
	
	
}