package ua.com.kneu.entity.account.account_state;

import ua.com.kneu.entity.account.Account;
import ua.com.kneu.exceptions.PaymentException;

import java.math.BigDecimal;

// інтерфейс AccountState для визначення методів, притаманних кожному стану
public interface AccountState {
    // метод для ініціалізації оплати
    void makePayment(Account account, BigDecimal amountOfMoney) throws PaymentException;

    // метод для ініціалізації поповнення рахунку
    void topUp(Account account, BigDecimal amountOfMoney) throws PaymentException;
}
