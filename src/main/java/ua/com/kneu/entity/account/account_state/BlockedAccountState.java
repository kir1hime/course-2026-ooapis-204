package ua.com.kneu.entity.account.account_state;

import ua.com.kneu.entity.account.Account;
import ua.com.kneu.exceptions.PaymentException;

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
