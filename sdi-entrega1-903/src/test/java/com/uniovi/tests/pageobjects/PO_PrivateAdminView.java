package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

public class PO_PrivateAdminView extends PO_PrivateView {
	
	/**
	 * Comprobar que el menú está como debe para un usuario administrador
	 * @param driver
	 */
	public static void checkMenu(WebDriver driver) {
		checkMenuBeingInLoggedAdmin(driver);
	}
	
}