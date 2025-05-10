package backend.project;

import backend.project.dtos.*;
import backend.project.entities.*;
import backend.project.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
			AuthorityService authorityService,
			UserService userService,
			ClientService clientService,
			PaymentMethodService paymentMethodService,
			CartService CartService,
			OrderService orderService,
			CategoryService categoryService,
			ProductService productService,
			ReviewService reviewService,
			CartItemsService cartItemsService,
			OrderDetailService orderDetailService
	) {
		return args -> {


			//insert roles
			authorityService.insertAuthority(new Authority(0L,"ADMIN",null));
			authorityService.insertAuthority(new Authority(0L,"USER", null));
			authorityService.insertAuthority(new Authority(0L,"MARKETING", null));

			//insert user
			User user1 = userService.insertUser(new DTOUser(0L, "Admin123", "luis@gmail.com" ,"1234", true, "ADMIN"));
			User user2 = userService.insertUser(new DTOUser(0L,"user123","user1@gmail.com","user1231",true, "USER"));
			User user3 = userService.insertUser(new DTOUser(0L,"user122","user2@gmail.com","user123",true, "USER"));
			User user4 = userService.insertUser(new DTOUser(0L,"user123","user3@gmail.com","user123",true, "USER"));


			// Insertar clientes
			Client client1 = clientService.insertClient(new DTOCliente(0L, "Luis Alberto", "Sandoval Lopez", "987654321", "Av. Javier Prado Este 1234, Lima", user1.getId()));
			Client client2 = clientService.insertClient(new DTOCliente(0L, "Miguel Angel", "Santiago Lopez", "912345678", "Av. Pardo y Aliaga 5678, Lima", user2.getId()));
			Client client3 = clientService.insertClient(new DTOCliente(0L, "Ricardo Eduardo", "Sanchez Lopez", "998877665", "Av. Arequipa 4321, Lima", user3.getId()));
			Client client4 = clientService.insertClient(new DTOCliente(0L, "Juan Marcos", "Sanchez Lopez", "998877665", "Av. Arequipa 4321, Lima", user4.getId()));


			// Insertar métodos de pago
			PaymentMethod paymentMethod1 = paymentMethodService.insertPaymentMethod(new DTOPaymentMethod(0L, "Tarjeta de credito", "Pago con tarjeta de crédito", true));
			PaymentMethod paymentMethod2 = paymentMethodService.insertPaymentMethod(new DTOPaymentMethod(0L, "Tarjeta de debito", "Pago con tarjeta de débito", true));
			PaymentMethod paymentMethod3 = paymentMethodService.insertPaymentMethod(new DTOPaymentMethod(0L, "Transferencia bancaria", "Pago mediante transferencia bancaria", true));

			//insert order
			Order order1 = orderService.insertOrder(new DTOOrder(0L, "Pendiente",new BigDecimal(40.54), client1.getId(), paymentMethod1.getId()));
			Order order2 = orderService.insertOrder(new DTOOrder(0L, "En proceso",new BigDecimal(20.54), client2.getId(), paymentMethod2.getId()));

			//insert cart
			Cart cart1 = CartService.insertCart(new DTOCart(0L, "Pendiente", "Calle 1", client1.getId()));
			Cart cart2 = CartService.insertCart(new DTOCart(0L, "En proceso", "Calle 2", client2.getId()));

			// Insertar categorías de café
			Category category1 = categoryService.insertCategory(new DTOCategory(0L, "Caturra", "Café Arábica de Brasil, suave, con buena acidez y cuerpo ligero."));
			Category category2 = categoryService.insertCategory(new DTOCategory(0L, "Catimor", "Híbrido resistente a enfermedades, sabor ácido y ligero."));
			Category category3 = categoryService.insertCategory(new DTOCategory(0L, "Gesha", "Café de Etiopía, conocido por su complejidad, notas florales y frutales."));

			// Insertar productos de café
			Product product1 = productService.insertProduct(new DTOProduct(0L,
					"https://media.scoolinary.app/blog/images/2022/08/scoolinary-cafe-de-especialidad4.jpg",
					"Café Caturra Grano",
					"Café en grano de variedad Caturra, de sabor suave y acidez balanceada.",
					new BigDecimal(20.46), 100, "Peru", "Media", "250g", category1.getId()));

			Product product2 = productService.insertProduct(new DTOProduct(0L,
					"https://laroussecocina.mx/wp-content/uploads/2022/11/CafeMexa_1080_02.jpg",
					"Café Catimor Grano",
					"Café en grano de variedad Catimor, con un sabor más ácido y ligero.",
					new BigDecimal(18.30), 100, "Peru", "Alta", "250g", category2.getId()));

			Product product3 = productService.insertProduct(new DTOProduct(0L,
					"https://th.bing.com/th/id/OIP.FWLRf9SXwbxIyRZx-RBRgQHaHa?w=626&h=626&rs=1&pid=ImgDetMain",
					"Café Gesha Grano",
					"Café en grano de variedad Gesha, conocido por su perfil complejo y acidez brillante.",
					new BigDecimal(35.00), 100, "Peru", "Alta", "250g", category3.getId()));

			Product product4 = productService.insertProduct(new DTOProduct(0L,
					"https://cupofespresso.com/wp-content/uploads/2023/12/what-are-the-top-espresso-bean-varieties-2.png",
					"Café Caturra Molido",
					"Café molido de variedad Caturra, ideal para preparación en cafetera.",
					new BigDecimal(22.50), 100, "Peru", "Media", "250g", category1.getId()));

			//Product product5 = productService.insertProduct(new DTOProduct(0L, "Café Catimor Molido", "Café molido de variedad Catimor, con notas ácidas y ligeras.", new BigDecimal(20.00), 100, "Peru", "Alta", "250g", category2.getId()));
			//Product product6 = productService.insertProduct(new DTOProduct(0L, "Café Gesha Molido", "Café molido de variedad Gesha, con notas florales y frutales.", new BigDecimal(40.00), 100, "Peru", "Alta", "250g", category3.getId()));
			//Product product7 = productService.insertProduct(new DTOProduct(0L, "Café Caturra Orgánico", "Café orgánico en grano de variedad Caturra, con un sabor suave y aroma natural.", new BigDecimal(23.75), 100, "Peru", "Media", "250g", category1.getId()));
			//Product product8 = productService.insertProduct(new DTOProduct(0L, "Café Catimor Orgánico", "Café orgánico en grano de variedad Catimor, con un toque ácido y sabor fresco.", new BigDecimal(21.50), 100, "Peru", "Alta", "250g", category2.getId()));
			//Product product9 = productService.insertProduct(new DTOProduct(0L, "Café Gesha Orgánico", "Café orgánico en grano de variedad Gesha, con un perfil único de sabor y una acidez brillante.", new BigDecimal(45.00), 100, "Peru", "Alta", "250g", category3.getId()));
			//Product product10 = productService.insertProduct(new DTOProduct(0L, "Café Caturra Espresso", "Café en grano de variedad Caturra, ideal para preparar un espresso fuerte y suave.", new BigDecimal(25.00), 100, "Peru", "Alta", "250g", category1.getId()));

			// Insertar reseñas
			Review review1 = reviewService.insertReview(new DTOReview(0L, 4, "Excelente café con un sabor suave y agradable.", product1.getId(), client1.getId()));
			Review review2 = reviewService.insertReview(new DTOReview(0L, 5, "El mejor café que he probado, la acidez es perfecta.", product2.getId(), client2.getId()));
			Review review3 = reviewService.insertReview(new DTOReview(0L, 3, "Café bueno, pero esperaba un poco más de sabor y cuerpo.", product3.getId(), client3.getId()));
			Review review4 = reviewService.insertReview(new DTOReview(0L, 5, "Increíble perfil de sabor, definitivamente recomendaría este café.", product4.getId(), client1.getId()));
			//Review review5 = reviewService.insertReview(new DTOReview(0L, 2, "No me gustó mucho, me parece demasiado ácido para mi gusto.", product5.getId(), client2.getId()));

			//insert cartitem
			CartItems cartItems1 = cartItemsService.insertCartItems(new DTOCartItems(0L, 2, new BigDecimal(40.54),cart1.getId(), product1.getId()));
			CartItems cartItems2 = cartItemsService.insertCartItems(new DTOCartItems(0L, 2, new BigDecimal(20.54),cart2.getId(), product2.getId()));

			//insert orderdetail
			OrderDetail orderDetail1 = orderDetailService.insertOrderDetail(new DTOOrderDetail(0L, 2, new BigDecimal(40.54),order1.getId(), product1.getId()));
			OrderDetail orderDetail2 = orderDetailService.insertOrderDetail(new DTOOrderDetail(0L, 2, new BigDecimal(20.54),order2.getId(), product2.getId()));



		};
	}

}
