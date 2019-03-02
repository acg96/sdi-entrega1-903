package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateUserView extends PO_PrivateView {
	
	public static final int ULTIMA_POSICION= -1000;

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
		assertTrue("La posición " + pos + " indicada no existe", pos < list.size() && (pos >= 0 || pos == ULTIMA_POSICION));
		if (pos == ULTIMA_POSICION) pos= list.size()-1;
		String idProductoSeleccionado= list.get(pos).getAttribute("id");
		list.get(pos).click();
		list = SeleniumUtils.EsperaCargaPagina(driver, "class", "ownOffersListId", getTimeout());
		for (WebElement w : list) {
			assertTrue("El producto eliminado sigue estando", !w.getAttribute("id").equals(idProductoSeleccionado));
		}
	}
}