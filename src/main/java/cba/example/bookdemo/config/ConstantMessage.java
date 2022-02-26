package cba.example.bookdemo.config;

import org.springframework.stereotype.Component;

@Component
public class ConstantMessage {

	public static final int SUCCESS_CODE = 1;
	public static final int FAIL_CODE = 0;
	public static final int EXCEPTION_CODE = 3;

	public static final String INSERT_SUCCESS = "Book detail saved successfully.";
	public static final String INSERT_FAIL = "Book detail save operation filed.";
}
