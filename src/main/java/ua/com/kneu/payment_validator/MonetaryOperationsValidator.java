package ua.com.kneu.payment_validator;

import ua.com.kneu.entity.account.Account;

import java.math.BigDecimal;
// клас для проведення валідації платіжних операцій
public class MonetaryOperationsValidator {

    // метод для валідації коректності платежу
    public static ValidationResult validatePayment(Account account, BigDecimal amountOfMoney) {
        // перевірка на те чи достатньо коштів на рахунку
        if (amountOfMoney.compareTo(account.getBalance()) > 0) {
            return new ValidationResult.FailureValidationResult("There aren't enough money in your account");
        }
        // перевірка вхідного значення суми грошей
        if (amountOfMoney.compareTo(BigDecimal.ZERO) <= 0) {
            return new ValidationResult.FailureValidationResult("Amount must be positive");
        }
        // перевірка на перевищення ліміту
        if (amountOfMoney.compareTo(account.getPaymentLimit()) > 0) {
            return new ValidationResult.FailureValidationResult("The amount of money you want to pay is bigger than your limit");
        }
        return new ValidationResult.SuccessfulValidationResult();
    }

    // метод для валідації коректності поповнення рахунку
    public static ValidationResult validateReplenishment(BigDecimal amountOfMoney) {
        // перевірка вхідного значення суми грошей
        if (amountOfMoney.compareTo(BigDecimal.ZERO) <= 0) {
            return new ValidationResult.FailureValidationResult("Amount must be positive");
        }
        return new ValidationResult.SuccessfulValidationResult();
    }
}
