package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationsRepository;
import com.uniovi.repositories.MessagesRepository;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.PurchasesRepository;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.ConversationsService;
import com.uniovi.services.MessagesService;
import com.uniovi.services.OffersService;
import com.uniovi.services.PurchasesService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.*;
import com.uniovi.tests.util.SeleniumUtils;

import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWallapopTests {
	final static String URL_LOCAL = "http://localhost:8080";
	final static String URL_AMAZON = "http://ec2-54-185-22-249.us-west-2.compute.amazonaws.com:8080";

	static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Selenium\\geckodriver024win64.exe";
	static WebDriver driver = getDriver(PathFirefox64, Geckdriver024);
	
	//*****************CAMBIAR AQUÍ POR OTRA URL*******************
	static String URL = URL_LOCAL; // Cambiar por URL_LOCAL
	//*****************CAMBIAR AQUÍ POR OTRA URL*******************

	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OffersService offersService;

	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private PurchasesService purchasesService;
	
	@Autowired
	private ConversationsService conversationsService;
	
	@Autowired
	private MessagesService messagesService;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PurchasesRepository purchasesRepository;
	
	@Autowired
	private OffersRepository offersRepository;
	
	@Autowired
	private MessagesRepository messagesRepository;
	
	@Autowired
	private ConversationsRepository conversationsRepository;

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		initDB();
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
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		driver.quit(); // Cierra el navegador
	}
	
	
	
	public void initDB() {
		messagesRepository.deleteAll();
		conversationsRepository.deleteAll();
		purchasesRepository.deleteAll();
		offersRepository.deleteAll();
		usersRepository.deleteAll();		
		
		User user1 = new User("prueba@gmail.com", "Daniel", "González");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[1]);
		User user2 = new User("prueba2@gmail.com", "Sara", "García");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[1]);
		User user3 = new User("prueba3@gmail.com", "Sofía", "Zamora");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[1]);
		User user4 = new User("prueba4@gmail.com", "Raul", "Rodríguez");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[1]);
		User user5 = new User("prueba5@gmail.com", "Lucía", "Méndez");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[1]);
		User userAdmin = new User("admin@email.com", "Andrés", "Casillas");
		userAdmin.setPassword("admin");
		userAdmin.setRole(rolesService.getRoles()[0]);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(userAdmin);
		
		Offer offer1= new Offer("Producto 1", "Hecho de madera", "26-02-2019", 2.40, user1);
		Offer offer2= new Offer("Producto 2", "Hecho de metal", "15-01-2018", 0.90, user1);
		Offer offer3= new Offer("Producto 3", "Hecho de hierro", "31-12-2017", 58.40, user1);
		Offer offer4= new Offer("Producto 4", "Hecho de plastico", "15-01-2019", 14.59, user2);
		Offer offer5= new Offer("Producto 5", "Hecho de papel", "18-01-2019", 37.10, user2);
		Offer offer6= new Offer("Producto 6", "Hecho de cartón", "24-02-2019", 4.50, user2);
		Offer offer7= new Offer("Producto 7", "Hecho de cristal", "02-05-2018", 60.0, user3);
		Offer offer8= new Offer("Producto 8", "Hecho de cerámica", "26-02-2019", 13.90, user3);
		Offer offer9= new Offer("Producto 9", "Hecho de barro", "21-02-2019", 12.43, user3);
		Offer offer10= new Offer("Producto 10", "Hecho de aglomerado", "13-05-2019", 12.60, user4);
		Offer offer11= new Offer("Producto 11", "Hecho de espuma", "10-10-2019", 4.50, user4);
		Offer offer12= new Offer("Producto 12", "Hecho de poliuretano", "05-10-2017", 60.50, user4);
		Offer offer13= new Offer("Producto 13", "Hecho de serrín", "02-12-2019", 10.86, user5);
		Offer offer14= new Offer("Producto 14", "Hecho de vinilo", "19-12-2014", 4.53, user5);
		Offer offer15= new Offer("Producto 15", "Hecho de corcho", "10-04-2020", 10.72, user5);
		
		offersService.addOffer(offer1);
		offersService.addOffer(offer2);
		offersService.addOffer(offer3);
		offersService.addOffer(offer4);
		offersService.addOffer(offer5);
		offersService.addOffer(offer6);
		offersService.addOffer(offer7);
		offersService.addOffer(offer8);
		offersService.addOffer(offer9);
		offersService.addOffer(offer10);
		offersService.addOffer(offer11);
		offersService.addOffer(offer12);
		offersService.addOffer(offer13);
		offersService.addOffer(offer14);
		offersService.addOffer(offer15);
		
		Conversation conversation1= new Conversation(user5, offer1);//1
		Conversation conversation2= new Conversation(user5, offer2);//1
		Conversation conversation3= new Conversation(user5, offer3);//1
		Conversation conversation4= new Conversation(user3, offer4);//2
		Conversation conversation5= new Conversation(user3, offer5);//2
		Conversation conversation6= new Conversation(user3, offer6);//2
		Conversation conversation7= new Conversation(user1, offer7);//3
		Conversation conversation8= new Conversation(user1, offer8);//3
		Conversation conversation9= new Conversation(user1, offer9);//3
		Conversation conversation10= new Conversation(user2, offer10);//4
		Conversation conversation11= new Conversation(user2, offer11);//4
		Conversation conversation12= new Conversation(user2, offer12);//4
		Conversation conversation13= new Conversation(user4, offer13);//5
		Conversation conversation14= new Conversation(user4, offer14);//5
		Conversation conversation15= new Conversation(user4, offer15);//5
		
		conversationsService.addConversation(conversation1);
		conversationsService.addConversation(conversation2);
		conversationsService.addConversation(conversation3);
		conversationsService.addConversation(conversation4);
		conversationsService.addConversation(conversation5);
		conversationsService.addConversation(conversation6);
		conversationsService.addConversation(conversation7);
		conversationsService.addConversation(conversation8);
		conversationsService.addConversation(conversation9);
		conversationsService.addConversation(conversation10);
		conversationsService.addConversation(conversation11);
		conversationsService.addConversation(conversation12);
		conversationsService.addConversation(conversation13);
		conversationsService.addConversation(conversation14);
		conversationsService.addConversation(conversation15);
		
		Message message1= new Message(user5.getFullName(), "Hola 51", conversation1);
		Message message2= new Message(user1.getFullName(), "Buenas! 11", conversation1);
		Message message3= new Message(user5.getFullName(), "¿Qué tal? 51", conversation1);
		Message message4= new Message(user1.getFullName(), "Bien 11", conversation1);
		
		Message message5= new Message(user5.getFullName(), "Hola 52", conversation2);
		Message message6= new Message(user1.getFullName(), "Buenas! 12", conversation2);
		Message message7= new Message(user5.getFullName(), "¿Qué tal? 52", conversation2);
		Message message8= new Message(user1.getFullName(), "Bien 12", conversation2);
		
		Message message9= new Message(user5.getFullName(), "Hola 53", conversation3);
		Message message10= new Message(user1.getFullName(), "Buenas! 13", conversation3);
		Message message11= new Message(user5.getFullName(), "¿Qué tal? 53", conversation3);
		Message message12= new Message(user1.getFullName(), "Bien 13", conversation3);
		
		Message message13= new Message(user3.getFullName(), "Hola 34", conversation4);
		Message message14= new Message(user2.getFullName(), "Buenas! 24", conversation4);
		Message message15= new Message(user3.getFullName(), "¿Qué tal? 34", conversation4);
		Message message16= new Message(user2.getFullName(), "Bien 24", conversation4);
		
		Message message17= new Message(user3.getFullName(), "Hola 35", conversation5);
		Message message18= new Message(user2.getFullName(), "Buenas! 25", conversation5);
		Message message19= new Message(user3.getFullName(), "¿Qué tal? 35", conversation5);
		Message message20= new Message(user2.getFullName(), "Bien 25", conversation5);
		
		Message message21= new Message(user3.getFullName(), "Hola 36", conversation6);
		Message message22= new Message(user2.getFullName(), "Buenas! 26", conversation6);
		Message message23= new Message(user3.getFullName(), "¿Qué tal? 36", conversation6);
		Message message24= new Message(user2.getFullName(), "Bien 26", conversation6);
		
		Message message25= new Message(user1.getFullName(), "Hola 17", conversation7);
		Message message26= new Message(user3.getFullName(), "Buenas! 37", conversation7);
		Message message27= new Message(user1.getFullName(), "¿Qué tal? 17", conversation7);
		Message message28= new Message(user3.getFullName(), "Bien 37", conversation7);
		
		Message message29= new Message(user1.getFullName(), "Hola 18", conversation8);
		Message message30= new Message(user3.getFullName(), "Buenas! 38", conversation8);
		Message message31= new Message(user1.getFullName(), "¿Qué tal? 18", conversation8);
		Message message32= new Message(user3.getFullName(), "Bien 38", conversation8);

		Message message33= new Message(user1.getFullName(), "Hola 19", conversation9);
		Message message34= new Message(user3.getFullName(), "Buenas! 39", conversation9);
		Message message35= new Message(user1.getFullName(), "¿Qué tal? 19", conversation9);
		Message message36= new Message(user3.getFullName(), "Bien 39", conversation9);
		
		Message message37= new Message(user2.getFullName(), "Hola 210", conversation10);
		Message message38= new Message(user4.getFullName(), "Buenas! 410", conversation10);
		Message message39= new Message(user2.getFullName(), "¿Qué tal? 210", conversation10);
		Message message40= new Message(user4.getFullName(), "Bien 410", conversation10);
		
		Message message41= new Message(user2.getFullName(), "Hola 211", conversation11);
		Message message42= new Message(user4.getFullName(), "Buenas! 411", conversation11);
		Message message43= new Message(user2.getFullName(), "¿Qué tal? 211", conversation11);
		Message message44= new Message(user4.getFullName(), "Bien 411", conversation11);
		
		Message message45= new Message(user2.getFullName(), "Hola 212", conversation12);
		Message message46= new Message(user4.getFullName(), "Buenas! 412", conversation12);
		Message message47= new Message(user2.getFullName(), "¿Qué tal? 212", conversation12);
		Message message48= new Message(user4.getFullName(), "Bien 412", conversation12);
		
		Message message49= new Message(user4.getFullName(), "Hola 413", conversation13);
		Message message50= new Message(user5.getFullName(), "Buenas! 513", conversation13);
		Message message51= new Message(user4.getFullName(), "¿Qué tal? 413", conversation13);
		Message message52= new Message(user5.getFullName(), "Bien 513", conversation13);
		
		Message message53= new Message(user4.getFullName(), "Hola 414", conversation14);
		Message message54= new Message(user5.getFullName(), "Buenas! 514", conversation14);
		Message message55= new Message(user4.getFullName(), "¿Qué tal? 414", conversation14);
		Message message56= new Message(user5.getFullName(), "Bien 514", conversation14);
		
		Message message57= new Message(user4.getFullName(), "Hola 415", conversation15);
		Message message58= new Message(user5.getFullName(), "Buenas! 515", conversation15);
		Message message59= new Message(user4.getFullName(), "¿Qué tal? 415", conversation15);
		Message message60= new Message(user5.getFullName(), "Bien 515", conversation15);
		
		messagesService.addMessage(message1);
		messagesService.addMessage(message2);
		messagesService.addMessage(message3);
		messagesService.addMessage(message4);
		messagesService.addMessage(message5);
		messagesService.addMessage(message6);
		messagesService.addMessage(message7);
		messagesService.addMessage(message8);
		messagesService.addMessage(message9);
		messagesService.addMessage(message10);
		messagesService.addMessage(message11);
		messagesService.addMessage(message12);
		messagesService.addMessage(message13);
		messagesService.addMessage(message14);
		messagesService.addMessage(message15);
		messagesService.addMessage(message16);
		messagesService.addMessage(message17);
		messagesService.addMessage(message18);
		messagesService.addMessage(message19);
		messagesService.addMessage(message20);
		messagesService.addMessage(message21);
		messagesService.addMessage(message22);
		messagesService.addMessage(message23);
		messagesService.addMessage(message24);
		messagesService.addMessage(message25);
		messagesService.addMessage(message26);
		messagesService.addMessage(message27);
		messagesService.addMessage(message28);
		messagesService.addMessage(message29);
		messagesService.addMessage(message30);
		messagesService.addMessage(message31);
		messagesService.addMessage(message32);
		messagesService.addMessage(message33);
		messagesService.addMessage(message34);
		messagesService.addMessage(message35);
		messagesService.addMessage(message36);
		messagesService.addMessage(message37);
		messagesService.addMessage(message38);
		messagesService.addMessage(message39);
		messagesService.addMessage(message40);
		messagesService.addMessage(message41);
		messagesService.addMessage(message42);
		messagesService.addMessage(message43);
		messagesService.addMessage(message44);
		messagesService.addMessage(message45);
		messagesService.addMessage(message46);
		messagesService.addMessage(message47);
		messagesService.addMessage(message48);
		messagesService.addMessage(message49);
		messagesService.addMessage(message50);
		messagesService.addMessage(message51);
		messagesService.addMessage(message52);
		messagesService.addMessage(message53);
		messagesService.addMessage(message54);
		messagesService.addMessage(message55);
		messagesService.addMessage(message56);
		messagesService.addMessage(message57);
		messagesService.addMessage(message58);
		messagesService.addMessage(message59);
		messagesService.addMessage(message60);
		
		Purchase purchase1= new Purchase(user1, offer10);//4
		Purchase purchase2= new Purchase(user1, offer11);//4
		Purchase purchase3= new Purchase(user2, offer1);//1
		Purchase purchase4= new Purchase(user2, offer2);//1
		Purchase purchase5= new Purchase(user3, offer14);//5
		Purchase purchase6= new Purchase(user3, offer15);//5
		Purchase purchase7= new Purchase(user4, offer8);//3
		Purchase purchase8= new Purchase(user4, offer9);//3
		Purchase purchase9= new Purchase(user5, offer5);//2
		Purchase purchase10= new Purchase(user5, offer6);//2
		
		purchasesService.addPurchase(purchase1);
		purchasesService.addPurchase(purchase2);
		purchasesService.addPurchase(purchase3);
		purchasesService.addPurchase(purchase4);
		purchasesService.addPurchase(purchase5);
		purchasesService.addPurchase(purchase6);
		purchasesService.addPurchase(purchase7);
		purchasesService.addPurchase(purchase8);
		purchasesService.addPurchase(purchase9);
		purchasesService.addPurchase(purchase10);
		
		//Para que se actualice el dinero del monedero
		usersService.updateUser(user1);
		usersService.updateUser(user2);
		usersService.updateUser(user3);
		usersService.updateUser(user4);
		usersService.updateUser(user5);
		usersService.updateUser(userAdmin);
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
		List<String> emails= new ArrayList<String>();
		emails.add("prueba@gmail.com");
		emails.add("prueba2@gmail.com");
		emails.add("prueba3@gmail.com");
		emails.add("prueba4@gmail.com");
		emails.add("prueba5@gmail.com");
		emails.add("admin@email.com");
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
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 4", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 5", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 6", PO_View.getTimeout());
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
		// Sólo deberían aparecer cinco ofertas en la primera página
		int totalEncontrado = PO_PrivateUserView.buscarProductos(driver, "");
		assertTrue("Se esperaban 5 productos en la primera página ha habido " + totalEncontrado, totalEncontrado == 5);
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 1", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 2", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 3", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 4", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 5", PO_View.getTimeout());
		driver.findElement(By.id("2pag")).click(); //Se va a página 2
		// Sólo deberían aparecer otras 5 en la segunda página
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 6", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 7", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 8", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 9", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 10", PO_View.getTimeout());
		driver.findElement(By.id("3pag")).click(); //Se va a página 3
		// Sólo deberían aparecer 2 ofertas en la última página
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 11", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 12", PO_View.getTimeout());
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
		List<WebElement> totalEncontrado = PO_PrivateUserView.buscarProductosAComprar(driver, "4");
		// Se comprueba que sale 1 producto
		assertTrue("Se esperaban 1 productos", totalEncontrado.size() == 1);
		// Se compra el producto
		totalEncontrado.get(0).click();
		// Se comprueba el saldo en el monedero
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Cartera: 43.81€", PO_View.getTimeout());
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
		List<WebElement> totalEncontrado = PO_PrivateUserView.buscarProductosAComprar(driver, "3");
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
		List<WebElement> totalEncontrado = PO_PrivateUserView.buscarProductosAComprar(driver, "7");
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
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 5", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaTieneTexto(driver, "Producto 6", PO_View.getTimeout());
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