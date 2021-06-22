package validations;

import com.google.common.base.Preconditions;
import exceptions.InvalidEmailException;
import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidatorService {

    private static final EmailValidator validator =EmailValidator.getInstance();

    public static void validateEmail(String email) {
        Preconditions.checkNotNull(email);
        if (!validator.isValid(email)) {
            throw new InvalidEmailException();
        }
    }
}
