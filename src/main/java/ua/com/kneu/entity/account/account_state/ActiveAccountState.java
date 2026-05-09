package ua.com.kneu.entity.account.account_state;

import ua.com.kneu.entity.account.Account;
import ua.com.kneu.exceptions.PaymentException;
import ua.com.kneu.payment_validator.MonetaryOperationsValidator;
import ua.com.kneu.payment_validator.ValidationResult;

import java.math.BigDecimal;

// клас, який являє собою активний стан рахунку
public class ActiveAccountState implements AccountState {

    // перевизначення методу з інтерфейсу для ініціалізації оплати
    @Override
    public void makePayment(Account account, BigDecimal amountOfMoney) throws PaymentException {
        // валідація вхідних даних
        ValidationResult validationResult = MonetaryOperationsValidator.validatePayment(account, amountOfMoney);

        switch (validationResult) {
            // прокидання винятка у разі неуспішного результату
            case ValidationResult.FailureValidationResult failure ->
                    throw new PaymentException(failure.getMessage());

            // змінна балансу відповідного рахунку у разі успішного результату
            case ValidationResult.SuccessfulValidationResult _ ->
                    account.setBalance(account.getBalance().subtract(amountOfMoney));
        }
    }

    // перевизначення методу з інтерфейсу для ініціалізації поповнення рахунку
    @Override
    public void topUp(Account account, BigDecimal amountOfMoney) throws PaymentException {
        // валідація вхідних даних
        ValidationResult validationResult = MonetaryOperationsValidator.validateReplenishment(amountOfMoney);

        switch (validationResult){
            // прокидання винятка у разі неуспішного результату
            case ValidationResult.FailureValidationResult failure ->
                    throw new PaymentException(failure.getMessage());

            // змінна балансу відповідного рахунку у разі успішного результату
            case ValidationResult.SuccessfulValidationResult _ ->
                    account.setBalance(account.getBalance().add(amountOfMoney));
        }
    }

    // перевизначення методу toString
    @Override
    public String toString() {
        return "ActiveAccountState";
    }
}
