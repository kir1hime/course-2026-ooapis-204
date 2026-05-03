package ua.com.kneu.lab4;

import ua.com.kneu.lab2.entity.account.Account;

import java.math.BigDecimal;

public interface AccountState {
    void makePayment(Account account, BigDecimal amountOfMoney);

    void topUp(Account account, BigDecimal amountOfMoney);
}
