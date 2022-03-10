package nl.rabobank.cdm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.cdm.data.entities.Customer;
import nl.rabobank.cdm.data.repository.CustomerRepository;
import nl.rabobank.cdm.exception.CDMException;
import nl.rabobank.cdm.model.CustomerDetails;
import nl.rabobank.cdm.validator.CustomerValidator;
import static nl.rabobank.cdm.constants.CDMConstants.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerValidator customerValidator;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getCustomers() {
		
		List<Customer> customerList = new ArrayList<>();
		Iterable<Customer> customerIterator = customerRepository.findAll();
		customerIterator.forEach(customerList::add);
		
        return customerList;
    }

	 public Customer getCustomerById(String id) throws CDMException {
		 Optional<Customer> customer = customerRepository.findById(id);
	     if (customer.isEmpty()) {
	    	 throw new CDMException(NO_RECORD_FOUND);
	     }
	     return customer.get();
	 }
	 
    public List<Customer> getCustomerByName(String name) throws CDMException {
    	
        List<Customer> customerList = customerRepository.findByFirstNameContainsOrLastNameContains(name, name);
        
        if (customerList == null || customerList.size() == 0) {
        	throw new CDMException(NO_RECORD_FOUND);
        }
        
        return customerList;
    }
    
    public void saveCustomer(CustomerDetails customerDetails) throws CDMException{
    	//validate the customer details
    	customerValidator.validateCustomerDetails(customerDetails);
    	
    	try {
    		customerRepository.save(customerDetails.getCustomerEntity());
    	}
    	catch(Exception ex) {
    		throw new CDMException(CUSTOMER_SAVE_OPERATION_FAILED);
    	}
    }

    public void updateCustomer(CustomerDetails customerDetails) throws CDMException {
    	//validate the customer details
    	customerValidator.validateCustomerDetails(customerDetails);
    	try {
    		customerRepository.save(customerDetails.getCustomerEntity());
    	}
    	catch(Exception ex) {
    		throw new CDMException(CUSTOMER_UPDATE_OPERATION_FAILED);
    	}
    }

}
