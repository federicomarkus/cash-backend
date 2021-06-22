package exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public ApiException() {
    }

}
