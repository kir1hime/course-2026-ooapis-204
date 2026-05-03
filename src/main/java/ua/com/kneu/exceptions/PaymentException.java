package ua.com.kneu.exceptions;

public class PaymentException extends Exception {
    private final String message;

    public PaymentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
