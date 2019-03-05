package com.uniovi.services;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
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

	@PostConstruct
	public void init() {
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
		User user6 = new User("prueba6@gmail.com", "Josefa", "Méndez");
		user6.setPassword("123456");
		user6.setMoney(20.0);
		user6.setRole(rolesService.getRoles()[1]);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(userAdmin);
		usersService.addUser(user6);

		Offer offer1 = new Offer("Producto 1", "Hecho de madera", "26-02-2019", 2.40, user1);
		Offer offer2 = new Offer("Producto 2", "Hecho de metal", "15-01-2018", 0.90, user1);
		Offer offer3 = new Offer("Producto 3", "Hecho de hierro", "31-12-2017", 58.40, user1);
		Offer offer4 = new Offer("Producto 4", "Hecho de plastico", "15-01-2019", 14.59, user2);
		Offer offer5 = new Offer("Producto 5", "Hecho de papel", "18-01-2019", 37.10, user2);
		Offer offer6 = new Offer("Producto 6", "Hecho de cartón", "24-02-2019", 4.50, user2);
		Offer offer7 = new Offer("Producto 7", "Hecho de cristal", "02-05-2018", 60.0, user3);
		Offer offer8 = new Offer("Producto 8", "Hecho de cerámica", "26-02-2019", 13.90, user3);
		Offer offer9 = new Offer("Producto 9", "Hecho de barro", "21-02-2019", 12.43, user3);
		Offer offer10 = new Offer("Producto 10", "Hecho de aglomerado", "13-05-2019", 12.60, user4);
		Offer offer11 = new Offer("Producto 11", "Hecho de espuma", "10-10-2019", 4.50, user4);
		Offer offer12 = new Offer("Producto 12", "Hecho de poliuretano", "05-10-2017", 60.50, user4);
		Offer offer13 = new Offer("Producto 13", "Hecho de serrín", "02-12-2019", 10.86, user5);
		Offer offer14 = new Offer("Producto 14", "Hecho de vinilo", "19-12-2014", 4.53, user5);
		Offer offer15 = new Offer("Producto 15", "Hecho de corcho", "10-04-2020", 10.72, user5);
		Offer offer16 = new Offer("Producto 16", "Hecho de mensajes", "10-01-2021", 10.72, user3);
		Offer offer17 = new Offer("Producto 17", "Prueba de destacar con 20€", "10-01-2021", 10.72, user6);
		Offer offer18 = new Offer("Producto 18", "Prueba de destacar con menos de 20€", "10-01-2021", 10.72, user6);

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
		offersService.addOffer(offer16);
		offersService.addOffer(offer17);
		offersService.addOffer(offer18);

		Conversation conversation1 = new Conversation(user5, offer1);// 1
		Conversation conversation2 = new Conversation(user5, offer2);// 1
		Conversation conversation3 = new Conversation(user5, offer3);// 1
		Conversation conversation4 = new Conversation(user3, offer4);// 2
		Conversation conversation5 = new Conversation(user3, offer5);// 2
		Conversation conversation6 = new Conversation(user3, offer6);// 2
		Conversation conversation7 = new Conversation(user1, offer7);// 3
		Conversation conversation8 = new Conversation(user1, offer8);// 3
		Conversation conversation9 = new Conversation(user1, offer9);// 3
		Conversation conversation10 = new Conversation(user2, offer10);// 4
		Conversation conversation11 = new Conversation(user2, offer11);// 4
		Conversation conversation12 = new Conversation(user2, offer12);// 4
		Conversation conversation13 = new Conversation(user4, offer13);// 5
		Conversation conversation14 = new Conversation(user4, offer14);// 5
		Conversation conversation15 = new Conversation(user4, offer15);// 5

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

		Message message1 = new Message(user5.getFullName(), "Hola 51", conversation1);
		Message message2 = new Message(user1.getFullName(), "Buenas! 11", conversation1);
		Message message3 = new Message(user5.getFullName(), "¿Qué tal? 51", conversation1);
		Message message4 = new Message(user1.getFullName(), "Bien 11", conversation1);

		Message message5 = new Message(user5.getFullName(), "Hola 52", conversation2);
		Message message6 = new Message(user1.getFullName(), "Buenas! 12", conversation2);
		Message message7 = new Message(user5.getFullName(), "¿Qué tal? 52", conversation2);
		Message message8 = new Message(user1.getFullName(), "Bien 12", conversation2);

		Message message9 = new Message(user5.getFullName(), "Hola 53", conversation3);
		Message message10 = new Message(user1.getFullName(), "Buenas! 13", conversation3);
		Message message11 = new Message(user5.getFullName(), "¿Qué tal? 53", conversation3);
		Message message12 = new Message(user1.getFullName(), "Bien 13", conversation3);

		Message message13 = new Message(user3.getFullName(), "Hola 34", conversation4);
		Message message14 = new Message(user2.getFullName(), "Buenas! 24", conversation4);
		Message message15 = new Message(user3.getFullName(), "¿Qué tal? 34", conversation4);
		Message message16 = new Message(user2.getFullName(), "Bien 24", conversation4);

		Message message17 = new Message(user3.getFullName(), "Hola 35", conversation5);
		Message message18 = new Message(user2.getFullName(), "Buenas! 25", conversation5);
		Message message19 = new Message(user3.getFullName(), "¿Qué tal? 35", conversation5);
		Message message20 = new Message(user2.getFullName(), "Bien 25", conversation5);

		Message message21 = new Message(user3.getFullName(), "Hola 36", conversation6);
		Message message22 = new Message(user2.getFullName(), "Buenas! 26", conversation6);
		Message message23 = new Message(user3.getFullName(), "¿Qué tal? 36", conversation6);
		Message message24 = new Message(user2.getFullName(), "Bien 26", conversation6);

		Message message25 = new Message(user1.getFullName(), "Hola 17", conversation7);
		Message message26 = new Message(user3.getFullName(), "Buenas! 37", conversation7);
		Message message27 = new Message(user1.getFullName(), "¿Qué tal? 17", conversation7);
		Message message28 = new Message(user3.getFullName(), "Bien 37", conversation7);

		Message message29 = new Message(user1.getFullName(), "Hola 18", conversation8);
		Message message30 = new Message(user3.getFullName(), "Buenas! 38", conversation8);
		Message message31 = new Message(user1.getFullName(), "¿Qué tal? 18", conversation8);
		Message message32 = new Message(user3.getFullName(), "Bien 38", conversation8);

		Message message33 = new Message(user1.getFullName(), "Hola 19", conversation9);
		Message message34 = new Message(user3.getFullName(), "Buenas! 39", conversation9);
		Message message35 = new Message(user1.getFullName(), "¿Qué tal? 19", conversation9);
		Message message36 = new Message(user3.getFullName(), "Bien 39", conversation9);

		Message message37 = new Message(user2.getFullName(), "Hola 210", conversation10);
		Message message38 = new Message(user4.getFullName(), "Buenas! 410", conversation10);
		Message message39 = new Message(user2.getFullName(), "¿Qué tal? 210", conversation10);
		Message message40 = new Message(user4.getFullName(), "Bien 410", conversation10);

		Message message41 = new Message(user2.getFullName(), "Hola 211", conversation11);
		Message message42 = new Message(user4.getFullName(), "Buenas! 411", conversation11);
		Message message43 = new Message(user2.getFullName(), "¿Qué tal? 211", conversation11);
		Message message44 = new Message(user4.getFullName(), "Bien 411", conversation11);

		Message message45 = new Message(user2.getFullName(), "Hola 212", conversation12);
		Message message46 = new Message(user4.getFullName(), "Buenas! 412", conversation12);
		Message message47 = new Message(user2.getFullName(), "¿Qué tal? 212", conversation12);
		Message message48 = new Message(user4.getFullName(), "Bien 412", conversation12);

		Message message49 = new Message(user4.getFullName(), "Hola 413", conversation13);
		Message message50 = new Message(user5.getFullName(), "Buenas! 513", conversation13);
		Message message51 = new Message(user4.getFullName(), "¿Qué tal? 413", conversation13);
		Message message52 = new Message(user5.getFullName(), "Bien 513", conversation13);

		Message message53 = new Message(user4.getFullName(), "Hola 414", conversation14);
		Message message54 = new Message(user5.getFullName(), "Buenas! 514", conversation14);
		Message message55 = new Message(user4.getFullName(), "¿Qué tal? 414", conversation14);
		Message message56 = new Message(user5.getFullName(), "Bien 514", conversation14);

		Message message57 = new Message(user4.getFullName(), "Hola 415", conversation15);
		Message message58 = new Message(user5.getFullName(), "Buenas! 515", conversation15);
		Message message59 = new Message(user4.getFullName(), "¿Qué tal? 415", conversation15);
		Message message60 = new Message(user5.getFullName(), "Bien 515", conversation15);

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

		Purchase purchase1 = new Purchase(user1, offer10);// 4
		Purchase purchase2 = new Purchase(user1, offer11);// 4
		Purchase purchase3 = new Purchase(user2, offer1);// 1
		Purchase purchase4 = new Purchase(user2, offer2);// 1
		Purchase purchase5 = new Purchase(user3, offer14);// 5
		Purchase purchase6 = new Purchase(user3, offer15);// 5
		Purchase purchase7 = new Purchase(user4, offer8);// 3
		Purchase purchase8 = new Purchase(user4, offer9);// 3
		Purchase purchase9 = new Purchase(user5, offer5);// 2
		Purchase purchase10 = new Purchase(user5, offer6);// 2

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

		// Para que se actualice el dinero del monedero
		usersService.updateUser(user1);
		usersService.updateUser(user2);
		usersService.updateUser(user3);
		usersService.updateUser(user4);
		usersService.updateUser(user5);
		usersService.updateUser(user6);
		usersService.updateUser(userAdmin);
	}
}