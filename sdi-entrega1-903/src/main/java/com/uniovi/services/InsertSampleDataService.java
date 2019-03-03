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
		User user7 = new User("prueba6@gmail.com", "Rodrigo", "Ruiz");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[1]);
		User user8 = new User("prueba7@gmail.com", "Belén", "García");
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[1]);
		User user6 = new User("admin@email.com", "Andrés", "Casillas");
		user6.setPassword("admin");
		user6.setRole(rolesService.getRoles()[0]);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		
		Offer offer1= new Offer("Producto 1", "Hecho de madera", "26-02-2019", 2.40, user1);
		Offer offer2= new Offer("Producto 2", "Hecho de metal", "15-01-2018", 0.90, user1);
		Offer offer3= new Offer("Producto 3", "Hecho de hierro", "31-12-2017", 49.90, user1);
		Offer offer4= new Offer("Producto 4", "Hecho de plastico", "15-01-2019", 14.59, user1);
		Offer offer5= new Offer("Producto 5", "Hecho de papel", "18-01-2019", 370.10, user1);
		Offer offer6= new Offer("Producto 6", "Hecho de cartón", "24-02-2019", 4.50, user1);
		Offer offer7= new Offer("Producto 7", "Hecho de cristal", "02-05-2018", 1200.0, user1);
		Offer offer8= new Offer("Producto 8", "Hecho de cerámica", "26-02-2019", 13.90, user1);
		Offer offer9= new Offer("Producto 9", "Hecho de barro", "21-02-2019", 12.43, user1);
		Offer offer10= new Offer("Producto 10", "Hecho de aglomerado", "13-05-2019", 12.60, user2);
		Offer offer11= new Offer("Producto 11", "Hecho de espuma", "10-10-2019", 4.50, user2);
		Offer offer12= new Offer("Producto 12", "Hecho de poliuretano", "05-10-2017", 95.50, user2);
		Offer offer13= new Offer("Producto 13", "Hecho de serrín", "02-12-2019", 100.86, user2);
		
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
		
		Conversation conversation1= new Conversation(user5, offer13);
		conversationsService.addConversation(conversation1);
		
		Message message1= new Message(user5.getFullName(), "Hola", conversation1);
		Message message2= new Message(user2.getFullName(), "Buenas!", conversation1);
		messagesService.addMessage(message1);
		messagesService.addMessage(message2);
		
		Purchase purchase1= new Purchase(user3, offer2);
		Purchase purchase2= new Purchase(user3, offer4);
		Purchase purchase3= new Purchase(user3, offer8);
		Purchase purchase4= new Purchase(user2, offer1);
		Purchase purchase5= new Purchase(user2, offer9);
		
		purchasesService.addPurchase(purchase1);
		purchasesService.addPurchase(purchase2);
		purchasesService.addPurchase(purchase3);
		purchasesService.addPurchase(purchase4);
		purchasesService.addPurchase(purchase5);
		
		//Para que se actualice el dinero del monedero
		usersService.updateUser(user1);
		usersService.updateUser(user2);
		usersService.updateUser(user3);
		usersService.updateUser(user4);
		usersService.updateUser(user5);
		usersService.updateUser(user6);
	}
}