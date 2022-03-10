package nl.rabobank.cdm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.rabobank.cdm.controllers.CustomerController;
import nl.rabobank.cdm.data.entities.Customer;
import nl.rabobank.cdm.exception.CDMException;
import nl.rabobank.cdm.model.APIResponse;
import nl.rabobank.cdm.model.CustomerDetails;
import nl.rabobank.cdm.service.CustomerService;


public class CustomerControllerTest {
	
	@InjectMocks
	CustomerController customerController;
	
	@Mock
	CustomerService customerService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_get_all_customers() {
		List<Customer> expectedCustomerList = new ArrayList<>();
		expectedCustomerList.add(new Customer("1", "testuser1", "lastname1", "address1", "01012000", "22"));
		expectedCustomerList.add(new Customer("2", "testuser2", "lastname2", "address2", "02022000", "22"));
		when(customerService.getCustomers()).thenReturn(expectedCustomerList);
		List<Customer> actualCustomerList = customerController.getCustomers();
		assertEquals(expectedCustomerList, actualCustomerList);
		
	}

	@Test
	public void test_get_customer_by_name() throws CDMException {
		List<Customer> expectedCustomerList = new ArrayList<>();
		expectedCustomerList.add(new Customer("1", "testuser1", "lastname1", "address1", "01012000", "22"));
		expectedCustomerList.add(new Customer("2", "testuser2", "lastname2", "address2", "02022000", "22"));
		when(customerService.getCustomerByName("testUser1")).thenReturn(expectedCustomerList);
		List<Customer> actualCustomerList = customerController.getCustomerByName("testUser1");
		assertEquals(expectedCustomerList, actualCustomerList);
		
	}
	
	@Test
	public void test_save_customer() throws CDMException {
		//when(customerService.saveCustomer(any())).thenReturn(null);
		APIResponse apiResponse = customerController.saveCustomer(new CustomerDetails("1", "testuser1", "lastname1", "address1", "01012000"));
		assertEquals(apiResponse.getStatus(), "SUCCESS");
		
	}
	
	//@Test
	public void test_save_customer_failed() throws CDMException {
		//when(customerService.saveCustomer(any())).
		APIResponse apiResponse = customerController.saveCustomer(new CustomerDetails("1", "testuser1", "lastname1", "address1", "01012000"));
		assertEquals(apiResponse.getStatus(), "SUCCESS");
		
	}

	
	
}
