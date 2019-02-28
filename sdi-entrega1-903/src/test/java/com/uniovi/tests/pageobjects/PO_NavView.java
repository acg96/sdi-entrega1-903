package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.*;
import com.uniovi.tests.util.SeleniumUtils;

public class PO_NavView extends PO_View {
	/**
	 * Clica una de las opciones principales (a href) y comprueba que se vaya a la
	 * vista con el elemento de tipo type con el texto Destino
	 * 
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param textOption: Texto de la opción principal.
	 * @param criterio: "id" or "class" or "text" or "@attribute" or "free". Si el
	 *        valor de criterio es free es una expresion xpath completa.
	 * @param textoDestino: texto correspondiente a la búsqueda de la página
	 *        destino.
	 */
	public static void clickOption(WebDriver driver, String textOption, String criterio, String textoDestino) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption, getTimeout());
		assertTrue(elementos.size() == 1);
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());
		assertTrue(elementos.size() == 1);
	}

	/**
	 * Selecciona el enlace de idioma correspondiente al texto textLanguage
	 * 
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param textLanguage: el texto que aparece en el enlace de idioma ("English" o
	 *        "Spanish")
	 */
	public static void changeIdiom(WebDriver driver, String textLanguage) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "languageDropdownMenuButton", getTimeout());
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", textLanguage, getTimeout());
		elementos.get(0).click();
	}
	
	static void checkMenuBeingInLogged(WebDriver driver) {
		checkLogOutAppear(driver);
		checkLoginNotAppear(driver);
		checkSignUpNotAppear(driver);
		checkEmailAppear(driver);		
	}
	
	/**
	 * Comprueba el estado del menú estando logueado como usuario normal
	 * @param driver
	 */
	public static void checkMenuBeingInLoggedUser(WebDriver driver) {
		checkMenuBeingInLogged(driver);	
		checkUsersMenuNotAppear(driver);
		checkOffersMenuAppear(driver);
		checkWalletAppear(driver);
	}
	
	/**
	 * Comprueba el estado del menú estando logueado como usuario administrador
	 * @param driver
	 */
	public static void checkMenuBeingInLoggedAdmin(WebDriver driver) {
		checkMenuBeingInLogged(driver);	
		checkUsersMenuAppear(driver);
		checkOffersMenuNotAppear(driver);
		checkWalletNotAppear(driver);
	}
	
	/**
	 * Comprueba el estado del menú no estando logueado
	 * @param driver
	 */
	public static void checkMenuNotBeingInLogged(WebDriver driver) {
		checkEmailNotAppear(driver);
		checkWalletNotAppear(driver);
		checkOffersMenuNotAppear(driver);
		checkUsersMenuNotAppear(driver);
		checkLanguageMenuAppear(driver);
		checkSignUpAppear(driver);
		checkLoginAppear(driver);
		checkLogOutNotAppear(driver);
	}
	
	static public void checkEmailNotAppear(WebDriver driver) {
		SeleniumUtils.idNoPresentePagina(driver, "navEmail");
	}

	static public void checkWalletNotAppear(WebDriver driver) {
		SeleniumUtils.idNoPresentePagina(driver, "navMoney");
	}

	static public void checkOffersMenuNotAppear(WebDriver driver) {
		SeleniumUtils.idNoPresentePagina(driver, "offers-menu");
	}
	
	static public void checkUsersMenuNotAppear(WebDriver driver) {
		SeleniumUtils.idNoPresentePagina(driver, "users-menu");
	}
	
	static public void checkLanguageMenuAppear(WebDriver driver) {
		SeleniumUtils.idPresentePagina(driver, "btnLanguage");
	}
	
	static public void checkSignUpAppear(WebDriver driver) {
		SeleniumUtils.idPresentePagina(driver, "navSignup");
	}
	
	static public void checkLoginAppear(WebDriver driver) {
		SeleniumUtils.idPresentePagina(driver, "navLogin");
	}
	
	static public void checkLogOutNotAppear(WebDriver driver) {
		SeleniumUtils.idNoPresentePagina(driver, "navLogout");
	}
	
	static public void checkLogOutAppear(WebDriver driver) {
		SeleniumUtils.idPresentePagina(driver, "navLogout");
	}
	
	static public void checkLoginNotAppear(WebDriver driver) {
		SeleniumUtils.idNoPresentePagina(driver, "navLogin");
	}
	
	static public void checkSignUpNotAppear(WebDriver driver) {
		SeleniumUtils.idNoPresentePagina(driver, "navSignup");
	}
	
	static public void checkOffersMenuAppear(WebDriver driver) {
		SeleniumUtils.idPresentePagina(driver, "offers-menu");
	}
	
	static public void checkUsersMenuAppear(WebDriver driver) {
		SeleniumUtils.idPresentePagina(driver, "users-menu");
	}
	
	static public void checkEmailAppear(WebDriver driver) {
		SeleniumUtils.idPresentePagina(driver, "navEmail");
	}

	static public void checkWalletAppear(WebDriver driver) {
		SeleniumUtils.idPresentePagina(driver, "navMoney");
	}
}