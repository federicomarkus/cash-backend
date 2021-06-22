package validations;

import com.google.common.base.Preconditions;
import exceptions.BadRequestException;
import exceptions.ConstraintViolationException;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ApiPreconditions {

    public static void validateModel(Object model) {
        Preconditions.checkNotNull(model);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set constraintViolations = validator.validate(model);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    public static void checkNotNull(Object obj, String objectName) {
        if (obj == null) {
            throw new BadRequestException(objectName + " must not be null.");
        }
    }

}
