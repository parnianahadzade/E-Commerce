package com.mftplus.ecommerce.api;

import com.mftplus.ecommerce.api.dto.RegistrationBody;
import com.mftplus.ecommerce.exception.EmailFailureException;
import com.mftplus.ecommerce.exception.UserAlreadyExistsException;
import com.mftplus.ecommerce.model.entity.*;
import com.mftplus.ecommerce.repository.*;
import com.mftplus.ecommerce.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final OrderQuantitiesRepository orderQuantitiesRepository;

    public Test(UserServiceImpl userService, UserRepository userRepository, ProductRepository productRepository, InventoryRepository inventoryRepository, AddressRepository addressRepository, OrderRepository orderRepository, OrderQuantitiesRepository orderQuantitiesRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.orderQuantitiesRepository = orderQuantitiesRepository;
    }


    @GetMapping("/data")
    public void testDate(){
        //user
        RegistrationBody user = RegistrationBody.builder()
                .username("test")
                .password("test123")
                .email("test@gmail.com")
                .firstName("test")
                .lastName("test")
                .build();
        try {
            userService.save(user);
        } catch (UserAlreadyExistsException e) {
            throw new RuntimeException(e);
        }catch (EmailFailureException e) {
            throw  new RuntimeException();
        }

        RegistrationBody user1 = RegistrationBody.builder()
                .username("testone")
                .password("test123")
                .email("testone@gmail.com")
                .firstName("test")
                .lastName("test")
                .build();
        try {
            userService.save(user1);
        } catch (UserAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (EmailFailureException e) {
            throw  new RuntimeException();
        }

        //product
        Product product1 = Product.builder()
                .name("Product #1")
                .shortDescription("Product one short description.")
                .longDescription("This is a very long description of product #1")
                .build();
        productRepository.save(product1);

        Product product2 = Product.builder()
                .name("Product #2")
                .shortDescription("Product two short description.")
                .longDescription("This is a very long description of product #2")
                .build();
        productRepository.save(product2);

        Product product3 = Product.builder()
                .name("Product #3")
                .shortDescription("Product three short description.")
                .longDescription("This is a very long description of product #3")
                .build();
        productRepository.save(product3);

        Product product4 = Product.builder()
                .name("Product #4")
                .shortDescription("Product four short description.")
                .longDescription("This is a very long description of product #4")
                .build();
        productRepository.save(product4);

        Product product5 = Product.builder()
                .name("Product #5")
                .shortDescription("Product five short description.")
                .longDescription("This is a very long description of product #5")
                .build();
        productRepository.save(product5);

        //inventory
        Inventory inventory1 = Inventory.builder().product(product1).quantity(1).build();
        inventoryRepository.save(inventory1);

        Inventory inventory2 = Inventory.builder().product(product2).quantity(2).build();
        inventoryRepository.save(inventory2);

        Inventory inventory3 = Inventory.builder().product(product3).quantity(3).build();
        inventoryRepository.save(inventory3);

        Inventory inventory4 = Inventory.builder().product(product4).quantity(4).build();
        inventoryRepository.save(inventory4);

        Inventory inventory5 = Inventory.builder().product(product5).quantity(5).build();
        inventoryRepository.save(inventory5);

        //address
        Address address1 = Address.builder()
                .addressLine1("123 Tester Hill")
                .city("Testerton")
                .country("England")
                .user(userRepository.findByUsernameIgnoreCaseAndDeletedFalse(user.getUsername()).get())
                .build();
        addressRepository.save(address1);

        Address address2 = Address.builder()
                .addressLine1("124 Tester Hill")
                .city("Hibernate")
                .country("England")
                .user(userRepository.findByUsernameIgnoreCaseAndDeletedFalse(user1.getUsername()).get())
                .build();
        addressRepository.save(address2);

        //order
        Order order1 = Order.builder().user(userRepository.findByUsernameIgnoreCaseAndDeletedFalse(user.getUsername()).get()).address(address1).build();
        orderRepository.save(order1);

        Order order2 = Order.builder().user(userRepository.findByUsernameIgnoreCaseAndDeletedFalse(user.getUsername()).get()).address(address1).build();
        orderRepository.save(order2);

        Order order3 = Order.builder().user(userRepository.findByUsernameIgnoreCaseAndDeletedFalse(user.getUsername()).get()).address(address1).build();
        orderRepository.save(order3);

        Order order4 = Order.builder().user(userRepository.findByUsernameIgnoreCaseAndDeletedFalse(user1.getUsername()).get()).address(address2).build();
        orderRepository.save(order4);

        Order order5 = Order.builder().user(userRepository.findByUsernameIgnoreCaseAndDeletedFalse(user1.getUsername()).get()).address(address2).build();
        orderRepository.save(order5);

        //order quantities
        OrderQuantities orderQuantities1 = OrderQuantities.builder().order(order1).product(product1).quantity(1).build();
        orderQuantitiesRepository.save(orderQuantities1);

        OrderQuantities orderQuantities2 = OrderQuantities.builder().order(order1).product(product2).quantity(1).build();
        orderQuantitiesRepository.save(orderQuantities2);

        OrderQuantities orderQuantities3 = OrderQuantities.builder().order(order2).product(product3).quantity(1).build();
        orderQuantitiesRepository.save(orderQuantities3);

        OrderQuantities orderQuantities4 = OrderQuantities.builder().order(order2).product(product2).quantity(1).build();
        orderQuantitiesRepository.save(orderQuantities4);

        OrderQuantities orderQuantities5 = OrderQuantities.builder().order(order2).product(product5).quantity(1).build();
        orderQuantitiesRepository.save(orderQuantities5);

        OrderQuantities orderQuantities6 = OrderQuantities.builder().order(order3).product(product3).quantity(1).build();
        orderQuantitiesRepository.save(orderQuantities6);

        OrderQuantities orderQuantities7 = OrderQuantities.builder().order(order4).product(product4).quantity(1).build();
        orderQuantitiesRepository.save(orderQuantities7);

        OrderQuantities orderQuantities8 = OrderQuantities.builder().order(order4).product(product2).quantity(1).build();
        orderQuantitiesRepository.save(orderQuantities8);

        OrderQuantities orderQuantities9 = OrderQuantities.builder().order(order5).product(product3).quantity(1).build();
        orderQuantitiesRepository.save(orderQuantities9);

        OrderQuantities orderQuantities10 = OrderQuantities.builder().order(order5).product(product1).quantity(1).build();
        orderQuantitiesRepository.save(orderQuantities10);
    }
}
