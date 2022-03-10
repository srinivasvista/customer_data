package nl.rabobank.cdm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.rabobank.cdm.data.entities.Customer;
import nl.rabobank.cdm.exception.CDMException;
import nl.rabobank.cdm.model.APIResponse;
import nl.rabobank.cdm.model.CustomerDetails;
import nl.rabobank.cdm.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customer")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(value = "/customer/id/{id}")
    public Customer getCustomerById(@PathVariable String id) throws CDMException {
        return customerService.getCustomerById(id);
    }
    
    @GetMapping(value = "/customer/{name}")
    public List<Customer> getCustomerByName(@PathVariable String name) throws CDMException {
        return customerService.getCustomerByName(name);
    }
    
    @PostMapping(value = "/customer")
    public APIResponse saveCustomer(@ModelAttribute("customerDetails") CustomerDetails customerDetails) throws CDMException {
        customerService.saveCustomer(customerDetails);
        return new APIResponse("SUCCESS", "Save Operation successful");
        
    }

	@PutMapping(value = "/customer")
    public APIResponse updateCustomer(@ModelAttribute("customerDetails") CustomerDetails customerDetails) throws CDMException {
        customerService.updateCustomer(customerDetails);
        return new APIResponse("SUCCESS", "Update Operation successful");
    }

}
