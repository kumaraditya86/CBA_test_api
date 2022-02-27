package cba.example.bookdemo.config;

import org.springframework.stereotype.Component;

@Component
public class ConstantMessage {

	public static final int SUCCESS_CODE = 0;
	public static final int FAIL_CODE = 1;
	public static final int EXCEPTION_CODE = 2;

	public static final String INSERT_SUCCESS = "Book detail saved successfully.";
	public static final String INSERT_FAIL = "Book detail save operation filed.";
	public static final String DELETE_SUCCESS = "Book detail deleted successfully.";
	public static final String DELETE_FAIL = "Book detail not found.";
	public static final String EXCEPTION_MSG = "Constraint Violation. Duplicate value.";
}
