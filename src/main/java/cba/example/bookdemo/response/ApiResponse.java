package cba.example.bookdemo.response;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter @Setter @NoArgsConstructor
public class ApiResponse {
	
	private int responseCode;
	private String responseMsg;
	
	public ApiResponse(int responseCode, String responseMsg) {
		
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
		
	}

}
