package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
	final static String URL_LOCAL = "http://localhost:8090";
	final static String URL_AMAZON = "";

	static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Selenium\\geckodriver024win64.exe";
	static WebDriver driver = getDriver(PathFirefox64, Geckdriver024);
	
	//*****************CAMBIAR AQUÍ POR OTRA URL*******************
	static String URL = URL_LOCAL; // Cambiar por URL_AMAZON
	//*****************CAMBIAR AQUÍ POR OTRA URL*******************

	private static List<String> emails = new ArrayList<String>();

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
		emails.add("prueba@gmail.com");
		emails.add("prueba2@gmail.com");
		emails.add("prueba3@gmail.com");
		emails.add("prueba4@gmail.com");
		emails.add("prueba5@gmail.com");
		emails.add("prueba6@gmail.com");
		emails.add("prueba7@gmail.com");
		emails.add("admin@email.com");
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		driver.quit(); // Cierra el navegador
	}

	// PR01. Registro de usuario con datos válidos
	@Test
	public void PR01() {
		// Se comprueba que esté en la ventana principal
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
		// Se comprueba que esté el menú correcto
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se clica sobre la opción de menú y se comprueba que va donde debe
		PO_NavView.clickOption(driver, "/signup", "text",
				PO_NavView.getP().getString("signup.title", PO_Properties.getSPANISH()));
		// Se rellena el formulario con datos válidos
		PO_RegisterView.fillForm(driver, "pruebaSelenium@gmail.com", "Selenium", "Spring Boot", "123456", "123456");
		emails.add("pruebaSelenium@gmail.com");
		// Se comprueba que está logueado
		PO_NavView.checkMenuBeingInLoggedUser(driver);
		PO_PrivateView.checkHomePage(driver);
	}

	// PR02. Registro de usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos)
	@Test
	public void PR02() {
		// Se comprueba que esté en la ventana principal
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
		// Se comprueba que esté el menú correcto
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se clica sobre la opción de menú y se comprueba que va donde debe
		PO_NavView.clickOption(driver, "/signup", "text",
				PO_NavView.getP().getString("signup.title", PO_Properties.getSPANISH()));
		// Se rellena el formulario con datos inválidos
		PO_RegisterView.fillForm(driver, "", "", "", "123456", "123456");
		// Se comprueba que no está logueado y sigue en página de registro
		PO_NavView.checkMenuNotBeingInLogged(driver);
		PO_RegisterView.checkIsInSignUpView(driver, PO_Properties.getSPANISH());
	}

	// PR03. Registro de usuario con datos inválidos (repetición de contraseña
	// inválida)
	@Test
	public void PR03() {
		// Se comprueba que esté en la ventana principal
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
		// Se comprueba que esté el menú correcto
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se clica sobre la opción de menú y se comprueba que va donde debe
		PO_NavView.clickOption(driver, "/signup", "text",
				PO_NavView.getP().getString("signup.title", PO_Properties.getSPANISH()));
		// Se rellena el formulario con repetición de contraseña inválida
		PO_RegisterView.fillForm(driver, "pruebaSelenium2@gmail.com", "Selenium2", "Spring2 Boot2", "123456",
				"1234567");
		// Se comprueba que no está logueado y sigue en página de registro con el
		// mensaje de error de contraseña no igual
		PO_NavView.checkMenuNotBeingInLogged(driver);
		PO_RegisterView.checkInvalidSignIn(driver, "Error.signup.passwordConfirm.coincidence",
				PO_Properties.getSPANISH());
	}

	// PR04. Registro de usuario con datos inválidos (email existente)
	@Test
	public void PR04() {
		// Se comprueba que esté en la ventana principal
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
		// Se comprueba que esté el menú correcto
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se clica sobre la opción de menú y se comprueba que va donde debe
		PO_NavView.clickOption(driver, "/signup", "text",
				PO_NavView.getP().getString("signup.title", PO_Properties.getSPANISH()));
		// Se rellena el formulario con un email existente
		PO_RegisterView.fillForm(driver, "admin@email.com", "Selenium2", "Spring2 Boot2", "123456", "123456");
		// Se comprueba que no está logueado y sigue en página de registro con el
		// mensaje de error de email existente
		PO_NavView.checkMenuNotBeingInLogged(driver);
		PO_RegisterView.checkInvalidSignIn(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}

	// PR05. Inicio de sesión de administrador con datos válidos
	@Test
	public void PR05() {
		// Se comprueba que esté en la ventana principal
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
		// Se comprueba que esté el menú correcto
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se clica sobre la opción de menú y se comprueba que va donde debe
		PO_NavView.clickOption(driver, "/login", "text",
				PO_NavView.getP().getString("login.title", PO_Properties.getSPANISH()));
		// Se comprueba que no muestra mensaje de login incorrecto
		PO_LoginView.checkFirstLoginAttempt(driver);
		// Se rellena el formulario con datos válidos
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Se comprueba que está logueado
		PO_NavView.checkMenuBeingInLoggedAdmin(driver);
		PO_PrivateView.checkHomePage(driver);
	}

	// PR06. Inicio de sesión de usuario estándar con datos válidos
	@Test
	public void PR06() {
		// Se comprueba que esté en la ventana principal
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
		// Se comprueba que esté el menú correcto
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se clica sobre la opción de menú y se comprueba que va donde debe
		PO_NavView.clickOption(driver, "/login", "text",
				PO_NavView.getP().getString("login.title", PO_Properties.getSPANISH()));
		// Se comprueba que no muestra mensaje de login incorrecto
		PO_LoginView.checkFirstLoginAttempt(driver);
		// Se rellena el formulario con datos válidos
		PO_LoginView.fillForm(driver, "prueba@gmail.com", "123456");
		// Se comprueba que está logueado
		PO_NavView.checkMenuBeingInLoggedUser(driver);
		PO_PrivateView.checkHomePage(driver);
	}

	// PR07. Inicio de sesión inválido con ambos campos vacíos
	@Test
	public void PR07() {
		// Se comprueba que esté en la ventana principal
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
		// Se comprueba que esté el menú correcto
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se clica sobre la opción de menú y se comprueba que va donde debe
		PO_NavView.clickOption(driver, "/login", "text",
				PO_NavView.getP().getString("login.title", PO_Properties.getSPANISH()));
		// Se comprueba que no muestra mensaje de login incorrecto
		PO_LoginView.checkFirstLoginAttempt(driver);
		// Se rellena el formulario con campos vacíos
		PO_LoginView.fillForm(driver, "", "");
		// Se comprueba que no está logueado y sigue en la página de login
		PO_NavView.checkMenuNotBeingInLogged(driver);
		PO_LoginView.checkIsInLogInView(driver, PO_Properties.getSPANISH());
	}

	// PR08. Inicio de sesión inválido con email de usuario estándar existente y
	// contraseña incorrecta
	@Test
	public void PR08() {
		// Se comprueba que esté en la ventana principal
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
		// Se comprueba que esté el menú correcto
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se clica sobre la opción de menú y se comprueba que va donde debe
		PO_NavView.clickOption(driver, "/login", "text",
				PO_NavView.getP().getString("login.title", PO_Properties.getSPANISH()));
		// Se comprueba que no muestra mensaje de login incorrecto
		PO_LoginView.checkFirstLoginAttempt(driver);
		// Se rellena el formulario con email válido y contraseña incorrecta
		PO_LoginView.fillForm(driver, "prueba@gmail.com", "1234567");
		// Se comprueba que no está logueado y sigue en la página de login
		PO_LoginView.checkInvalidLogin(driver);
	}

	// PR09. Inicio de sesión inválido con email no existente
	@Test
	public void PR09() {
		// Se comprueba que esté en la ventana principal
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
		// Se comprueba que esté el menú correcto
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se clica sobre la opción de menú y se comprueba que va donde debe
		PO_NavView.clickOption(driver, "/login", "text",
				PO_NavView.getP().getString("login.title", PO_Properties.getSPANISH()));
		// Se comprueba que no muestra mensaje de login incorrecto
		PO_LoginView.checkFirstLoginAttempt(driver);
		// Se rellena el formulario con email no existente
		PO_LoginView.fillForm(driver, "noexiste@gmail.com", "1234567");
		// Se comprueba que no está logueado y sigue en la página de login
		PO_LoginView.checkInvalidLogin(driver);
	}

	// PR10. Hacer clic en la opción de salir de sesión y comprobar que se va a la
	// página de login
	@Test
	public void PR10() {
		// Primero se inicia sesión como usuario estándar
		PO_LoginView.inicioDeSesionUser2(driver);
		// Se clica sobre la opción de menú y se comprueba que va a la página de login
		PO_NavView.clickOption(driver, "/logout", "text",
				PO_NavView.getP().getString("login.title", PO_Properties.getSPANISH()));
		// Se comprueba que esté el menú que debe
		PO_NavView.checkMenuNotBeingInLogged(driver);
	}

	// PR11. Comprobar que el botón de logout no está disponible si el usuario no
	// está
	// autenticado
	@Test
	public void PR11() {
		// Se comprueba que no aparezca ya que aún no se está autenticado
		PO_NavView.checkLogOutNotAppear(driver);
	}

	// PR12. Mostrar el listado de usuarios y comprobar que se muestran todos los
	// que existen
	@Test
	public void PR12() {
		// Primero se inicia sesión como usuario admin
		PO_LoginView.inicioDeSesionAdmin(driver);
		// Se clica sobre la opción de menú y se comprueba que se carga
		PO_PrivateAdminView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.showUsers", PO_Properties.getSPANISH()));
		SeleniumUtils.esperaCargaPaginaIdPresente(driver, "userListId", PO_View.getTimeout());
		// Se comprueba que coincidan con los emails que debería haber
		PO_PrivateAdminView.checkUsersInList(driver, emails);
	}

	// PR13. Ir a la lista de usuarios y borrar el primero de la lista
	@Test
	public void PR13() {
		// Primero se inicia sesión como usuario admin
		PO_LoginView.inicioDeSesionAdmin(driver);
		// Se clica sobre la opción de menú y se comprueba que se carga
		PO_PrivateAdminView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.showUsers", PO_Properties.getSPANISH()));
		SeleniumUtils.esperaCargaPaginaIdPresente(driver, "userListId", PO_View.getTimeout());
		// Se solicitar borrar el primero y se comprueba que se haya borrado
		PO_PrivateAdminView.borrarUsuariosPorPosicion(driver, PO_PrivateAdminView.PRIMERO_LISTA);
	}

	// PR14. Ir a la lista de usuarios y borrar el último de la lista
	@Test
	public void PR14() {
		// Primero se inicia sesión como usuario admin
		PO_LoginView.inicioDeSesionAdmin(driver);
		// Se clica sobre la opción de menú y se comprueba que se carga
		PO_PrivateAdminView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.showUsers", PO_Properties.getSPANISH()));
		SeleniumUtils.esperaCargaPaginaIdPresente(driver, "userListId", PO_View.getTimeout());
		// Se solicitar borrar el ultimo y se comprueba que se haya borrado
		PO_PrivateAdminView.borrarUsuariosPorPosicion(driver, PO_PrivateAdminView.ULTIMO_LISTA);
	}

	// PR15. Ir a la lista de usuarios y borrar tres usuarios
	@Test
	public void PR15() {
		// Primero se inicia sesión como usuario admin
		PO_LoginView.inicioDeSesionAdmin(driver);
		// Se clica sobre la opción de menú y se comprueba que se carga
		PO_PrivateAdminView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.showUsers", PO_Properties.getSPANISH()));
		SeleniumUtils.esperaCargaPaginaIdPresente(driver, "userListId", PO_View.getTimeout());
		// Se solicitar borrar tres usuarios y se comprueba que se hayan borrado
		// Se solicitan borrar las posiciones 2, 3 y la última
		Integer[] posicionesABorrar = { 1, 2, PO_PrivateAdminView.ULTIMO_LISTA };
		PO_PrivateAdminView.borrarUsuariosPorPosicion(driver, posicionesABorrar);
	}

	// PR16. Alta de oferta con datos válidos y comprobación en listado del usuario
	@Test
	public void PR16() {
		// Primero se inicia sesión como usuario estándar
		PO_LoginView.inicioDeSesionUser2(driver);
		// Se clica sobre la opción de menú y se comprueba que se carga
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.addOffer", PO_Properties.getSPANISH()));
		SeleniumUtils.esperaCargaPaginaIdPresente(driver, "addOfferId", PO_View.getTimeout());
		// Se rellena con datos válidos y se crea
		PO_PrivateUserView.fillForm(driver, "Producto prueba", "Hecho con Selenium", "12-05-2016", "5.40");
		// Se comprueba que aparece en el listado de ofertas del usuario
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.showOffers", PO_Properties.getSPANISH()));
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto prueba", PO_View.getTimeout());
	}

	// PR17. Alta de oferta con datos inválidos (título vacío)
	@Test
	public void PR17() {
		// Primero se inicia sesión como usuario estándar
		PO_LoginView.inicioDeSesionUser2(driver);
		// Se clica sobre la opción de menú y se comprueba que se carga
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.addOffer", PO_Properties.getSPANISH()));
		SeleniumUtils.esperaCargaPaginaIdPresente(driver, "addOfferId", PO_View.getTimeout());
		// Se rellena con datos inválidos y se crea
		PO_PrivateUserView.fillForm(driver, " ", "Hecho con Selenium", "12-05-2016", "5.40");
		// Se comprueba que aparece el mensaje de campo vacío
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver,
				PO_View.getP().getString("Error.empty", PO_Properties.getSPANISH()), PO_View.getTimeout());
	}

	// PR18. Mostrar listado de ofertas del usuario y comprobar que están todas
	@Test
	public void PR18() {
		// Primero se inicia sesión como usuario estándar
		PO_LoginView.inicioDeSesionUser2(driver);
		// Se clica sobre la opción de menú
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.showOffers", PO_Properties.getSPANISH()));
		// Se comprueba cada una de las ofertas
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto prueba", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 10", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 11", PO_View.getTimeout());
	}

	// PR19. Ir a la lista de ofertas y borrar la primera de ellas
	@Test
	public void PR19() {
		// Primero se inicia sesión como usuario estándar
		PO_LoginView.inicioDeSesionUser2(driver);
		// Se clica sobre la opción de menú
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.showOffers", PO_Properties.getSPANISH()));
		// Se eliminar la primera oferta y se comprueba que no esté
		PO_PrivateUserView.borrarProductosPorPosicion(driver, 0);
	}

	// PR20. Ir a la lista de ofertas y borrar la ultima de ellas
	@Test
	public void PR20() {
		// Primero se inicia sesión como usuario estándar
		PO_LoginView.inicioDeSesionUser2(driver);
		// Se clica sobre la opción de menú
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.showOffers", PO_Properties.getSPANISH()));
		// Se eliminar la última oferta y se comprueba que no esté
		PO_PrivateUserView.borrarProductosPorPosicion(driver, PO_PrivateUserView.ULTIMA_POSICION);
	}

	// PR21. Hacer búsqueda con campo vacío y ver que se muestran las que deben
	@Test
	public void PR21() {
		// Primero se inicia sesión como usuario estándar (prueba5@gmail.com)
		PO_LoginView.inicioDeSesionUser5(driver);
		// Se clica sobre la opción de menú
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.searchOffers", PO_Properties.getSPANISH()));
		// Sólo deberían aparecer dos ofertas [Producto 11 y Producto 12]
		int totalEncontrado = PO_PrivateUserView.buscarProductos(driver, "");
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 11", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 12", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 13", PO_View.getTimeout());
		assertTrue("Se esperaban tres productos ha habido " + totalEncontrado, totalEncontrado == 3);
	}

	// PR22. Hacer búsqueda con una cadena que no exista y comprobar que no sale
	// nada
	@Test
	public void PR22() {
		// Primero se inicia sesión como usuario estándar (prueba5@gmail.com)
		PO_LoginView.inicioDeSesionUser5(driver);
		// Se clica sobre la opción de menú
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.searchOffers", PO_Properties.getSPANISH()));
		// Se realiza la búsqueda
		int totalEncontrado = PO_PrivateUserView.buscarProductos(driver, "f2344affs");
		// Se comprueba que no sale nada
		assertTrue("Se esperaban 0 productos", totalEncontrado == 0);
	}

	// PR23. Hacer búsqueda y comprar un producto que deje saldo positivo
	@Test
	public void PR23() {
		// Primero se inicia sesión como usuario estándar (prueba5@gmail.com)
		PO_LoginView.inicioDeSesionUser5(driver);
		// Se clica sobre la opción de menú
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.searchOffers", PO_Properties.getSPANISH()));
		// Se realiza la búsqueda
		List<WebElement> totalEncontrado = PO_PrivateUserView.buscarProductosAComprar(driver, "11");
		// Se comprueba que sale 1 producto
		assertTrue("Se esperaban 1 productos", totalEncontrado.size() == 1);
		// Se compra el producto
		totalEncontrado.get(0).click();
		// Se comprueba el saldo en el monedero
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Cartera: 95.5€", PO_View.getTimeout());
	}

	// PR24. Hacer búsqueda y comprar un producto que deje saldo 0
	@Test
	public void PR24() {
		// Primero se inicia sesión como usuario estándar (prueba5@gmail.com)
		PO_LoginView.inicioDeSesionUser5(driver);
		// Se clica sobre la opción de menú
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.searchOffers", PO_Properties.getSPANISH()));
		// Se realiza la búsqueda
		List<WebElement> totalEncontrado = PO_PrivateUserView.buscarProductosAComprar(driver, "12");
		// Se comprueba que sale 1 producto
		assertTrue("Se esperaban 1 productos", totalEncontrado.size() == 1);
		// Se compra el producto
		totalEncontrado.get(0).click();
		// Se comprueba el saldo en el monedero
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Cartera: 0.0€", PO_View.getTimeout());
	}

	// PR25. Hacer búsqueda y comprar un producto que tenga precio mayor al saldo
	// disponible
	@Test
	public void PR25() {
		// Primero se inicia sesión como usuario estándar (prueba5@gmail.com)
		PO_LoginView.inicioDeSesionUser5(driver);
		// Se clica sobre la opción de menú
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.searchOffers", PO_Properties.getSPANISH()));
		// Se realiza la búsqueda
		List<WebElement> totalEncontrado = PO_PrivateUserView.buscarProductosAComprar(driver, "13");
		// Se obtiene el valor del monedero antes
		String monederoAntes = driver.findElement(By.id("navMoney")).getText();
		// Se comprueba que sale 1 producto
		assertTrue("Se esperaban 1 productos", totalEncontrado.size() == 1);
		// Se compra el producto
		totalEncontrado.get(0).click();
		// Se comprueba el saldo en el monedero que debiera ser el mismo que antes
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, monederoAntes, PO_View.getTimeout());
		// Se comprueba que se ha indicado saldo insuficiente
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver,
				PO_View.getP().getString("offer.search.notmoneyenough", PO_Properties.getSPANISH()),
				PO_View.getTimeout());
	}

	// PR26. Ver listado de ofertas compradas y comprobar que están todas
	@Test
	public void PR26() {
		// Primero se inicia sesión como usuario estándar (prueba5@gmail.com)
		PO_LoginView.inicioDeSesionUser5(driver);
		// Se clica sobre la opción de menú
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.boughtOffers", PO_Properties.getSPANISH()));
		// Se obtiene el número de compras y se comprueba (2 deben ser)
		int totalCompras = PO_PrivateUserView.buscarCompras(driver);
		assertTrue("Deberían ser 2 compras", totalCompras == 2);
		// Se comprueba que se muestren
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 11", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 12", PO_View.getTimeout());
	}

	// PR27. Comprobar internacionalización en 4 páginas (Index, alta de oferta,
	// listado usuarios, home)
	@Test
	public void PR27() {
		// Se comprueba el index
		PO_HomeView.checkChangeIdiom(driver);

		// Se comprueba el home y el listado de opciones de menú del usuario
		PO_LoginView.inicioDeSesionUser2(driver);
		PO_PrivateView.checkChangeIdiomHome(driver);
		PO_PrivateUserView.checkChangeIdiomUserMenu(driver);

		// Se comprueba el alta de oferta
		PO_PrivateUserView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.addOffer", PO_Properties.getSPANISH()));
		PO_PrivateUserView.checkChangeIdiomAddOffer(driver);

		// Se comprueba el listado de usuarios de administrador
		PO_NavView.clickOption(driver, "/logout", "text",
				PO_NavView.getP().getString("login.title", PO_Properties.getSPANISH()));
		PO_LoginView.inicioDeSesionAdmin(driver);
		PO_PrivateAdminView.clickOpcionMenu(driver,
				PO_View.getP().getString("nav.menu.showUsers", PO_Properties.getSPANISH()));
		PO_PrivateAdminView.checkChangeIdiomAddOffer(driver);
	}

	// PR28. Intentar acceder al listado de usuarios sin estar autenticado
	@Test
	public void PR28() {
		// Se comprueba que el menú está en estado de no estar logueado
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se dirige a la página del listado de usuarios
		driver.navigate().to(URL+"/user/list/2");
		// Se comprueba que se está en la página de login
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver,
				PO_View.getP().getString("login.title", PO_Properties.getSPANISH()), PO_View.getTimeout());
	}

	// PR29. Intentar acceder al listado de ofertas propias del usuario sin estar
	// autenticado
	@Test
	public void PR29() {
		// Se comprueba que el menú está en estado de no estar logueado
		PO_NavView.checkMenuNotBeingInLogged(driver);
		// Se dirige a la página del listado de ofertas propias
		driver.navigate().to(URL+"/offer/list");
		// Se comprueba que se está en la página de login
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver,
				PO_View.getP().getString("login.title", PO_Properties.getSPANISH()), PO_View.getTimeout());
	}

	// PR30. Estando autenticado como usuario estándar intentar acceder al listado de usuarios
	@Test
	public void PR30() {
		//Se inicia sesión como usuario estándar
		PO_LoginView.inicioDeSesionUser5(driver);
		// Se comprueba que el menú está en estado de estar logueado como usuario estándar
		PO_NavView.checkMenuBeingInLoggedUser(driver);
		// Se dirige a la página del listado de ofertas propias
		driver.navigate().to(URL+"/user/list/2");
		// Se comprueba que sale el mensaje de acción prohibida
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver,
				PO_View.getP().getString("error.403.description", PO_Properties.getSPANISH()), PO_View.getTimeout());
	}

}