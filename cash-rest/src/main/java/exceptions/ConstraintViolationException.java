package exceptions;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstraintViolationException extends BadRequestException {

    public ConstraintViolationException(Set<ConstraintViolation<?>> constraintViolations) {
        Stream<String> violations = constraintViolations.stream().map(constraintViolation ->
                String.format("[%s %s] - ", constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage())).collect(Collectors.toList()).stream();
        setMessage(violations.collect(Collectors.joining()));
    }
}
