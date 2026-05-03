package ua.com.kneu.lab4.exceptions;

public class AccountOwnershipException extends Exception {
    @Override
    public String getMessage() {
        return "Inappropriate account is used";
    }
}
