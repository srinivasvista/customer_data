package nl.rabobank.cdm.service;

import java.util.ArrayList;
import java.util.List;

import nl.rabobank.cdm.data.entities.Customer;
import nl.rabobank.cdm.exception.CDMException;
import nl.rabobank.cdm.model.CustomerDetails;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	 public Customer getCustomerById(String id) throws CDMException; 
	 
    public List<Customer> getCustomerByName(String name) throws CDMException;
    
    public void saveCustomer(CustomerDetails customerDetails) throws CDMException;

    public void updateCustomer(CustomerDetails customerDetails) throws CDMException;

}
