package ua.com.kneu.lab4.entity.payment_system;

import ua.com.kneu.lab4.entity.account.Account;
import ua.com.kneu.lab4.entity.account.Currency;
import ua.com.kneu.lab4.entity.card.Card;
import ua.com.kneu.lab4.entity.card.CardType;
import ua.com.kneu.lab4.entity.user.admin.Admin;
import ua.com.kneu.lab4.entity.user.admin.AdminType;
import ua.com.kneu.lab4.entity.user.client.Client;
import ua.com.kneu.lab4.exceptions.AccountOwnershipException;
import ua.com.kneu.lab4.exceptions.PaymentException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Клас для зручного маніпулювання платіжними операціями (реалізований на основі патерну - фасад)
public class PaymentSystem {

    // ініціалізація констант
    private static final Currency BASE_CURRENCY = Currency.UAH;
    private static final int NUMBERS_IN_IBAN = 27;
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";

    private static final Random random = new Random();

    // ініціалізація списків сутностей платіжної системи
    private final List<Account> accounts = new ArrayList<>();
    private final List<Client> clients = new ArrayList<>();
    private final List<Card> cards = new ArrayList<>();

    // створення об'єкта адміна для виконання операції - розблокування
    private final Admin admin = new Admin(
            1111L,
            "Petro",
            "Kravchenko",
            "admin.admin@gmail.com",
            true,
            "#808Fasd9fasf",
            AdminType.MAIN_ADMIN);

    // метод для реалізації платежів
    public void makePayment(long accountId, BigDecimal amountOfMoney) {
        // отримання об'єкта рахунка за його ідентифікатором
        Account account = getAccountById(accountId);
        // перевірка на наявність рахунку з введеним ідентифікатором в системі платежів
        if (account == null) {
            printAccountErrorMessage(accountId);
            return;
        }
        // реалізація платежу
        try {
            account.getClient().makePayment(account, amountOfMoney);
            System.out.println("Payment is successful");
        } catch (PaymentException e) {
            System.out.println("Unfortunately payment is failed, cause: " + e.getMessage());
        } catch (AccountOwnershipException e) {
            System.out.println("Ownership error: " + e.getMessage());
        }

    }

    // метод для активації рахунку
    public void activeAccount(long accountId) {
        // отримання об'єкта рахунка за його ідентифікатором
        Account account = getAccountById(accountId);
        // перевірка на наявність рахунку з введеним ідентифікатором в системі платежів
        if (account == null) {
            printAccountErrorMessage(accountId);
            return;
        }

        // активація рахунку за допомогою об'єкта адміна
        admin.activeAccount(account);
    }

    // метод для створення рахунку для користувача
    public void registerAccountForClient(Client client, BigDecimal paymentLimit) {
        // визначення унікальних властивостей рахунку
        String iban = generateUniqueIban();
        long id = accounts.isEmpty() ? 1 : accounts.getLast().getId() + 1;
        // створення об'єкта - рахунок, на основі
        Account newAccount = new Account(
                id,
                iban,
                BigDecimal.ZERO,
                paymentLimit,
                BASE_CURRENCY,
                client);
        // додавання нового рахунку в список рахунків
        accounts.add(newAccount);

        // перевірка на те, чи знаходиться клієнт в платіжній системі
        if (!clients.contains(client)) {
            // додавання нового клієнта у відповідний список
            clients.add(client);
        }

        // додавання нового рахунку в список рахунків клієнта
        client.addAccount(newAccount);
    }

    // метод для блокування рахунку
    public void blockAccount(long accountId) {
        // отримання об'єкта рахунка за його ідентифікатором
        Account account = getAccountById(accountId);
        // перевірка на наявність рахунку з введеним ідентифікатором в системі платежів
        if (account == null) {
            printAccountErrorMessage(accountId);
            return;
        }

        // блокування рахунку
        try {
            account.getClient().blockAccount(account);
        } catch (AccountOwnershipException e) {
            System.out.println("Ownership error: " + e.getMessage());
        }
    }

    // метод для поповнення рахунку
    public void topUpAccount(long accountId, BigDecimal amountOfMoney) {
        // отримання об'єкта рахунка за його ідентифікатором
        Account account = getAccountById(accountId);
        // перевірка на наявність рахунку з введеним ідентифікатором в системі платежів
        if (account == null) {
            printAccountErrorMessage(accountId);
            return;
        }

        // поповнення рахунку
        try {
            account.getClient().topUpAccount(account, amountOfMoney);
            System.out.println("Replenishment is successful");
        } catch (PaymentException e) {
            System.out.println("Unfortunately payment is failed, cause: " + e.getMessage());
        } catch (AccountOwnershipException e) {
            System.out.println("Ownership error: " + e.getMessage());
        }
    }

