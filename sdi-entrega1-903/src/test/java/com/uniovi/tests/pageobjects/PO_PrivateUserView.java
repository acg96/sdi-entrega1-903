package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateUserView extends PO_PrivateView {

	public static final int ULTIMA_POSICION = -1000;

	/**
	 * Comprobar que el menú está como debe para un usuario estándar
	 * 
	 * @param driver
	 */
	public static void checkMenu(WebDriver driver) {
		checkMenuBeingInLoggedUser(driver);
	}

	/**
	 * Ir a una opción de menú
	 * 
	 * @param driver
	 * @param nameOption con el String con el nombre de la opción de menú
	 */
	public static void clickOpcionMenu(WebDriver driver, String nameOption) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnOffersManagement", getTimeout());
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "offersDropdownMenuButton", getTimeout());
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", nameOption, getTimeout());
		elementos.get(0).click();
	}
	
	
	/**
	 * Comprueba al cambiar el idioma en añadir oferta
	 * @param driver
	 */
	public static void checkChangeIdiomAddOffer(WebDriver driver) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offer.add.title", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offer.add.title.title", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offer.add.title.details", PO_Properties.SPANISH), getTimeout());
		changeIdiom(driver, "btnEnglish");
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offer.add.title", PO_Properties.ENGLISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offer.add.title.title", PO_Properties.ENGLISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offer.add.title.details", PO_Properties.ENGLISH), getTimeout());
		changeIdiom(driver, "btnSpanish");
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offer.add.title", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offer.add.title.title", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offer.add.title.details", PO_Properties.SPANISH), getTimeout());
	}
	
	/**
	 * Comprueba al cambiar el idioma en el listado de opciones de menú del usuario
	 * @param driver
	 */
	public static void checkChangeIdiomUserMenu(WebDriver driver) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnOffersManagement", getTimeout());
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.addOffer", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.showOffers", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.searchOffers", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.boughtOffers", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.offersManagement", PO_Properties.SPANISH), getTimeout());
		changeIdiom(driver, "btnEnglish");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnOffersManagement", getTimeout());
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.addOffer", PO_Properties.ENGLISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.showOffers", PO_Properties.ENGLISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.searchOffers", PO_Properties.ENGLISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.boughtOffers", PO_Properties.ENGLISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.offersManagement", PO_Properties.ENGLISH), getTimeout());
		changeIdiom(driver, "btnSpanish");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnOffersManagement", getTimeout());
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.addOffer", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.showOffers", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.searchOffers", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.boughtOffers", PO_Properties.SPANISH), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.menu.offersManagement", PO_Properties.SPANISH), getTimeout());
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnOffersManagement", getTimeout());
		elementos.get(0).click();
	}


	/**
	 * Rellenar el formulario de alta de un producto
	 * 
	 * @param driver
	 * @param stitle
	 * @param sdetails
	 * @param sdate
	 * @param sprice
	 */
	static public void fillForm(WebDriver driver, String stitle, String sdetails, String sdate, String sprice) {
		WebElement title = driver.findElement(By.name("title"));
		title.click();
		title.clear();
		title.sendKeys(stitle);
		WebElement details = driver.findElement(By.name("details"));
		details.click();
		details.clear();
		details.sendKeys(sdetails);
		WebElement date = driver.findElement(By.name("date"));
		date.click();
		date.clear();
		date.sendKeys(sdate);
		WebElement price = driver.findElement(By.name("price"));
		price.click();
		price.clear();
		price.sendKeys(sprice);

		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	/**
	 * Borra productos por las posiciones dadas y comprueba que después no estén
	 * 
	 * @param driver
	 * @param pos    con la posición a borrar
	 */
	public static void borrarProductosPorPosicion(WebDriver driver, int pos) {
		List<WebElement> list = SeleniumUtils.EsperaCargaPagina(driver, "class", "ownOffersListId", getTimeout());
		assertTrue("No hay productos", !list.isEmpty());
		assertTrue("La posición " + pos + " indicada no existe",
				pos < list.size() && (pos >= 0 || pos == ULTIMA_POSICION));
		if (pos == ULTIMA_POSICION)
			pos = list.size() - 1;
		String idProductoSeleccionado = list.get(pos).getAttribute("id");
		list.get(pos).click();
		list = SeleniumUtils.EsperaCargaPagina(driver, "class", "ownOffersListId", getTimeout());
		for (WebElement w : list) {
			assertTrue("El producto eliminado sigue estando", !w.getAttribute("id").equals(idProductoSeleccionado));
		}
	}

	/**
	 * Buscar productos en lista
	 * 
	 * @param driver
	 * @param textoBusqueda con lo que se quiere buscar
	 * @param               int con el número de productos encontrados en la primera
	 *                      página, coincidirá con el total si son 5 o menos
	 *                      productos el total
	 */
	public static int buscarProductos(WebDriver driver, String textoBusqueda) {
		WebElement email = driver.findElement(By.name("searchText"));
		email.click();
		email.clear();
		email.sendKeys(textoBusqueda);
		By boton = By.className("btn");
		driver.findElement(boton).click();
		int resultados = 0;
		try {
			List<WebElement> list = SeleniumUtils.EsperaCargaPagina(driver, "class", "searchOffersList", getTimeout());
			resultados = list.size();
		} catch (TimeoutException ex) {
			// No hacer nada, ya que es que no hay productos
			// y se carga antes en resultados el valor 0
		}
		return resultados;
	}

	/**
	 * Ver productos comprados
	 * 
	 * @param driver
	 * @return int el numero de productos
	 */
	public static int buscarCompras(WebDriver driver) {
		int resultados = 0;
		try {
			List<WebElement> list = SeleniumUtils.EsperaCargaPagina(driver, "class", "purchasesUserList", getTimeout());
			resultados = list.size();
		} catch (TimeoutException ex) {
			// No hacer nada, ya que es que no hay productos
			// y se carga antes en resultados el valor 0
		}
		return resultados;
	}

	/**
	 * Buscar productos en lista que se puedan comprar
	 * 
	 * @param driver
	 * @param textoBusqueda con lo que se quiere buscar
	 * @param               int con el número de productos encontrados en la primera
	 *                      página, coincidirá con el total si son 5 o menos
	 *                      productos el total
	 * @return List<WebElement> elementos que se pueden comprar
	 */
	public static List<WebElement> buscarProductosAComprar(WebDriver driver, String textoBusqueda) {
		WebElement email = driver.findElement(By.name("searchText"));
		email.click();
		email.clear();
		email.sendKeys(textoBusqueda);
		By boton = By.className("btn");
		driver.findElement(boton).click();
		try {
			List<WebElement> list = SeleniumUtils.EsperaCargaPagina(driver, "class", "bListToBuy", getTimeout());
			return list;
		} catch (TimeoutException ex) {
			return null;
		}
	}
}