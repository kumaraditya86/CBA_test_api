package cba.example.bookdemo.response;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter @Setter @NoArgsConstructor
public class ResponseBody {
	
	private int responseCode;
	private String responseMsg;
	
	public ResponseBody(int responseCode, String responseBody) {
		
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
		
	}

}
