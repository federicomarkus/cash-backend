package exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

    public BadRequestException() {
        setHttpStatus(HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        setHttpStatus(HttpStatus.BAD_REQUEST);
        setMessage(message);
    }

}
