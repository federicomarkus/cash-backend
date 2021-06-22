package advice;

import exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
public class BaseApiExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ApiException.class})
    protected ResponseEntity<String> handleApiException(ApiException ex) {
        log.debug("Api error: " + ex.getMessage(), ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        log.debug("Critical error: " + ex.getMessage(), ex);
        return ResponseEntity.status(500).body(ex.getMessage());
    }
}

