package ua.com.kneu.lab4.account_state;

import ua.com.kneu.lab2.entity.account.Account;
import ua.com.kneu.lab4.exceptions.PaymentException;

import java.math.BigDecimal;

public interface AccountState {
    void makePayment(Account account, BigDecimal amountOfMoney) throws PaymentException;

    void topUp(Account account, BigDecimal amountOfMoney) throws PaymentException;

}
