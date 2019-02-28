package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_HomeView extends PO_NavView {
	/**
	 * Comprueba que se encuentre el mensaje de bienvenida
	 * @param driver
	 * @param language fichero de propiedades del idioma en el que se quiere comprobar
	 */
	public static void checkWelcome(WebDriver driver, int language) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}

	/**
	 * Comprueba al cambiar el idioma
	 * @param driver
	 * @param textIdiom1 Idioma inicial
	 * @param textIdiom2 Idioma final
	 * @param locale1 fichero de propiedades del idioma 1
	 * @param locale2 fichero de propiedades del idioma 2
	 */
	public static void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		PO_HomeView.checkWelcome(driver, locale1);
		PO_HomeView.changeIdiom(driver, textIdiom2);
		PO_HomeView.checkWelcome(driver, locale2);
		PO_HomeView.changeIdiom(driver, textIdiom1);
		PO_HomeView.checkWelcome(driver, locale1);
	}

	/**
	 * Comprueba que el menú esté como debe estar, en este caso el que se encuentra sin estar logueado
	 * @param driver
	 */
	public static void checkMenuStatus(WebDriver driver) {
		checkMenuNotBeingInLogged(driver);
	}
}
