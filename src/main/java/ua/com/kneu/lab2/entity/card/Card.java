package ua.com.kneu.lab2.entity.card;

import ua.com.kneu.lab2.entity.account.Account;

public class Card {
    private long id;
    private String hashedCardNumber;
    private String expiryDate;
    private String hashedCvvCode;
    private CardType cardType;
    private Account account;

    public Card(long id, String hashedCardNumber, String expiryDate, String hashedCvvCode, CardType cardType, Account account) {
        this.id = id;
        this.hashedCardNumber = hashedCardNumber;
        this.expiryDate = expiryDate;
        this.hashedCvvCode = hashedCvvCode;
        this.cardType = cardType;
        this.account = account;
    }

    public Card(long id, String hashedCardNumber, String expiryDate, String hashedCvvCode, CardType cardType) {
        this.id = id;
        this.hashedCardNumber = hashedCardNumber;
        this.expiryDate = expiryDate;
        this.hashedCvvCode = hashedCvvCode;
        this.cardType = cardType;
    }

    public Card() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHashedCardNumber() {
        return hashedCardNumber;
    }

    public void setHashedCardNumber(String hashedCardNumber) {
        this.hashedCardNumber = hashedCardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getHashedCvvCode() {
        return hashedCvvCode;
    }

    public void setHashedCvvCode(String hashedCvvCode) {
        this.hashedCvvCode = hashedCvvCode;
    }


    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", hashedCardNumber='" + hashedCardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", hashedCvvCode='" + hashedCvvCode + '\'' +
                ", cardType=" + cardType +
                ", accountId=" + account.getId() +
                '}';
    }
}
