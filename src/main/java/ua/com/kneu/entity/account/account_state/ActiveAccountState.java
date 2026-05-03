package ua.com.kneu.entity.account.account_state;

import ua.com.kneu.entity.account.Account;
import ua.com.kneu.exceptions.PaymentException;
import ua.com.kneu.payment_validator.MonetaryOperationsValidator;
import ua.com.kneu.payment_validator.ValidationResult;

import java.math.BigDecimal;

public class ActiveAccountState implements AccountState {


    @Override
    public void makePayment(Account account, BigDecimal amountOfMoney) throws PaymentException {
        ValidationResult validationResult = MonetaryOperationsValidator.validatePayment(account, amountOfMoney);

        if (validationResult instanceof ValidationResult.FailureValidationResult) {
            throw new PaymentException(((ValidationResult.FailureValidationResult) validationResult).getMessage());
        }
        account.setBalance(account.getBalance().subtract(amountOfMoney));
    }

    @Override
    public void topUp(Account account, BigDecimal amountOfMoney) throws PaymentException {
        ValidationResult validationResult = MonetaryOperationsValidator.validateReplenishment(amountOfMoney);

        if (validationResult instanceof ValidationResult.FailureValidationResult) {
            throw new PaymentException(((ValidationResult.FailureValidationResult) validationResult).getMessage());
        }
        account.setBalance(account.getBalance().add(amountOfMoney));
    }

    @Override
    public String toString() {
        return "ActiveAccountState";
    }
}
