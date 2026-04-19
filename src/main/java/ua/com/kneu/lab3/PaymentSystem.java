package ua.com.kneu.lab3;

import ua.com.kneu.lab2.entity.account.Account;
import ua.com.kneu.lab2.entity.account.AccountState;
import ua.com.kneu.lab2.entity.account.Currency;
import ua.com.kneu.lab2.entity.card.Card;
import ua.com.kneu.lab2.entity.card.CardType;
import ua.com.kneu.lab2.entity.user.admin.Admin;
import ua.com.kneu.lab2.entity.user.admin.AdminType;
import ua.com.kneu.lab2.entity.user.client.Client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaymentSystem {

    private static final int BASE_PAYMENT_LIMIT = 3000;
    private static final Currency BASE_CURRENCY = Currency.UAH;
    private static final int NUMBERS_IN_IBAN = 27;
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";

    private static final Random random = new Random();

    private final List<Account> accounts = new ArrayList<>();
    private final List<Client> clients = new ArrayList<>();
    private final List<Card> cards = new ArrayList<>();

    private final Admin admin = new Admin(
            1111L,
            "Petro",
            "Kravchenko",
            "admin.admin@gmail.com",
            true,
            "#808Fasd9fasf",
            AdminType.MAIN_ADMIN);

    public void makePayment(long accountId, BigDecimal amountOfMoney) {
        Account account = getAccountById(accountId);

        if (account == null) {
            printAccountErrorMessage(accountId);
            return;
        }

        account.getClient().makePayment(account, amountOfMoney);
    }

    public void activeAccount(long accountId) {
        Account account = getAccountById(accountId);

        if (account == null) {
            printAccountErrorMessage(accountId);
            return;
        }

        admin.activeAccount(account);
    }

    public void registerAccountForClient(Client client) {
        String iban = generateUniqueIban();
        long id = accounts.isEmpty() ? 1 : accounts.getLast().getId() + 1;

        Account newAccount = new Account(
                id,
                iban,
                BigDecimal.ZERO,
                new BigDecimal(BASE_PAYMENT_LIMIT),
                BASE_CURRENCY,
                AccountState.ACTIVE,
                client);

        accounts.add(newAccount);

        if (!clients.contains(client)) {
            clients.add(client);
        }

        client.addAccount(newAccount);
    }

    public void blockAccount(long accountId) {
        Account account = getAccountById(accountId);

        if (account == null) {
            printAccountErrorMessage(accountId);
            return;
        }

        account.getClient().blockAccount(account);
    }

    public void topUpAccount(long accountId, BigDecimal amountOfMoney) {
        Account account = getAccountById(accountId);

        if (account == null) {
            printAccountErrorMessage(accountId);
            return;
        }

        account.getClient().topUpAccount(account, amountOfMoney);

    }

    public List<Card> getClientCards(Client client) {
        List<Card> clientCards = new ArrayList<>();
        for (Account account : client.getAccounts()) {
            clientCards.add(account.getCard());
        }
        return clientCards;
    }

    public void makeTransaction(long fromAccountId, long toAccountId, BigDecimal amountOfMoney) {
        Account fromAccount = getAccountById(fromAccountId);
        Account toAccount = getAccountById(toAccountId);

        if (fromAccount == null) {
            printAccountErrorMessage(fromAccountId);
            return;
        }
        if (toAccount == null) {
            printAccountErrorMessage(toAccountId);
            return;
        }

        if (fromAccount.getAccountState() == AccountState.BLOCKED || toAccount.getAccountState() == AccountState.BLOCKED) {
            return;
        }

        BigDecimal balanceBefore = fromAccount.getBalance();
        fromAccount.getClient().makePayment(fromAccount, amountOfMoney);

        if (fromAccount.getBalance().compareTo(balanceBefore) < 0) {
            toAccount.getClient().topUpAccount(toAccount, amountOfMoney);
            System.out.println("Transaction successful");
        } else {
            System.out.println("Transaction failed");
        }
    }

    public void createAndLinkCardToAccount(long accountId, CardType cardType) {
        Account account = getAccountById(accountId);

        if (account == null) {
            printAccountErrorMessage(accountId);
            return;
        }

        long cardId = cards.isEmpty() ? 1 : cards.getLast().getId() + 1;
        String hashedCvvCode = generateUniqueHashedCvvCode();
        String hashedCardNumber = generateUniqueHashedCardNumber();

        Card card = new Card(
                cardId,
                hashedCardNumber,
                "09/28",
                hashedCvvCode,
                cardType);

        account.setCard(card);
        card.setAccount(account);
        cards.add(card);
    }

    private String generateUniqueIban() {
        String iban = generateIban();
        while (isIbanRegistered(iban)) {
            iban = generateIban();
        }
        return iban;
    }

    private String generateIban() {
        StringBuilder sb = new StringBuilder("UA");

        for (int i = 0; i < NUMBERS_IN_IBAN; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    private boolean isIbanRegistered(String iban) {
        return accounts.stream()
                .anyMatch(account -> account.getIban().equals(iban));
    }

    private Account getAccountById(long id) {
        return accounts.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private String generateUniqueHashedCardNumber() {
        String hashedCardNumber = generateHashedString(11);
        while (isCardNumberRegistered(hashedCardNumber)) {
            hashedCardNumber = generateHashedString(11);
        }
        return hashedCardNumber;
    }

    private String generateUniqueHashedCvvCode() {
        String hashedCvvCode = generateHashedString(8);
        while (isCvvRegistered(hashedCvvCode)) {
            hashedCvvCode = generateHashedString(8);
        }
        return hashedCvvCode;
    }


    private String generateHashedString(int length) {
        StringBuilder sb = new StringBuilder("#");

        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }

        return sb.toString();
    }

    private boolean isCardNumberRegistered(String hashedCardNumber) {
        return cards.stream()
                .anyMatch(card -> card.getHashedCardNumber().equals(hashedCardNumber));
    }

    private boolean isCvvRegistered(String hashedCvvCode) {
        return cards.stream()
                .anyMatch(card -> card.getHashedCvvCode().equals(hashedCvvCode));
    }
    private void printAccountErrorMessage(long accountId){
        System.out.println("Account with id " + accountId + " not found");
    }
}
