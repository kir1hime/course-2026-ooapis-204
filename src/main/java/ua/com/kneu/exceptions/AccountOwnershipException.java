package ua.com.kneu.exceptions;

// виняток невідповідності клієнта з рахунком
public class AccountOwnershipException extends Exception {
    @Override
    public String getMessage() {
        return "Inappropriate account is used";
    }
}
