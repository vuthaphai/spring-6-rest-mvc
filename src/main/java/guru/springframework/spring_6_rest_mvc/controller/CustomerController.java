package guru.springframework.spring_6_rest_mvc.controller;


import guru.springframework.spring_6_rest_mvc.model.Customer;
import guru.springframework.spring_6_rest_mvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;



    @PatchMapping("{customerId}")
    public ResponseEntity updateCustomerPatchById(@PathVariable("customerId") UUID customerId,@RequestBody Customer customer){
        customerService.updateCustomerPatchById(customerId,customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity deleteCustomerById(@PathVariable("customerId") UUID customerId){

        customerService.deleteCustomerById(customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{customerId}")
    public ResponseEntity updateCustomerById(@PathVariable("customerId") UUID customerId,@RequestBody Customer customer){
        customerService.updateCustomerById(customerId,customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer customer){
        Customer saveCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new  HttpHeaders();
        headers.add("Location","/api/v1/customer/" + saveCustomer.getId().toString());

        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listAllCustomers(){
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID id){
        return customerService.getCustomerById(id);
    }

}
