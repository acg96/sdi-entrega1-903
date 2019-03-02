package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	
	/**
	 * Comprueba que se muestre el mensaje de área privada
	 * @param driver
	 */
	public static void checkHomePage(WebDriver driver) {
		SeleniumUtils.esperaCargaPaginaIdPresente(driver, "privateArea", getTimeout());
	}
	
	/**
	 * Comprueba al cambiar el idioma en el home
	 * @param driver
	 */
	public static void checkChangeIdiomHome(WebDriver driver) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("home.title", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("home.description", PO_Properties.SPANISH), getTimeout());
		changeIdiom(driver, "btnEnglish");
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", PO_Properties.ENGLISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("home.title", PO_Properties.ENGLISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("home.description", PO_Properties.ENGLISH), getTimeout());
		changeIdiom(driver, "btnSpanish");
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("home.title", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("home.description", PO_Properties.SPANISH), getTimeout());
	}
}