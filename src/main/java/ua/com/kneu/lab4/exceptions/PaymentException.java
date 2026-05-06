package ua.com.kneu.lab4.exceptions;

// виняток невдалої платіжної операції
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
