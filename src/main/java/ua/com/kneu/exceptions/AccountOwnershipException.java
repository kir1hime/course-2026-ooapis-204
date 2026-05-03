package ua.com.kneu.exceptions;

public class AccountOwnershipException extends Exception {
    @Override
    public String getMessage() {
        return "Inappropriate account is used";
    }
}
