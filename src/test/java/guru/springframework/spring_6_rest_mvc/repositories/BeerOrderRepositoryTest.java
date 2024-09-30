package guru.springframework.spring_6_rest_mvc.repositories;


import guru.springframework.spring_6_rest_mvc.entities.Beer;
import guru.springframework.spring_6_rest_mvc.entities.BeerOrder;
import guru.springframework.spring_6_rest_mvc.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BeerOrderRepositoryTest {

    @Autowired
    BeerOrderRepository beerOrderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerRepository beerRepository;

    Customer testCustomer;
    Beer testBeer;

    @BeforeEach
    void setUp() {
        testCustomer=customerRepository.findAll().get(0);
        testBeer=beerRepository.findAll().get(0);
    }


    @Transactional
    @Test
    void testBeerOrders(){
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("Test order")
                .customer(testCustomer)
                .build();

        BeerOrder savedBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);
        System.out.println(savedBeerOrder.getCustomerRef());
    }




}