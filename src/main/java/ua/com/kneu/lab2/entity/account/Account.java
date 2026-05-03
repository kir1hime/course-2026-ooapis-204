package ua.com.kneu.lab2.entity.account;

import ua.com.kneu.lab2.entity.card.Card;
import ua.com.kneu.lab2.entity.user.client.Client;
import ua.com.kneu.lab4.account_state.AccountState;
import ua.com.kneu.lab4.account_state.ActiveAccountState;
import ua.com.kneu.lab4.exceptions.PaymentException;

import java.math.BigDecimal;

public class Account {
    private long id;
    private String iban;
    private BigDecimal balance;
    private BigDecimal paymentLimit;
    private Currency currency;
    private Card card;
    private Client client;
    private AccountState accountState;

    public Account(long id, String iban, BigDecimal balance, BigDecimal paymentLimit, Currency currency, Card card, Client client) {
        this.id = id;
        this.iban = iban;
        this.balance = balance;
        this.paymentLimit = paymentLimit;
        this.currency = currency;
        this.card = card;
        this.client = client;
        accountState = new ActiveAccountState();
    }

    public Account(long id, String iban, BigDecimal balance, BigDecimal paymentLimit, Currency currency) {
        this.id = id;
        this.iban = iban;
        this.balance = balance;
        this.paymentLimit = paymentLimit;
        this.currency = currency;
        accountState = new ActiveAccountState();
    }

    public Account(long id, String iban, BigDecimal balance, BigDecimal paymentLimit, Currency currency, Client client) {
        this.id = id;
        this.iban = iban;
        this.balance = balance;
        this.paymentLimit = paymentLimit;
        this.currency = currency;
        this.client = client;
        accountState = new ActiveAccountState();
    }

    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState(AccountState accountState) {
        this.accountState = accountState;
    }

    public Account() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPaymentLimit() {
        return paymentLimit;
    }

    public void setPaymentLimit(BigDecimal paymentLimit) {
        this.paymentLimit = paymentLimit;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void makePayment(BigDecimal amountOfMoney) throws PaymentException {
        accountState.makePayment(this, amountOfMoney);
    }

    public void topUp(BigDecimal amountOfMoney) throws PaymentException {
        accountState.topUp(this, amountOfMoney);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", creditLimit=" + paymentLimit +
                ", currency='" + currency + '\'' +
                ", cardId=" + (card == null ? "no card" : card.getId()) +
                ", clientId=" + (client == null ? "no client" : client.getId()) +
                '}';
    }
}
