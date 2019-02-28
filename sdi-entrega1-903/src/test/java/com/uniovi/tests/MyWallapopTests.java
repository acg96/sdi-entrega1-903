package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.*;
import com.uniovi.tests.util.SeleniumUtils;

import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyWallapopTests {
	final static String URL_LOCAL= "http://localhost:8090";
	final static String URL_AMAZON= "";
	
	static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Selenium\\geckodriver024win64.exe";
	static WebDriver driver = getDriver(PathFirefox64, Geckdriver024);
	static String URL = URL_LOCAL; //Cambiar por URL_AMAZON

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	//Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	//Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	//Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	//Al finalizar la última prueba
	@AfterClass
	static public void end() {
		driver.quit(); //Cierra el navegador
	}
	
	


}