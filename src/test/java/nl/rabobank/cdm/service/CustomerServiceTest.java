package nl.rabobank.cdm.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import nl.rabobank.cdm.data.repository.CustomerRepository;
import nl.rabobank.cdm.exception.CDMException;
import nl.rabobank.cdm.model.CustomerDetails;
import nl.rabobank.cdm.validator.CustomerValidator;


public class CustomerServiceTest {
	
	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Mock
	CustomerValidator customerValidator;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_save_invalid_customer() throws CDMException {
		CDMException cdmException = null;
		try {
			Mockito.doThrow(new CDMException("invalid request")).when(customerValidator).validateCustomerDetails(any());
			customerService.saveCustomer(new CustomerDetails("1", "testuser1", "lastname1", "address1", "01012000"));
		}
		catch(CDMException ex) {
			cdmException = ex;
		}
		
		assertNotNull(cdmException);
		
	}

	
	
}
