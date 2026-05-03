package ua.com.kneu.lab4.account_state;

import ua.com.kneu.lab2.entity.account.Account;
import ua.com.kneu.lab4.exceptions.PaymentException;

import java.math.BigDecimal;

public class BlockedAccountState implements AccountState {

    @Override
    public void makePayment(Account account, BigDecimal amountOfMoney) throws PaymentException {
        throw new PaymentException("Account with id: " + account.getId() +" is blocked");
    }

    @Override
    public void topUp(Account account, BigDecimal amountOfMoney) throws PaymentException {
        throw new PaymentException("Account with id: " + account.getId() +" is blocked");
    }

    @Override
    public String toString() {
        return "BlockedAccountState";
    }
}
