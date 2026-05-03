package ua.com.kneu.payment_validator;

import ua.com.kneu.entity.account.Account;

import java.math.BigDecimal;

public class MonetaryOperationsValidator {

    public static ValidationResult validatePayment(Account account, BigDecimal amountOfMoney) {
        if (amountOfMoney.compareTo(account.getBalance()) > 0) {
            return new ValidationResult.FailureValidationResult("There aren't enough money in your account");
        }
        if (amountOfMoney.compareTo(BigDecimal.ZERO) <= 0) {
            return new ValidationResult.FailureValidationResult("Amount must be positive");
        }
        if (amountOfMoney.compareTo(account.getPaymentLimit()) > 0) {
            return new ValidationResult.FailureValidationResult("The amount of money you want to pay is bigger than your limit");
        }
        return new ValidationResult.SuccessfulValidationResult();
    }

    public static ValidationResult validateReplenishment(BigDecimal amountOfMoney) {
        if (amountOfMoney.compareTo(BigDecimal.ZERO) <= 0) {
            return new ValidationResult.FailureValidationResult("Amount must be positive");
        }
        return new ValidationResult.SuccessfulValidationResult();
    }
}
