package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_LoginView extends PO_NavView {
	/**
	 * Rellenar el formulario de login
	 * 
	 * @param driver
	 * @param emailp con el email
	 * @param passwordp con la contraseña
	 */
	public static void fillForm(WebDriver driver, String emailp, String passwordp) {
		WebElement email = driver.findElement(By.name("username"));
		email.click();
		email.clear();
		email.sendKeys(emailp);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	/**
	 * Comprobar que sale el mensaje de login invalido además de que el menú siga el
	 * que debe es decir, no estando logueado
	 * 
	 * @param driver
	 */
	public static void checkInvalidLogin(WebDriver driver) {
		checkMenuNotBeingInLogged(driver);
		SeleniumUtils.idPresentePagina(driver, "loginError");
	}

	/**
	 * Comprueba que no se encuentre el mensaje de login incorrecto al acceder al
	 * login por primera vez
	 * 
	 * @param driver
	 */
	public static void checkFirstLoginAttempt(WebDriver driver) {
		checkMenuNotBeingInLogged(driver);
		SeleniumUtils.idNoPresentePagina(driver, "loginError");
	}
}
