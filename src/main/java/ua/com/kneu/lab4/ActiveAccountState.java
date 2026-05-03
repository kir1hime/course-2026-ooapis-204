package ua.com.kneu.lab4;

import ua.com.kneu.lab2.entity.account.Account;

import java.math.BigDecimal;

public class ActiveAccountState implements  AccountState{


    @Override
    public void makePayment(Account account, BigDecimal amountOfMoney) {
        
    }

    @Override
    public void topUp(Account account, BigDecimal amountOfMoney) {

    }
}
