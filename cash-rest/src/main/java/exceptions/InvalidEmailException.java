package exceptions;

public class InvalidEmailException extends BadRequestException {

    public InvalidEmailException() {
        setMessage("The email is not valid.");
    }
}
