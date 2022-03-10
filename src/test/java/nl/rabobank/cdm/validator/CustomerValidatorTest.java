package nl.rabobank.cdm.validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.rabobank.cdm.data.entities.Customer;
import nl.rabobank.cdm.exception.CDMException;
import nl.rabobank.cdm.model.APIResponse;
import nl.rabobank.cdm.model.CustomerDetails;
import nl.rabobank.cdm.service.CustomerService;

public class CustomerValidatorTest {
	
	@InjectMocks
	CustomerValidator customerValidator;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_valid_customer_details() {
		CustomerDetails customerDetails = new CustomerDetails("1", "testuser", "lastname", "address1", "01012000");
		CDMException cdmException = null;
		try {
			customerValidator.validateCustomerDetails(customerDetails);
		}
		catch(CDMException ex) {
			cdmException = ex;
		}
		assertNull(cdmException);
		
	}

	@Test
	public void test_invalid_id() {
		CustomerDetails customerDetails = new CustomerDetails("1#", "testuser", "lastname", "address1", "01012000");
		CDMException cdmException = null;
		try {
			customerValidator.validateCustomerDetails(customerDetails);
		}
		catch(CDMException ex) {
			cdmException = ex;
		}
		assertNotNull(cdmException);
		
	}
	
	@Test
	public void test_invalid_values() {
		CustomerDetails customerDetails = new CustomerDetails("", "testuser#", "lastname#", "address()1", "01019&00");
		CDMException cdmException = null;
		try {
			customerValidator.validateCustomerDetails(customerDetails);
		}
		catch(CDMException ex) {
			cdmException = ex;
		}
		System.out.println(cdmException.getMessage());
		assertNotNull(cdmException);
		
	}
	
	
	
}
