package com.uniovi.tests;


import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.openqa.selenium.WebDriver;
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
	static String URL = URL_LOCAL; // Cambiar por URL_AMAZON

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
		PO_LoginView.inicioDeSesionUser(driver);
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
		Integer[] posicionesABorrar= {1, 2, PO_PrivateAdminView.ULTIMO_LISTA};
		PO_PrivateAdminView.borrarUsuariosPorPosicion(driver, posicionesABorrar);
	}

}