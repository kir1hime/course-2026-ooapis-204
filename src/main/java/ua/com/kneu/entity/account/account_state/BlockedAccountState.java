package ua.com.kneu.entity.account.account_state;

import ua.com.kneu.entity.account.Account;
import ua.com.kneu.exceptions.PaymentException;

import java.math.BigDecimal;

// клас, який являє собою заблокований стан рахунку
public class BlockedAccountState implements AccountState {

    // перевизначення методу з інтерфейсу для ініціалізації оплати
    @Override
    public void makePayment(Account account, BigDecimal amountOfMoney) throws PaymentException {
        // прокидання винятка з причиною
        throw new PaymentException("Account with id: " + account.getId() +" is blocked");
    }
    // перевизначення методу з інтерфейсу для ініціалізації поповнення рахунку
    @Override
    public void topUp(Account account, BigDecimal amountOfMoney) throws PaymentException {
        // прокидання винятка з причиною
        throw new PaymentException("Account with id: " + account.getId() +" is blocked");
    }

    // перевизначення методу toString
    @Override
    public String toString() {
        return "BlockedAccountState";
    }
}