    // метод для отримання всіх платіжних карток певного клієнта
    public List<Card> getClientCards(Client client) {
        // створення пустого списку карток
        List<Card> clientCards = new ArrayList<>();
        // ітерація по всіх рахунках клієнта
        for (Account account : client.getAccounts()) {
            // додавання картки в список карток
            clientCards.add(account.getCard());
        }
        return clientCards;
    }

    // метод для переказу коштів з одного рахунку на інший
    public void makeTransaction(long fromAccountId, long toAccountId, BigDecimal amountOfMoney) {
        // отримання відповідних рахунків за ідентифікаторами
        Account fromAccount = getAccountById(fromAccountId);
        Account toAccount = getAccountById(toAccountId);
        // перевірка на наявність рахунку з введеним ідентифікатором в системі платежів
        if (fromAccount == null) {
            printAccountErrorMessage(fromAccountId);
            return;
        }
        // перевірка на наявність рахунку з введеним ідентифікатором в системі платежів
        if (toAccount == null) {
            printAccountErrorMessage(toAccountId);
            return;
        }

        try {
            // реалізація платежу з fromAccount
            fromAccount.getClient().makePayment(fromAccount, amountOfMoney);
            // поповнення на toAccount
            toAccount.getClient().topUpAccount(toAccount, amountOfMoney);
            System.out.println("Transaction successful");
        } catch (PaymentException | AccountOwnershipException e) {
            System.out.println("Transaction failed");
            System.out.println(e.getMessage());
        }
    }

    public void createAndLinkCardToAccount(long accountId, CardType cardType) {
        // отримання об'єкта рахунка за його ідентифікатором
        Account account = getAccountById(accountId);
        // перевірка на наявність рахунку з введеним ідентифікатором в системі платежів
        if (account == null) {
            printAccountErrorMessage(accountId);
            return;
        }
        // визначення унікальних властивостей карти
        long cardId = cards.isEmpty() ? 1 : cards.getLast().getId() + 1;
        String hashedCvvCode = generateUniqueHashedCvvCode();
        String hashedCardNumber = generateUniqueHashedCardNumber();

        // створення об'єкта карти
        Card card = new Card(
                cardId,
                hashedCardNumber,
                "09/28",
                hashedCvvCode,
                cardType);

        // призначення рахунку карти
        account.setCard(card);
        // призначення карті рахунок
        card.setAccount(account);
        // додавання карти в список карт платіжної системи
        cards.add(card);
    }

    // метод для отримання унікального iban
    private String generateUniqueIban() {
        // генерація нового iban доти, доки не буде знайдено унікальний варіант
        String iban = generateIban();
        while (isIbanRegistered(iban)) {
            iban = generateIban();
        }
        return iban;
    }

    // метод для генерації рядка iban у форматі "UA" + 27 випадкових цифр
    private String generateIban() {
        StringBuilder sb = new StringBuilder("UA");

        for (int i = 0; i < NUMBERS_IN_IBAN; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    // перевірка наявності рахунку з таким iban у платіжній системі
    private boolean isIbanRegistered(String iban) {
        return accounts.stream()
                .anyMatch(account -> account.getIban().equals(iban));
    }

    // пошук рахунку за його унікальним ідентифікатором
    private Account getAccountById(long id) {
        return accounts.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // метод для отримання унікального хешованого номера картки
    private String generateUniqueHashedCardNumber() {
        // генерація нового номера картки доти, доки не буде знайдено унікальний варіант
        String hashedCardNumber = generateHashedString(11);
        while (isCardNumberRegistered(hashedCardNumber)) {
            hashedCardNumber = generateHashedString(11);
        }
        return hashedCardNumber;
    }

    // метод для отримання унікального хешованого cvv-коду
    private String generateUniqueHashedCvvCode() {
        // генерація нового cvv-коду доти, доки не буде знайдено унікальний варіант
        String hashedCvvCode = generateHashedString(8);
        while (isCvvRegistered(hashedCvvCode)) {
            hashedCvvCode = generateHashedString(8);
        }
        return hashedCvvCode;
    }

    // метод для генерації випадкового хешованого рядка заданої довжини з префіксом "#"
    private String generateHashedString(int length) {
        StringBuilder sb = new StringBuilder("#");

        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }

        return sb.toString();
    }

    // перевірка наявності картки з таким хешованим номером у платіжній системі
    private boolean isCardNumberRegistered(String hashedCardNumber) {
        return cards.stream()
                .anyMatch(card -> card.getHashedCardNumber().equals(hashedCardNumber));
    }

    // перевірка наявності картки з таким хешованим cvv-кодом у платіжній системі
    private boolean isCvvRegistered(String hashedCvvCode) {
        return cards.stream()
                .anyMatch(card -> card.getHashedCvvCode().equals(hashedCvvCode));
    }

    // виведення повідомлення про відсутність рахунку з вказаним ідентифікатором
    private void printAccountErrorMessage(long accountId) {
        System.out.println("Account with id " + accountId + " isn't founded");
    }
}
