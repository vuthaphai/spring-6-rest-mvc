package guru.springframework.spring_6_rest_mvc.controller;

import guru.springframework.spring_6_rest_mvc.entities.Customer;
import guru.springframework.spring_6_rest_mvc.model.CustomerDTO;
import guru.springframework.spring_6_rest_mvc.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIntegrationTest {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testGetCustomerByIdNotFound() {

        assertThrows(NotFoundException.class, ()->{
            customerController.getCustomerById(UUID.randomUUID());
        });



    }

    @Test
    void testGeCustomerById() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO dto = customerController.getCustomerById(customer.getId());
        assertThat(dto).isNotNull();
    }

    @Test
    void testListCustomers() {
        List<CustomerDTO> dtos = customerController.listAllCustomers();

        assertThat(dtos.size()).isEqualTo(3);

    }


    @Rollback
    @Transactional
    @Test
    void testListAllEmptyList() {

        customerRepository.deleteAll();
        List<CustomerDTO> dtos = customerController.listAllCustomers();

        assertThat(dtos.size()).isEqualTo(0);

    }

}