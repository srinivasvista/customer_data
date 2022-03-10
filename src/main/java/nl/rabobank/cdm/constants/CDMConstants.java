package nl.rabobank.cdm.constants;

import java.util.regex.Pattern;

public class CDMConstants {
	
	public static final Pattern PATTERN_CUSTOMER_NAME = Pattern.compile("[a-zA-Z-]+");
	public static final Pattern PATTERN_CUSTOMER_ID = Pattern.compile("[0-9]+");
	public static final Pattern PATTERN_CUSTOMER_ADDRESS = Pattern.compile("[a-zA-Z0-9-,]+");
	public static final Pattern PATTERN_CUSTOMER_DOB = Pattern.compile("[0-9-]+");
	

	public static final String NO_RECORD_FOUND = "No record found";
	public static final String CUSTOMER_UPDATE_OPERATION_FAILED = "Customer Update operation failed";
	public static final String CUSTOMER_SAVE_OPERATION_FAILED = "Customer Save operation failed";
}
