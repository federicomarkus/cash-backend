package exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException() {
        setHttpStatus(HttpStatus.NOT_FOUND);
    }

}
