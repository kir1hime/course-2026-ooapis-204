package ua.com.kneu.lab3;

import ua.com.kneu.lab2.entity.account.Account;
import ua.com.kneu.lab2.entity.card.CardType;
import ua.com.kneu.lab2.entity.user.client.Client;

import java.math.BigDecimal;

public class Main {
    static void main(String[] args) {
        Client client1 = new Client(
                1L,
                "Denys",
                "Shevchenko",
                "Olegovich",
                "den.sheva2000@gmail.com",
                true,
                20,
                "+380000000000",
                "Kyiv");

        Client client2 = new Client(
                2L,
                "Ivan",
                "Popeluh",
                "Stepanovich",
                "ivan.popel@gmail.com",
                true,
                24,
                "+380000000000",
                "Kyiv");

        System.out.println("Clients: \n");
        System.out.println("Client1: " + client1 + "\n");
        System.out.println("Client2: " + client2 + "\n");

        PaymentSystem paymentSystem = new PaymentSystem();

        paymentSystem.registerAccountForClient(client1, new BigDecimal(3000));
        paymentSystem.registerAccountForClient(client2, new BigDecimal(5000));

        Account client1Account = client1.getAccounts().getFirst();
        Account client2Account = client2.getAccounts().getFirst();

        System.out.println("Accounts: \n");
        System.out.println("Account1: " + client1Account + "\n");
        System.out.println("Account2: " + client2Account + "\n");

        long client1AccountId = client1Account.getId();
        long client2AccountId = client2Account.getId();

        paymentSystem.createAndLinkCardToAccount(client1AccountId, CardType.DEBIT);
        paymentSystem.createAndLinkCardToAccount(client2AccountId, CardType.CREDIT);

        System.out.println("Accounts after creating cards: \n");
        System.out.println("Account1: " + client1Account + "\n");
        System.out.println("Account2: " + client2Account + "\n");

        paymentSystem.topUpAccount(client1AccountId, new BigDecimal(25000));
        paymentSystem.topUpAccount(client2AccountId, new BigDecimal(20000));

        System.out.println("Accounts after replenishment: \n");
        System.out.println("Account1: " + client1Account + "\n");
        System.out.println("Account2: " + client2Account + "\n");

        paymentSystem.makePayment(client1AccountId, new BigDecimal(2896));
        System.out.println("Account1 after payment: " + client1Account + "\n");

        paymentSystem.makeTransaction(client1AccountId, client2AccountId, new BigDecimal(2500));

        System.out.println("Accounts after transaction: \n");
        System.out.println("Account1: " + client1Account + "\n");
        System.out.println("Account2: " + client2Account + "\n");
    }

}
