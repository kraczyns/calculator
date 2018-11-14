package exceptions;

public class InvalidCalculatorInputException extends Exception {

    private String invalidInput;

    public InvalidCalculatorInputException(String invalidInput) {
        this.invalidInput = invalidInput;
    }

    public String getMessage() {
        return "Invalid input - " + invalidInput;
    }
}
