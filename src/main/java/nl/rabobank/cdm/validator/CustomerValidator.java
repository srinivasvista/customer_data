package nl.rabobank.cdm.validator;

import static nl.rabobank.cdm.constants.CDMConstants.PATTERN_CUSTOMER_ADDRESS;
import static nl.rabobank.cdm.constants.CDMConstants.PATTERN_CUSTOMER_DOB;
import static nl.rabobank.cdm.constants.CDMConstants.PATTERN_CUSTOMER_ID;
import static nl.rabobank.cdm.constants.CDMConstants.PATTERN_CUSTOMER_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.cdm.exception.CDMException;
import nl.rabobank.cdm.model.CustomerDetails;

@Component
@Slf4j
public class CustomerValidator {
	
	public void validateCustomerDetails(CustomerDetails customerDetails) throws CDMException{
		String errorMessage = null;
		List<String> errorMessageList = new ArrayList<>();
		
		validateField("id", customerDetails.getId(), PATTERN_CUSTOMER_ID, errorMessageList);
		validateField("firstName", customerDetails.getFirstName(), PATTERN_CUSTOMER_NAME, errorMessageList);
		validateField("lastName", customerDetails.getLastName(), PATTERN_CUSTOMER_NAME, errorMessageList);
		validateField("address", customerDetails.getAddress(), PATTERN_CUSTOMER_ADDRESS, errorMessageList);
		validateField("dob", customerDetails.getDob(), PATTERN_CUSTOMER_DOB, errorMessageList);
		
		if (errorMessageList.size() > 0) {
			errorMessage= String.join(", ", errorMessageList);
			throw new CDMException(errorMessage);
		}
	}


	private void validateField(String fieldName, String fieldValue, Pattern pattern, List<String> errorMessageList) {
		if (isNullOrEmpty(fieldValue)
				|| isNotValid(fieldValue, pattern)){
			errorMessageList.add(fieldName +" is invalid");
		}
	}


	private boolean isNotValid(String fieldvalue, Pattern regexPattern) {
		Matcher matcher = regexPattern.matcher(fieldvalue);
		if (!matcher.matches()) {
			return true;
		}
		return false;
	}
	
	private boolean isNullOrEmpty(String fieldvalue) {
		if (fieldvalue == null || "".equals(fieldvalue)) {
			return true;
		}
		return false;
	}
}
