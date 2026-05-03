package ua.com.kneu.entity.account.account_state;

import ua.com.kneu.entity.account.Account;
import ua.com.kneu.exceptions.PaymentException;

import java.math.BigDecimal;

public interface AccountState {
    void makePayment(Account account, BigDecimal amountOfMoney) throws PaymentException;

    void topUp(Account account, BigDecimal amountOfMoney) throws PaymentException;

}
